package naero.naeroserver.order.service;

import naero.naeroserver.cart.dto.CartDTO;
import naero.naeroserver.coupon.repository.CouponListRepository;
import naero.naeroserver.entity.coupon.TblCouponList;
import naero.naeroserver.entity.order.TblAddress;
import naero.naeroserver.entity.order.TblOrder;
import naero.naeroserver.entity.order.TblOrderDetail;
import naero.naeroserver.entity.order.TblPayment;
import naero.naeroserver.entity.product.TblOption;
import naero.naeroserver.entity.product.TblProduct;
import naero.naeroserver.entity.user.TblUser;
import naero.naeroserver.member.dto.UserDTO;
import naero.naeroserver.member.repository.UserRepository;
import naero.naeroserver.order.dto.*;
import naero.naeroserver.order.repository.AddressRepository;
import naero.naeroserver.order.repository.OrderDetailRepository;
import naero.naeroserver.order.repository.OrderRepository;
import naero.naeroserver.order.repository.PaymentRepository;
import naero.naeroserver.product.repository.OptionRepository;
import naero.naeroserver.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final PaymentRepository paymentRepository;
    private final OptionRepository optionRepository;
    private final AddressRepository addressRepository;
    private final CouponListRepository couponListRepository;

    @Value("${portone.api-key}")
    private String API_KEY;
    @Value("${portone.api-secret}")
    private String API_SECRET;
    private static final long TOKEN_EXPIRY_DURATION = 3600 * 1000; // 1시간
    @Value("${image.image-url}")
    private String IMG_URL;

    private final AtomicReference<String> cachedToken = new AtomicReference<>();
    private long tokenFetchTime = 0;

    private final WebClient webClient = WebClient.builder().baseUrl("https://api.portone.io").build();

    @Autowired
    public OrderService(ModelMapper modelMapper, OrderRepository orderRepository, UserRepository userRepository,
                        ProductRepository productRepository, OrderDetailRepository orderDetailRepository, PaymentRepository paymentRepository, OptionRepository optionRepository, AddressRepository addressRepository, CouponListRepository couponListRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.paymentRepository = paymentRepository;
        this.optionRepository = optionRepository;
        this.addressRepository = addressRepository;
        this.couponListRepository = couponListRepository;
    }

    // 주문 페이지로 이동 시 들고갈 정보 생성
    public OrderPageDTO startOrder(List<CartDTO> cartDTOList) {

        Integer userId = cartDTOList.get(0).getUserId();    // 회원번호

        OrderDTO newOrder = new OrderDTO();
        UserDTO orderUser = new UserDTO();

        TblUser userInfo = userRepository.findTblUserByUserId(userId);
        List<TblAddress> userAddressInfo = addressRepository.findTblAddressByUserId(userId);

        // 주문자 정보 저장
        orderUser.setUserId(userId);
        orderUser.setUserFullName(userInfo.getUserFullname());
        orderUser.setUserEmail(userInfo.getUserEmail());
        orderUser.setUserPhone(userInfo.getUserPhone());

        // 배송지 정보 + 할인 정보(잔여 포인트)
        newOrder.setUserId(userId);
        newOrder.setRecipientName(userInfo.getUserFullname());
        newOrder.setRecipientPhoneNumber(userInfo.getUserPhone());
        newOrder.setPointDiscount(userInfo.getUserPoint());
        newOrder.setPostalCode(userAddressInfo.get(0).getPostalCode());
        newOrder.setAddressRoad(userAddressInfo.get(0).getAddressRoad());
        newOrder.setAddressDetail(userAddressInfo.get(0).getAddressDetail());
        newOrder.setAddressName(userAddressInfo.get(0).getAddressName());

        // 주문할 상품 정보 조회해서 리스트에 담기
        List<OrderPageProductDTO> orderProductsInfo = new ArrayList<>();

        for (CartDTO product : cartDTOList) {
            OrderPageProductDTO foundProduct = productRepository.findProductDetailForOrder(product.getOptionId());
            foundProduct.setCount(product.getCount());
            foundProduct.setProductImg(IMG_URL + foundProduct.getProductImg());
            foundProduct.setProductThumbnail(IMG_URL + foundProduct.getProductThumbnail());
            orderProductsInfo.add(foundProduct);
        }

        // 배송비 계산하기
        Map<Integer, Integer> deliveryFeeList = new HashMap<>();
        Map<Integer, Integer> productPriceByProducer = new HashMap<>();

//        for (OrderPageProductDTO orderProduct : orderProductsInfo) {
//            if (!deliveryFeeList.containsKey(orderProduct.getProducerId())) {
//                deliveryFeeList.put(orderProduct.getProducerId(), orderProduct.getDeliveryFee());
//
//                // 해당 상품 총 주문 금액 = (기본 상품 금액 + 옵션 추가 금액) * 주문 수량
//                int productPrice = (orderProduct.getAmount() + orderProduct.getAddPrice()) * orderProduct.getCount();
//                productPriceByProducer.put(orderProduct.getProducerId(), productPrice);
//            } else {
//                int productPrice = (orderProduct.getAmount() + orderProduct.getAddPrice()) * orderProduct.getCount();
//                productPriceByProducer.put(orderProduct.getProducerId(), productPriceByProducer.get(orderProduct.getProducerId()) + productPrice);
//            }
//        }

        int totalOrderCount = 0;

        // 위의 코드 리팩토링
        for (OrderPageProductDTO orderProduct : orderProductsInfo) {
            // 현재 상품의 총 주문 금액 계산: (기본 상품 금액 + 옵션 추가 금액) * 주문 수량
            int addPrice = (orderProduct.getAddPrice() != null) ? orderProduct.getAddPrice() : 0;
            int productPrice = (orderProduct.getAmount() + addPrice) * orderProduct.getCount();

            // deliveryFeeList에 생산자 ID가 없으면 초기화
            deliveryFeeList.putIfAbsent(orderProduct.getProducerId(), orderProduct.getDeliveryFee());

            // productPriceByProducer에 생산자별 주문 금액 누적
            productPriceByProducer.merge(orderProduct.getProducerId(), productPrice, Integer::sum);

            // for문 한 번 돌리는 김에..총 주문 수량 구하기
            totalOrderCount += orderProduct.getCount();

        }

        // 무료 배송 기준 비교 및 배송비 업데이트
        for (Map.Entry<Integer, Integer> entry : productPriceByProducer.entrySet()) {
            Integer producerId = entry.getKey();
            Integer totalPrice = entry.getValue();

            // producerId별 무료 배송 기준을 비교하여 배송비 수정
            for (OrderPageProductDTO orderProduct : orderProductsInfo) {
                if (orderProduct.getProducerId().equals(producerId)) {
                    if (totalPrice >= orderProduct.getDeliveryCrit()) {
                        deliveryFeeList.put(producerId, 0); // 배송비를 0으로 설정
                    }
                    break; // 같은 판매자에 대해 한 번만 확인
                }
            }
        }

        // 판매자별 배송비 금액 구해서 총 배송비 금액 계산
        int totalDeliveryFee = 0;
        for (int deliveryFee : deliveryFeeList.values()) {
            totalDeliveryFee += deliveryFee;
        }

        int totalPrice = 0;
        for (int price : productPriceByProducer.values()) {
            totalPrice += price;
        }

        newOrder.setDeliveryFee(totalDeliveryFee);
        newOrder.setOrderTotalAmount(totalPrice);
        newOrder.setOrderTotalCount(totalOrderCount);

        return new OrderPageDTO(newOrder, orderUser, orderProductsInfo);
    }

    @Transactional
    public Object insertOrder(OrderDTO orderDTO, PaymentDTO paymentDTO, Map<Integer, Integer> optionIds) {
        log.info("[OrderService] insertOrder() 시작");
        log.info("[OrderService] orderDTO : {}", orderDTO);

        try {
            // 1. 결제 정보 검증
            System.out.println("portoneToken() 시작");
            String portoneToken = getPortOneToken();
            log.info(portoneToken);
//            Map<String, Object> paymentInfo = validatePayment(paymentDTO.getImp_uid(), orderDTO.getOrderTotalAmount(), portoneToken);
            // 결제 검증 후 응답에서 필요한 정보 추가
//            paymentDTO.setCurrency((String) paymentInfo.get("currency"));
            paymentDTO.setCurrency("KRW");
            paymentDTO.setPaymentStatus("completed"); // 예: 결제 완료로 설정
//            paymentDTO.setTransaction_id((String) paymentInfo.get("transaction_id"));
            paymentDTO.setTransactionId("transid_1223423423422");
//            paymentDTO.setReceipt_url((String) paymentInfo.get("receipt_url"));
            paymentDTO.setReceiptUrl("sample_url");

            // 2. 옵션 조회 및 재고 확인
            // <옵션아이디, 옵션 주문수량>
            for (Map.Entry<Integer, Integer> entry : optionIds.entrySet()) {
                TblOption option = optionRepository.findById(entry.getKey()).orElseThrow(()
                        -> new IllegalArgumentException("해당 상품에 대한 옵션이 존재하지 않습니다."));;

                if (option.getOptionQuantity() < entry.getValue()) {
                    throw new IllegalStateException("재고가 부족합니다.");
                } else {
                    option.setOptionQuantity(option.getOptionQuantity() - entry.getValue());
                }

            }

            TblUser user = userRepository.findTblUserByUserId(orderDTO.getUserId());

            // 2. 주문 정보 저장
//            TblOrder order = modelMapper.map(orderDTO, TblOrder.class);
//            order.setUserId(user.getUserId());
//            order.setOrderDatetime(Instant.now());
//
//            orderRepository.saveAndFlush(order); // order 즉시 persisted(payment에서 참조하기 위해)

            // 영속성 문제로 일일이 모든 컬럼 명시적으로 작성해야 함
            TblOrder order = new TblOrder();
            order.setUserId(user.getUserId());
            order.setOrderDatetime(Instant.now());
            order.setOrderTotalAmount(orderDTO.getOrderTotalAmount());
            order.setOrderTotalCount(orderDTO.getOrderTotalCount());
            order.setDeliveryStatus(orderDTO.getDeliveryStatus());
            order.setOrderStatus(orderDTO.getOrderStatus());
            order.setDeliveryFee(orderDTO.getDeliveryFee());
            order.setPointDiscount(orderDTO.getPointDiscount());
            order.setCouponDiscount(orderDTO.getCouponDiscount());
            order.setRecipientName(orderDTO.getRecipientName());
            order.setRecipientPhoneNumber(orderDTO.getRecipientPhoneNumber());
            order.setPostalCode(orderDTO.getPostalCode());
            order.setAddressRoad(orderDTO.getAddressRoad());
            order.setAddressDetail(orderDTO.getAddressDetail());
            order.setAddressName(orderDTO.getAddressName());
            order.setDeliveryNote(orderDTO.getDeliveryNote());
            orderRepository.save(order);

            log.info("Order ID after saving: {}", order.getOrderId()); // 확인

            // 3. 결제 정보 저장
//            TblPayment payment = modelMapper.map(paymentDTO, TblPayment.class);
//            payment.setUserId(user.getUserId());
//            payment.setOrderId(order.getOrderId()); // 저장된 order의 ID 참조
//            payment.setAmount(orderDTO.getOrderTotalAmount());

            TblPayment payment = new TblPayment();
            payment.setUserId(paymentDTO.getUserId());
            payment.setOrderId(order.getOrderId());
            payment.setAmount(order.getOrderTotalAmount());
            payment.setCurrency(paymentDTO.getCurrency());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            payment.setPaymentStatus(paymentDTO.getPaymentStatus());
            payment.setImpUid(paymentDTO.getImpUid());
            payment.setMerchantUid(paymentDTO.getMerchantUid());
            payment.setReceiptUrl(paymentDTO.getReceiptUrl());
            payment.setTransactionId(paymentDTO.getTransactionId());

            paymentRepository.save(payment);

            // 4. 주문 상세 정보 저장
            for (Map.Entry<Integer, Integer> entry : optionIds.entrySet()) {
                TblOption option = optionRepository.findById(entry.getKey()).orElseThrow(()
                        -> new IllegalArgumentException("해당 상품에 대한 옵션이 존재하지 않습니다."));;

                TblProduct product = productRepository.findTblProductByProductId(option.getProductId());

                int addPrice = (option.getAddPrice() != null) ? option.getAddPrice() : 0;
                int amount = product.getProductPrice() + addPrice;

                TblOrderDetail orderDetail = new TblOrderDetail();
                orderDetail.setAmount(amount);
                orderDetail.setCount(entry.getValue());
                orderDetail.setOrderId(order.getOrderId());
                orderDetail.setOptionId(entry.getKey());

                orderDetailRepository.save(orderDetail);
            }

            // 사용된 포인트 소진
            user.setUserPoint(user.getUserPoint() - order.getPointDiscount());
            // 사용된 쿠폰 소진
            TblCouponList usedCoupon = couponListRepository.findTblCouponListByUserIdAndCouponId(user.getUserId(), orderDTO.getCouponId());
            usedCoupon.setUseStatus("Y");

            return modelMapper.map(order, OrderDTO.class);
        } catch (Exception e) {
            log.error("[OrderService] Exception: {}", e.getMessage());
            throw new RuntimeException("주문 처리 중 오류가 발생했습니다."); // 일반 예외 처리
        }
    }

    // 결제 검증 메서드
