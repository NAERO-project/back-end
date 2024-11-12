package naero.naeroserver.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.order.dto.PaymentDTO;
import naero.naeroserver.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ResponseDTO> insertOrder(@RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문 성공", orderService.insertOrder(paymentDTO)));
    }
}
