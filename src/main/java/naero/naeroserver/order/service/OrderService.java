package naero.naeroserver.order.service;

import naero.naeroserver.entity.order.TblOrder;
import naero.naeroserver.entity.order.TblOrderDetail;
import naero.naeroserver.entity.order.TblPayment;
import naero.naeroserver.entity.product.TblOption;
import naero.naeroserver.entity.product.TblProduct;
import naero.naeroserver.entity.user.TblUser;
import naero.naeroserver.member.repository.ProducerRepository;
import naero.naeroserver.member.repository.UserRepository;
import naero.naeroserver.order.dto.OrderDTO;
import naero.naeroserver.order.dto.OrderDetailProductDTO;
import naero.naeroserver.order.dto.PaymentDTO;
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
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
//    private final PaymentService paymentService;
    private final OrderDetailRepository orderDetailRepository;
    private final PaymentRepository paymentRepository;
    private final OptionRepository optionRepository;
    private final ProducerRepository producerRepository;

    @Value("${portone.api-key}")
    private String API_KEY;
    @Value("${portone.api-secret}")
    private String API_SECRET;
    private static final long TOKEN_EXPIRY_DURATION = 3600 * 1000; // 1시간

    private final AtomicReference<String> cachedToken = new AtomicReference<>();
    private long tokenFetchTime = 0;

    private final WebClient webClient = WebClient.builder().baseUrl("https://api.portone.io").build();

    @Autowired
    public OrderService(ModelMapper modelMapper, OrderRepository orderRepository, UserRepository userRepository,
                        ProductRepository productRepository, OrderDetailRepository orderDetailRepository, PaymentRepository paymentRepository, OptionRepository optionRepository, ProducerRepository producerRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.paymentRepository = paymentRepository;
        this.optionRepository = optionRepository;
        this.producerRepository = producerRepository;
    }

    @Transactional
    public Object insertOrder(OrderDTO orderDTO, PaymentDTO paymentDTO, int optionId) {
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
            paymentDTO.setTransaction_id("transid_1223423423422");
//            paymentDTO.setReceipt_url((String) paymentInfo.get("receipt_url"));
            paymentDTO.setReceipt_url("sample_url");

            // 2. 옵션 조회 및 재고 확인
            TblOption option = optionRepository.findById(optionId).orElseThrow(()
                    -> new IllegalArgumentException("해당 상품에 대한 옵션이 존재하지 않습니다."));;

            System.out.println(option.getOptionQuantity());
            if (option.getOptionQuantity() < orderDTO.getOrderTotalCount()) {
                throw new IllegalStateException("재고가 부족합니다.");
            } else {
                option.setOptionQuantity(option.getOptionQuantity() - orderDTO.getOrderTotalCount());
            }

            TblUser user = userRepository.findTblUserById(orderDTO.getUserId());

            // 2. 주문 정보 저장
            TblOrder order = new TblOrder();
            order.setUser(user);
            order.setOrderDatetime(Instant.now());
            order.setOrderTotalAmount(orderDTO.getOrderTotalAmount());
            order.setOrderTotalCount(orderDTO.getOrderTotalCount());
            order.setDeliveryStatus(orderDTO.getDeliveryStatus());
            order.setOrderStatus(orderDTO.getOrderStatus());
            order.setDeliveryFee(orderDTO.getDeliveryFee());
            order.setDiscountAmount(orderDTO.getDiscountAmount());
            order.setRecipientName(orderDTO.getRecipientName());
            order.setRecipientPhoneNumber(orderDTO.getRecipientPhoneNumber());
            order.setPostalCode(orderDTO.getPostalCode());
            order.setAddressRoad(orderDTO.getAddressRoad());
            order.setAddressDetail(orderDTO.getAddressDetail());
            order.setAddressName(orderDTO.getAddressName());
            order.setDeliveryNote(orderDTO.getDeliveryNote());

            log.info(String.valueOf(orderDTO));
//            TblOrder order = modelMapper.map(orderDTO, TblOrder.class);
//            order.setUser(user);
//            order.setOrderDatetime(Instant.now());

            orderRepository.save(order);

            // 3. 결제 정보 저장
            TblPayment payment = new TblPayment();
            payment.setUserId(paymentDTO.getUserId());
            payment.setOrder(order); // 저장된 order의 ID 참조
            payment.setAmount(orderDTO.getOrderTotalAmount());
            payment.setCurrency(paymentDTO.getCurrency());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            payment.setPaymentStatus(paymentDTO.getPaymentStatus());
            payment.setImpUid(paymentDTO.getImp_uid());
            payment.setMerchantUid(paymentDTO.getMerchant_uid());
            payment.setReceiptUrl(paymentDTO.getReceipt_url());
            payment.setTransactionId(paymentDTO.getTransaction_id());

            paymentRepository.save(payment);

//            TblProduct product = productRepository.findById(productId);
//            TblOption option = optionRepository.findByProduct(product);
//            if (option.getId() == null) {
//                throw new IllegalStateException("해당 상품에 대한 옵션이 존재하지 않습니다.");
//            }

            // 4. 주문 상세 정보 저장
            TblOrderDetail orderDetail = new TblOrderDetail();
            orderDetail.setAmount(orderDTO.getDiscountAmount());
            orderDetail.setCount(orderDTO.getOrderTotalCount());
            orderDetail.setOrder(order);
            orderDetail.setOption(option);

            orderDetailRepository.save(orderDetail);

            return modelMapper.map(order, OrderDTO.class);
        } catch (IllegalStateException e) {
            log.error("[OrderService] Exception: {}", e.getMessage());
            throw e; // 예외를 다시 던져서 호출하는 쪽에서 처리하도록 함
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
    private Map<String, Object> validatePayment(String impUid, int orderTotalAmount, String portoneToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.portone.io/v2/payments/" + impUid;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", portoneToken);

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Map.class
        );

        Map<String, Object> paymentData = response.getBody();
        int amount = (int) paymentData.get("amount");
        if (orderTotalAmount != amount) {
            throw new IllegalStateException("결제 금액 불일치");
        }

        return paymentData;
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

        return "Bearer " + response.getBody().get("accessToken").toString();
    }

    // 결제 취소
    @Transactional
    public void cancelPayment(String paymentId) {
        log.info("[OrderService] cancelPayment() 시작, paymentId: {}", paymentId);

        TblPayment payment = paymentRepository.findById(Integer.valueOf(paymentId))
                .orElseThrow(() -> new IllegalArgumentException("결제 정보가 존재하지 않습니다."));

        TblOrder order = orderRepository.findById(payment.getOrder().getId())
                .orElseThrow(() -> new IllegalArgumentException("주문 정보가 존재하지 않습니다."));

        if (!order.getDeliveryStatus().equals("pending")) {
            throw new IllegalStateException("취소가 불가한 주문 건 입니다.");
        }

        try {
            // 1. 포트원 API를 통해 결제 취소 요청
            String portoneToken = getPortOneToken();
            String url = "https://api.portone.io/payments/" + paymentId + "/cancel";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", portoneToken);

            ResponseEntity<Map> response = new RestTemplate().exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity<>(headers),
                    Map.class
            );

//            if (response.getStatusCode() == HttpStatus.OK) {
//                log.info("결제 취소 성공: {}", response.getBody());

                // 2. 결제 정보 업데이트
                payment.setPaymentStatus("canceled");
                payment.setUpdatedAt(Instant.now());
                paymentRepository.save(payment);

                // 3. 주문 정보 업데이트
                order.setOrderStatus("canceled");
                order.setUpdatedAt(Instant.now());
                orderRepository.save(order);

                // 4. 상품 정보(재고 원복) 업데이트
                TblOrderDetail orderProduct = orderDetailRepository.findByOrderId(order.getId());
                TblOption option = orderProduct.getOption();
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
        TblUser user = userRepository.findTblUserById(Integer.parseInt(userId));
        List<TblOrder> orderList = orderRepository.findByUserOrderByRecent(user); // 최신순으로 정렬된 주문 리스트 조회

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

        return orderDetails;
    }

    // 판매자 주문 건 조회
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