//    public Mono<Map> validatePaymentAsync(String impUid, int orderTotalAmount, String portoneToken) {
//        String url = "/v2/payments/" + impUid;
//
//        return webClient.get()
//                .uri(url)
//                .header(HttpHeaders.AUTHORIZATION, portoneToken)
//                .retrieve()
//                .bodyToMono(Map.class)
//                .doOnSuccess(paymentData -> {
//                    // 응답 후 로직 처리
//                    int amount = (int) paymentData.get("amount");
//                    if (orderTotalAmount != amount) {
//                        throw new IllegalStateException("결제 금액 불일치");
//                    }
//                });
//    }
//    private Map<String, Object> validatePayment(String impUid, int orderTotalAmount, String portoneToken) {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "https://api.portone.io/v2/payments/" + impUid;
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", portoneToken);
//
//        ResponseEntity<Map> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                new HttpEntity<>(headers),
//                Map.class
//        );
//
//        Map<String, Object> paymentData = response.getBody();
//        int amount = (int) paymentData.get("amount");
//        if (orderTotalAmount != amount) {
//            throw new IllegalStateException("결제 금액 불일치");
//        }
//
//        return paymentData;
//    }
    private Map<String, Object> validatePayment(String impUid, int orderTotalAmount, String portoneToken) {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.portone.io/v2/payments/")
                .defaultHeader("Authorization", portoneToken)
                .build();

        try {
            Map<String, Object> paymentData = webClient.get()
                    .uri(impUid)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                    .block(); // Synchronous operation

            int amount = (int) paymentData.get("amount");
            if (orderTotalAmount != amount) {
                throw new IllegalStateException("결제 금액 불일치: 요청 금액(" + orderTotalAmount + ") vs 결제 금액(" + amount + ")");
            }

            return paymentData;

        } catch (WebClientResponseException e) {
            // Handle HTTP errors
            throw new IllegalStateException("결제 정보 조회 실패: " + e.getStatusCode() + " " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            // Handle other errors
            throw new IllegalStateException("결제 검증 중 알 수 없는 오류 발생", e);
        }
    }

    // 포트원 토큰 요청 메서드
//    private String getPortOneToken() {
//        if (isTokenValid()) {
//            return cachedToken.get();
//        }
//
//        String newToken = requestNewToken();
//        cachedToken.set(newToken);
//        tokenFetchTime = System.currentTimeMillis();
//        return newToken;
//    }
//
//    private boolean isTokenValid() {
//        return cachedToken.get() != null && (System.currentTimeMillis() - tokenFetchTime < TOKEN_EXPIRY_DURATION);
//    }
//
//    private String requestNewToken() {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        body.add("api_key", API_KEY);
//        body.add("api_secret", API_SECRET);
//
//        try {
//            ResponseEntity<Map> response = restTemplate.postForEntity(
//                    API_URL,
//                    new HttpEntity<>(body, headers),
//                    Map.class
//            );
//
//            Map<String, Object> responseBody = response.getBody();
//            if (responseBody == null || !responseBody.containsKey("access_token")) {
//                throw new IllegalStateException("토큰 응답이 유효하지 않습니다: " + responseBody);
//            }
//
//            return "Bearer " + responseBody.get("access_token").toString();
//        } catch (Exception e) {
//            throw new IllegalStateException("포트원 토큰 요청 실패", e);
//        }
//    }
    private String getPortOneToken() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.portone.io/login/api-secret";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
//        body.put("api_key", API_KEY);
        body.put("apiSecret", API_SECRET);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                url,
                new HttpEntity<>(body, headers),
                Map.class
        );

        return "Bearer " + Objects.requireNonNull(response.getBody()).get("accessToken").toString();
    }

    // 결제 취소
    @Transactional
    public void cancelPayment(String paymentId) {
        log.info("[OrderService] cancelPayment() 시작, paymentId: {}", paymentId);

        TblPayment payment = paymentRepository.findById(Integer.valueOf(paymentId))
                .orElseThrow(() -> new IllegalArgumentException("결제 정보가 존재하지 않습니다."));

        TblOrder order = orderRepository.findById(payment.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("주문 정보가 존재하지 않습니다."));

        if (!order.getDeliveryStatus().equals("pending") || order.getOrderStatus().equals("canceled")) {
            throw new IllegalStateException("취소 불가한 주문 건 입니다.");
        }

        try {
            // 1. 포트원 API를 통해 결제 취소 요청
//            String portoneToken = getPortOneToken();
//            String url = "https://api.portone.io/payments/" + paymentId + "/cancel";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.set("Authorization", portoneToken);
//
//            ResponseEntity<Map> response = new RestTemplate().exchange(
//                    url,
//                    HttpMethod.POST,
//                    new HttpEntity<>(headers),
//                    Map.class
//            );

//            if (response.getStatusCode() == HttpStatus.OK) {
//                log.info("결제 취소 성공: {}", response.getBody());

                // 2. 결제 정보 업데이트
                payment.setPaymentStatus("canceled");
                payment.setUpdatedAt(Instant.now());

                // 3. 주문 정보 업데이트
                order.setOrderStatus("canceled");
                order.setUpdatedAt(Instant.now());

                // 포인트 원복
                TblUser user = userRepository.findTblUserByUserId(order.getUserId());
                user.setUserPoint(user.getUserPoint() + order.getPointDiscount());
                // 쿠폰 원복
                TblCouponList coupon = couponListRepository.findTblCouponListByUserIdAndCouponId(user.getUserId(), order.getCouponId());
                coupon.setUseStatus("N");

                // 4. 상품 정보(재고 원복) 업데이트
                TblOrderDetail orderProduct = orderDetailRepository.findByOrderId(order.getOrderId());
                TblOption option = optionRepository.findTblOptionByOptionId(orderProduct.getOptionId());
                option.setOptionQuantity(option.getOptionQuantity() + order.getOrderTotalCount());


//            } else {
//                log.error("결제 취소 실패: {}", response.getBody());
//                throw new IllegalStateException("결제 취소 요청이 실패했습니다.");
//            }
        } catch (Exception e) {
            log.error("[OrderService] 결제 취소 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("결제 취소 중 오류가 발생했습니다.");
        }
    }

    // ================================================================================================================

    // 마이페이지 내 구매자별 주문 리스트 조회 (최신순으로)
    public Object selectOrderList(String userId) {
        List<TblOrder> orderList = orderRepository.findByUserOrderByRecent(Integer.valueOf(userId));

        log.info("[OrderService] orderList {}", orderList);

        return orderList.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
    }

    // 특정 주문번호에 대한 상세 정보 조회(구매자, 판매자, 관리자 모두 해당)
    public Object getOrderDetail(String orderId) {
        TblOrder order = orderRepository.findById(Integer.valueOf(orderId))
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));

        log.info("[OrderService] order {}", order);

        return modelMapper.map(order, OrderDTO.class);
    }

    // 주문 리스트 내 주문번호 별 주문 상품 리스트 조회(구매자, 판매자, 관리자 모두 해당)
    public Object getOrderDetailList(String orderId) {
        List<OrderDetailProductDTO> orderDetails = orderDetailRepository.findOrderDetailWithProductByOrder(Integer.valueOf(orderId));

        log.info("[OrderService] orderDetails {}", orderDetails);

        for(int i = 0 ; i < orderDetails.size() ; i++) {
            orderDetails.get(i).setProductImg(IMG_URL + orderDetails.get(i).getProductImg());
            orderDetails.get(i).setProductThumbnail(IMG_URL + orderDetails.get(i).getProductThumbnail());
        }

        return orderDetails;
    }

    // 해당 판매자의 주문 건 전체 조회
    public Object getOrderListByProducer(String producerId) {
        List<TblOrder> orderListByProducer = orderRepository.findOrdersByProducerId(Integer.valueOf(producerId));

        log.info("[OrderService] orderListByProducer {}", orderListByProducer);

        return orderListByProducer.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
    }

    // 관리자 모든 주문 건 조회(최신순으로)
    public Object getAllOrderList() {
        List<TblOrder> allOrderList = orderRepository.findAllOrderByRecent();

        log.info("[OrderService] allOrderList {}", allOrderList);

        return allOrderList.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
    }


}
