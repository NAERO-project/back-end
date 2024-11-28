package naero.naeroserver.order.service;

import naero.naeroserver.cart.dto.CartDTO;
import naero.naeroserver.common.Criteria;
import naero.naeroserver.coupon.repository.CouponListRepository;
import naero.naeroserver.entity.coupon.TblCouponList;
import naero.naeroserver.entity.order.TblAddress;
import naero.naeroserver.entity.order.TblOrder;
import naero.naeroserver.entity.order.TblOrderDetail;
import naero.naeroserver.entity.order.TblPayment;
import naero.naeroserver.entity.product.TblOption;
import naero.naeroserver.entity.product.TblProduct;
import naero.naeroserver.entity.ship.TblShipping;
import naero.naeroserver.entity.user.TblProducer;
import naero.naeroserver.entity.user.TblUser;
import naero.naeroserver.member.dto.UserDTO;
import naero.naeroserver.member.repository.ProducerRepository;
import naero.naeroserver.member.repository.UserRepository;
import naero.naeroserver.order.dto.*;
import naero.naeroserver.order.repository.AddressRepository;
import naero.naeroserver.order.repository.OrderDetailRepository;
import naero.naeroserver.order.repository.OrderRepository;
import naero.naeroserver.order.repository.PaymentRepository;
import naero.naeroserver.product.dto.ProductOptionDTO;
import naero.naeroserver.product.repository.OptionRepository;
import naero.naeroserver.product.repository.ProductRepository;
import naero.naeroserver.shipping.repository.TblShippingRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
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
    private final ProducerRepository producerRepository;
    private final TblShippingRepository tblShippingRepository;

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
                        ProductRepository productRepository, OrderDetailRepository orderDetailRepository, PaymentRepository paymentRepository, OptionRepository optionRepository, AddressRepository addressRepository, CouponListRepository couponListRepository, ProducerRepository producerRepository, TblShippingRepository tblShippingRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.paymentRepository = paymentRepository;
        this.optionRepository = optionRepository;
        this.addressRepository = addressRepository;
        this.couponListRepository = couponListRepository;
        this.producerRepository = producerRepository;
        this.tblShippingRepository = tblShippingRepository;
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
        orderUser.setUserPoint(userInfo.getUserPoint());

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
        log.info("[OrderService] paymentDTO : {}", paymentDTO);
        log.info("[OrderService] optionIds : {}", optionIds);

        try {
            if (!Objects.equals(paymentDTO.getPaymentMethod(), "BANK_TRANSFER")) {
                // 1. 결제 정보 검증
                String portoneToken = getPortOneToken();
                log.info(portoneToken);
                Map<String, Object> paymentInfo = validatePayment(paymentDTO.getImpUid(), orderDTO.getOrderTotalAmount(), portoneToken);
                // 결제 검증 후 응답에서 필요한 정보 추가
                paymentDTO.setTransactionId((String) paymentInfo.get("transaction_id"));
                paymentDTO.setReceiptUrl((String) paymentInfo.get("receipt_url"));
                paymentDTO.setPaymentStatus("completed"); // 결제 완료로 설정
            } else {
                paymentDTO.setPaymentStatus("pending"); // 무통장 입금은 바로 결제 완료가 아님
                paymentDTO.setImpUid("payment-" + new Date().getTime());
            }


            // 2. 옵션 조회 및 재고 확인
            // <옵션아이디, 옵션 주문수량>
            for (Map.Entry<Integer, Integer> entry : optionIds.entrySet()) {
                TblOption option = optionRepository.findById(entry.getKey()).orElseThrow(()
                        -> new IllegalArgumentException("해당 상품에 대한 옵션이 존재하지 않습니다."));;

                if (option.getOptionQuantity() < entry.getValue()) {
                    throw new IllegalStateException("주문 상품 재고가 부족합니다.");
                } else {
                    option.setOptionQuantity(option.getOptionQuantity() - entry.getValue());
                }

            }

            TblUser user = userRepository.findTblUserByUserId(orderDTO.getUserId());
            user.setUserPoint((int) (user.getUserPoint() + (orderDTO.getOrderTotalAmount() * 0.1)));

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
            order.setOrderStatus("completed");
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

            System.out.println("paymentDTO" + paymentDTO);

            TblPayment payment = new TblPayment();
            payment.setUserId(paymentDTO.getUserId());
            payment.setOrderId(order.getOrderId());
            payment.setAmount(orderDTO.getOrderTotalAmount());
            payment.setCurrency(paymentDTO.getCurrency());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            payment.setPaymentStatus(paymentDTO.getPaymentStatus());
            payment.setImpUid(paymentDTO.getImpUid());
            payment.setMerchantUid(paymentDTO.getMerchantUid());
            payment.setReceiptUrl(paymentDTO.getReceiptUrl());
            payment.setTransactionId(paymentDTO.getTransactionId());

            paymentRepository.save(payment);

            // 4. 배송 정보 저장
            // 4-1. 주문 정보에서 서로 다른 판매자를 추출하기
            // 중복을 방지하기 위해 Set 데이터 타입으로 선언
            Set<Integer> producerSet = new HashSet<>();

            // 프런트에서 전달 받은 optionIds 파라미터에서 판매자 ID를 producerSet에 저장
            for (Map.Entry<Integer, Integer> entry : optionIds.entrySet()) {

                // optionIds entry 키를 이용해 option을 추출
                TblOption option = optionRepository.findById(entry.getKey())
                        .orElseThrow(() -> new IllegalArgumentException("해당 상품에 대한 옵션이 존재하지 않습니다."));

                // option 엔티티를 통해 product 엔티티를 추출
                TblProduct product = productRepository.findTblProductByProductId(option.getProductId());

                // product 엔티티에서 producerId를 추출하여 producerSet에 입력
                producerSet.add(product.getProducerId());
            }

            // 4-2. Map to store producerId and shippingId
            Map<Integer, Integer> shippingIdByProducer = new HashMap<>();

            // 4-3.producerSet에서 루프를 돌려 producer별 배송 엔티티를 생성
            for (Integer producer : producerSet) {
                TblShipping shipping = new TblShipping();

                shipping.setShippingStatus("pending");
                shipping.setOrderId(order.getOrderId());

                TblShipping savedShipping = tblShippingRepository.save(shipping);
                shippingIdByProducer.put(producer, savedShipping.getShippingId());    // Store the shippingId
            }
            System.out.println("shippingIdByProducer: " + shippingIdByProducer);

            // 5. 주문 상세 정보 저장
            for (Map.Entry<Integer, Integer> entry : optionIds.entrySet()) {
                TblOption option = optionRepository.findById(entry.getKey()).orElseThrow(()
                        -> new IllegalArgumentException("해당 상품에 대한 옵션이 존재하지 않습니다."));

                TblProduct product = productRepository.findTblProductByProductId(option.getProductId());

                int addPrice = (option.getAddPrice() != null) ? option.getAddPrice() : 0;
                int amount = product.getProductPrice() + addPrice;

                TblOrderDetail orderDetail = new TblOrderDetail();
                orderDetail.setAmount(amount);
                orderDetail.setCount(entry.getValue());
                orderDetail.setOrderId(order.getOrderId());
                orderDetail.setOptionId(entry.getKey());

                // Retrieve the shippingId for the current product's producer
                Integer shippingId = shippingIdByProducer.get(product.getProducerId());
                orderDetail.setShippingId(shippingId);  // Assuming TblOrderDetail has a field for shippingId

                orderDetailRepository.save(orderDetail);
            }

            // 사용된 포인트 소진
            user.setUserPoint(user.getUserPoint() - order.getPointDiscount());
            // 사용된 쿠폰 소진
            TblCouponList usedCoupon = couponListRepository.findTblCouponListByUserIdAndCouponId(user.getUserId(), orderDTO.getCouponId());
            if (usedCoupon != null) usedCoupon.setUseStatus("Y");

            return modelMapper.map(order, OrderDTO.class);
        } catch (Exception e) {
            log.error("[OrderService] Exception: {}", e.getMessage());
            throw new RuntimeException(e.getMessage()); // 일반 예외 처리
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

        log.info("impUid: {}", impUid);
        log.info("portoneToken: {}", portoneToken);


        try {
            Map<String, Object> paymentData = webClient.get()
                    .uri(impUid)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                    .block(); // Synchronous operation

            log.info("결제 정보 조회 응답: {}", paymentData);

            Map<String, Object> payment = (Map<String, Object>) paymentData.get("payment");
            if (payment == null) {
                throw new IllegalStateException("payment 데이터가 없습니다.");
            }

            // 'transactions' 데이터 가져오기
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> transactions = (List<Map<String, Object>>) payment.get("transactions");
            if (transactions == null || transactions.isEmpty()) {
                throw new IllegalStateException("transactions 데이터가 없습니다.");
            }

            // 첫 번째 transaction 가져오기
            Map<String, Object> firstTransaction = transactions.get(0);
            if (firstTransaction == null) {
                throw new IllegalStateException("첫 번째 transaction 데이터가 없습니다.");
            }

            // 'amount' 데이터 가져오기
            @SuppressWarnings("unchecked")
            Map<String, Object> amountData = (Map<String, Object>) firstTransaction.get("amount");
            if (amountData == null) {
                throw new IllegalStateException("amount 데이터가 없습니다.");
            }

            // 'total' 금액 가져오기
            Integer amount = (Integer) amountData.get("total");
            if (amount == null) {
                throw new IllegalStateException("amount total 값이 없습니다.");
            }

            log.info("결제된 금액: {}", amount);

            // 금액 검증
            if (orderTotalAmount != amount) {
                throw new IllegalStateException("결제 금액 불일치: 요청 금액(" + orderTotalAmount + ") vs 결제 금액(" + amount + ")");
            }

            return paymentData;

        } catch (WebClientResponseException e) {
            log.error("HTTP 에러 발생: {} {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw new IllegalStateException("결제 정보 조회 실패: " + e.getStatusCode(), e);
        } catch (Exception e) {
            log.error("알 수 없는 오류 발생: {}", e.getMessage(), e);
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
    public void cancelPayment(String orderId) {
        log.info("[OrderService] cancelPayment() 시작, orderId: {}", orderId);

        TblOrder order = orderRepository.findById(Integer.valueOf(orderId))
                .orElseThrow(() -> new IllegalArgumentException("주문 정보가 존재하지 않습니다."));

        TblPayment payment = paymentRepository.findByOrderId(Integer.valueOf(orderId));

        if (!order.getDeliveryStatus().equals("pending") || order.getOrderStatus().equals("canceled")) {
            throw new IllegalStateException("취소 불가한 주문 건 입니다.");
        }

        try {
            if (!payment.getPaymentMethod().equals("BANK_TRANSFER")) {
                // 1. 포트원 API를 통해 결제 취소 요청
                String portoneToken = getPortOneToken();
                String url = "https://api.portone.io/payments/" + payment.getImpUid() + "/cancel";

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("Authorization", portoneToken);

                // 요청 본문 생성
                Map<String, String> body = new HashMap<>();
                body.put("reason", "고객 요청에 의한 취소");

                HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

                ResponseEntity<Map> response = new RestTemplate().exchange(
                        url,
                        HttpMethod.POST,
                        entity,
                        Map.class
                );

                if (response.getStatusCode() == HttpStatus.OK) {
                    log.info("결제 취소 성공: {}", response.getBody());
                } else {
                    log.error("결제 취소 실패: {}", response.getBody());
                    throw new IllegalStateException("결제 취소 요청이 실패했습니다.");
                }
            }

            // 2. 결제 정보 업데이트
            payment.setPaymentStatus("canceled");
            payment.setUpdatedAt(Instant.now());

            // 3. 주문 정보 업데이트
            order.setOrderStatus("canceled");
            order.setDeliveryStatus("canceled");
            order.setUpdatedAt(Instant.now());

            TblUser user = userRepository.findTblUserByUserId(order.getUserId());

            // 4. 포인트 원복
            if (order.getPointDiscount() != 0) {
                user.setUserPoint(user.getUserPoint() + order.getPointDiscount());
            }

            // 5. 쿠폰 상태 원복
            TblCouponList coupon = couponListRepository.findTblCouponListByUserIdAndCouponId(
                    user.getUserId(), order.getCouponId());

            if (coupon != null) {
                coupon.setUseStatus("N");
            }

            // 6. 상품 재고 원복
            TblOrderDetail orderProduct = orderDetailRepository.findByOrderId(order.getOrderId());
            TblOption option = optionRepository.findTblOptionByOptionId(orderProduct.getOptionId());
            option.setOptionQuantity(option.getOptionQuantity() + order.getOrderTotalCount());

        } catch (Exception e) {
            log.error("[OrderService] 결제 취소 중 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException("결제 취소 중 오류가 발생했습니다.", e);
        }
    }

    // ================================================================================================================

    // 마이페이지 내 구매자별 주문 리스트 조회 (최신순으로)
    public int selectOrderListPage(int userId) {
        List<TblOrder> orderList = orderRepository.findByUserOrderByRecent(userId);

        return orderList.size();
    }

    public Object selectOrderList(int userId, Criteria cri) {
        int index = cri.getPageNum() -1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("orderId").descending());

        Page<TblOrder> result = orderRepository.findByUserOrderByRecentPaging(userId, paging);
        List<TblOrder> orderList = (List<TblOrder>)result.getContent();

        return orderList.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
    }

    // 특정 주문번호에 대한 상세 정보 조회(구매자, 판매자, 관리자 모두 해당)
    public Object getOrderDetail(String orderId) {
        TblOrder order = orderRepository.findById(Integer.valueOf(orderId))
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));

        log.info("[OrderService] order {}", order);

        return modelMapper.map(order, OrderDTO.class);
    }

    // 주문 리스트 내 주문번호 별 주문 상품 리스트 조회(구매자, 관리자 모두 해당)
    public Object getOrderDetailList(String orderId) {
        List<OrderDetailProductDTO> orderDetails = orderDetailRepository.findOrderDetailWithProductByOrder(Integer.valueOf(orderId));

        log.info("[OrderService] orderDetails {}", orderDetails);

        for(int i = 0 ; i < orderDetails.size() ; i++) {
            orderDetails.get(i).setProductImg(IMG_URL + orderDetails.get(i).getProductImg());
            orderDetails.get(i).setProductThumbnail(IMG_URL + orderDetails.get(i).getProductThumbnail());
        }

        return orderDetails;
    }

    // 주문 리스트 내 주문번호 별 해당 판매자의 주문 상품 리스트 조회(판매자 해당)
    public Object getProducerOrderDetailList(String orderId, int producerId) {
        List<OrderDetailProductDTO> orderDetails = orderDetailRepository
                .findProducerOrderDetailWithProductByOrder(Integer.valueOf(orderId), producerId);

        for(int i = 0 ; i < orderDetails.size() ; i++) {
            orderDetails.get(i).setProductImg(IMG_URL + orderDetails.get(i).getProductImg());
            orderDetails.get(i).setProductThumbnail(IMG_URL + orderDetails.get(i).getProductThumbnail());
        }

        return orderDetails;
    }

    // 해당 판매자의 주문 건 전체 조회
    public int getOrderListByProducer(int producerId) {
        List<TblOrder> orderListByProducer = orderRepository.findOrdersByProducerId(producerId);

        return orderListByProducer.size();
    }

    public Object getOrderListByProducerPaging(int producerId, Criteria cri) {
        int index = cri.getPageNum() -1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("orderId").descending());

        Page<TblOrder> result = orderRepository.findOrdersByProducerId(producerId, paging);
        List<TblOrder> orderList = result.getContent();
        return orderList.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
    }

    // 관리자 모든 주문 건 조회(최신순으로)
    public Object getAllOrderList() {
        List<TblOrder> allOrderList = orderRepository.findAllOrderByRecent();

        log.info("[OrderService] allOrderList {}", allOrderList);

        return allOrderList.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
    }


}
