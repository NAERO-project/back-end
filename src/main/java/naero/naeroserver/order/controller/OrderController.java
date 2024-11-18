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

    @Operation(summary = "단일 상품 결제 요청", description = "상품 결제가 진행됩니다.", tags = { "OrderController" })
    @PostMapping("/order")
    public ResponseEntity<ResponseDTO> insertOrder(@RequestBody PayRequestDTO payRequestDTO) {
        try {
            // PayRequestDTO에서 필요한 정보 추출
            OrderDTO orderDTO = payRequestDTO.getOrderDTO();
            PaymentDTO paymentDTO = payRequestDTO.getPaymentDTO();
            int optionId = payRequestDTO.getOptionId();

            // OrderService에 추출한 정보를 전달
            OrderDTO createdOrder = (OrderDTO) orderService.insertOrder(orderDTO, paymentDTO, optionId);
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문 성공", createdOrder));
        }  catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null));
        }
    }

    @Operation(summary = "결제 취소 요청", description = "결제를 취소합니다.", tags = { "OrderController" })
    @PutMapping("/order/cancel/{paymentId}")
    public ResponseEntity<ResponseDTO> cancelPayment(@PathVariable String paymentId) {
        try {
            orderService.cancelPayment(paymentId);
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "결제 취소 성공", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null));
        }
    }

    @Operation(summary = "회원 주문 리스트 조회 요청",
            description = "해당 회원의 주문건에 대한 리스트 조회가 진행됩니다.",
            tags = { "OrderController" })
    @GetMapping("/order/{userId}")
    public ResponseEntity<ResponseDTO> getOrderList(@PathVariable String userId) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "주문 리스트 조회 성공", orderService.selectOrderList(userId)));
    }

    @Operation(summary = "회원 주문 상세 조회 요청",
            description = "해당 회원의 주문건에 대한 상세 정보 조회가 진행됩니다.",
            tags = { "OrderController" })
    @GetMapping("/order/details/{orderId}")
    public ResponseEntity<ResponseDTO> getOrderDetail(@PathVariable String orderId) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "주문 상세정보 조회 성공", orderService.getOrderDetail(orderId)));
    }

    @Operation(summary = "회원 주문 상품 리스트 조회 요청",
            description = "해당 회원의 주문건에 대한 상품 리스트 조회가 진행됩니다.",
            tags = { "OrderController" })
    @GetMapping("/order/product/{orderId}")
    public ResponseEntity<ResponseDTO> getOrderDetailList(@PathVariable String orderId) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "주문 상품 리스트 조회 성공", orderService.getOrderDetailList(orderId)));
    }

    @Operation(summary = "해당 판매자의 주문 리스트 조회 요청",
            description = "해당 판매자의 주문건에 대한 리스트 조회가 진행됩니다.",
            tags = { "OrderController" })
    @GetMapping("/order/seller/{producerId}")
    public ResponseEntity<ResponseDTO> getOrderListByProducer(@PathVariable String producerId) {
        return ResponseEntity.ok()
                .body(
                        new ResponseDTO(
                                HttpStatus.OK,
                                "해당 판매자의 주문리스트 조회 성공",
                                orderService.getOrderListByProducer(producerId)));
    }

    @Operation(summary = "관리자용 전체 주문 리스트 조회 요청",
            description = "전체 주문건에 대한 리스트 조회가 진행됩니다.",
            tags = { "OrderController" })
    @GetMapping("/order/admin")
    public ResponseEntity<ResponseDTO> getAllOrderList() {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "전체 주문리스트 조회 성공", orderService.getAllOrderList()));
    }
}
