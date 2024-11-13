package naero.naeroserver.order.service;

import naero.naeroserver.entity.order.TblOrder;
import naero.naeroserver.entity.order.TblOrderDetail;
import naero.naeroserver.entity.order.TblPayment;
import naero.naeroserver.entity.product.TblProduct;
import naero.naeroserver.entity.user.TblUser;
import naero.naeroserver.member.dto.UserDTO;
import naero.naeroserver.member.repository.UserRepository;
import naero.naeroserver.order.dto.OrderDTO;
import naero.naeroserver.order.dto.OrderDetailDTO;
import naero.naeroserver.order.dto.PaymentDTO;
import naero.naeroserver.order.repository.OrderDetailRepository;
import naero.naeroserver.order.repository.OrderRepository;
import naero.naeroserver.order.repository.PaymentRepository;
import naero.naeroserver.product.dto.ProductDTO;
import naero.naeroserver.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PaymentService paymentService;
    private final OrderDetailRepository orderDetailRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public OrderService(ModelMapper modelMapper, OrderRepository orderRepository, UserRepository userRepository,
                        ProductRepository productRepository, PaymentService paymentService, OrderDetailRepository orderDetailRepository, PaymentRepository paymentRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.paymentService = paymentService;
        this.orderDetailRepository = orderDetailRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public Object insertOrder(OrderDTO orderDTO, PaymentDTO paymentDTO, int productId) {
        log.info("[OrderService] insertOrder() 시작");
        log.info("[OrderService] paymentDTO : {}", orderDTO);

        try {
            // 1. 결제 정보 검증
            String portoneToken = getPortOneToken();
            validatePayment(paymentDTO.getImp_uid(), orderDTO.getOrderTotalAmount(), portoneToken);

            TblUser user = userRepository.findTblUserById(orderDTO.getUserId());

            // 2. 주문 정보 저장
            TblOrder order = new TblOrder();
            order.setUser(user);
            order.setOrderDatetime(Instant.from(LocalDateTime.now()));
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

            orderRepository.save(order);

            // 3. 결제 정보 저장
            TblPayment payment = new TblPayment();
            payment.setUserId(paymentDTO.getUserId());
            payment.setOrder(order); // 저장된 order의 ID 참조
            payment.setAmount(paymentDTO.getAmount());
            payment.setCurrency(paymentDTO.getCurrency());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            payment.setPaymentStatus(paymentDTO.getPaymentStatus());
            payment.setImpUid(paymentDTO.getImp_uid());
            payment.setMerchantUid(paymentDTO.getMerchant_uid());

            paymentRepository.save(payment);

            Integer optionId = optionRepository.findOptionIdByProductId(productId);
            if (optionId == null) {
                throw new IllegalStateException("해당 상품에 대한 옵션이 존재하지 않습니다.");
            }

            // 4. 주문 상세 정보 저장
            TblOrderDetail orderDetail = new TblOrderDetail();
            orderDetail.setAmount(orderDTO.getDiscountAmount());
            orderDetail.setCount(orderDTO.getOrderTotalCount());
            orderDetail.setOrder(order);
            orderDetail.setOptionId(optionId);

            optionRepository.save(orderDetail);

//            return modelMapper.map(order, OrderDTO.class);
            return "주문 성공";
        } catch (Exception e) {
            log.error("[OrderService] Exception: {}", e.getMessage());
            return "주문 실패";
        }
    }

    // 결제 검증 메서드
    private void validatePayment(String impUid, int orderAmount, String portoneToken) {
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

        int amount = (int) response.getBody().get("amount");
        if (orderAmount != amount) {
            throw new IllegalStateException("결제 금액 불일치");
        }
    }

    // 포트원 토큰 요청 메서드
    private String getPortOneToken() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.portone.io/v2/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("api_key", "channel-key-e6a5937e-0f6b-422a-8748-c1f5f9c509f3");
        body.put("api_secret", "YOUR_API_SECRET"); // API Secret 설정 필요

        ResponseEntity<Map> response = restTemplate.postForEntity(
                url,
                new HttpEntity<>(body, headers),
                Map.class
        );

        return "Bearer " + response.getBody().get("access_token").toString();
    }
}
