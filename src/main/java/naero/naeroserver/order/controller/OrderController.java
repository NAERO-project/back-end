package naero.naeroserver.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.order.dto.OrderDTO;
import naero.naeroserver.order.dto.PayRequestDTO;
import naero.naeroserver.order.dto.PaymentDTO;
import naero.naeroserver.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/*")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "상품 주문 요청", description = "상품 주문이 진행됩니다.", tags = { "OrderController" })
    @PostMapping("/order")
    public ResponseEntity<ResponseDTO> insertOrder(@RequestBody PayRequestDTO payRequestDTO) {
        try {
            // PayRequestDTO에서 필요한 정보 추출
            OrderDTO orderDTO = payRequestDTO.getOrderDTO();
            PaymentDTO paymentDTO = payRequestDTO.getPaymentDTO();
            int productId = payRequestDTO.getProductId();

            // OrderService에 추출한 정보를 전달
            OrderDTO createdOrder = (OrderDTO) orderService.insertOrder(orderDTO, paymentDTO, productId);
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문 성공", createdOrder));
        } catch (Exception e) {
            log.error("주문 실패: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "주문 실패", null));
        }
    }

    @Operation(summary = "회원 주문 리스트 조회 요청", description = "해당 회원의 주문건에 대한 리스트 조회가 진행됩니다.", tags = { "OrderController" })
    @GetMapping("/order/{userId}")
    public ResponseEntity<ResponseDTO> getOrderList(@PathVariable String userId) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문리스트 조회 성공", orderService.selectOrderList(userId)));
    }

    @Operation(summary = "회원 주문 상세 리스트 조회 요청", description = "해당 회원의 주문건에 대한 상품 상세 리스트 조회가 진행됩니다.", tags = { "OrderController" })
    @GetMapping("/order/details/{orderId}")
    public ResponseEntity<ResponseDTO> getOrderDetailList(@PathVariable String orderId) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문 상세리스트 조회 성공", orderService.selectOrderDetailList(orderId)));
    }
}
