package naero.naeroserver.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import naero.naeroserver.cart.dto.CartDTO;
import naero.naeroserver.cart.repository.CartRepository;
import naero.naeroserver.common.Criteria;
import naero.naeroserver.common.PageDTO;
import naero.naeroserver.common.PagingResponseDTO;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.entity.cart.TblCart;
import naero.naeroserver.member.service.UserService;
import naero.naeroserver.order.dto.OrderDTO;
import naero.naeroserver.order.dto.OrderPageDTO;
import naero.naeroserver.order.dto.PayRequestDTO;
import naero.naeroserver.order.dto.PaymentDTO;
import naero.naeroserver.order.service.OrderService;
import naero.naeroserver.order.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/*")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final HttpSession httpSession;
    private final CartRepository cartRepository;
    private final UserService userService;
    private final PaymentService paymentService;

    @Autowired
    public OrderController(OrderService orderService, HttpSession httpSession, CartRepository cartRepository, UserService userService, PaymentService paymentService) {
        this.orderService = orderService;
        this.httpSession = httpSession;
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.paymentService = paymentService;
    }

    @Operation(summary = "상품 주문 요청", description = "상품 주문 화면을 조회합니다.", tags = { "OrderController" })
    @PostMapping("/order/start")
    public ResponseEntity<ResponseDTO> startCartOrder(@RequestBody List<CartDTO> cartDTOList,
                                                      @RequestParam("username") String username, HttpSession httpSession) {

        // 단일 상품 결제 시 cartDTO에 userId 정보가 없을 경우 대비 돌아가는 로직
        if (username != null) {
            int userId = userService.getUserIdFromUserName(username);
            cartDTOList.forEach((c) -> c.setUserId(userId));
        }

        OrderPageDTO tempOrder = orderService.startOrder(cartDTOList);

        // 추후 결제 완료된 장바구니 상품 삭제하기 위해 세션에 저장
        List<Integer> cartIds = new ArrayList<>();
        for (CartDTO cartItem : cartDTOList) {
            if (cartItem.getCartId() == null) break;
            cartIds.add(cartItem.getCartId());
        }

        httpSession.setAttribute("tempOrder", tempOrder);

        if (!cartIds.isEmpty()) {
            httpSession.setAttribute("cartIds", cartIds);
        }

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문 정보 생성 성공", tempOrder));
    }

    @Operation(summary = "상품 결제 요청", description = "상품 결제가 진행됩니다.", tags = { "OrderController" })
    @PostMapping("/order/process")
    public ResponseEntity<ResponseDTO> insertOrder(@RequestBody PayRequestDTO payRequestDTO, @RequestParam("username") String username) {
        try {
            // 회원 username으로 userId 조회 후 세팅
            int userId = userService.getUserIdFromUserName(username);
            payRequestDTO.getOrderDTO().setUserId(userId);
            payRequestDTO.getPaymentDTO().setUserId(userId);

            // PayRequestDTO에서 필요한 정보 추출
            OrderDTO orderDTO = payRequestDTO.getOrderDTO();
            PaymentDTO paymentDTO = payRequestDTO.getPaymentDTO();
            Map<Integer, Integer> optionIdAndCount = payRequestDTO.getOptionIds();

            System.out.println("===================================");
            log.info("Received PayRequestDTO: {}", payRequestDTO);
            log.info("orderDTO {}", orderDTO);
            log.info("paymentDTO {}", paymentDTO);
            log.info("optionIdAndCount {}", optionIdAndCount);
            System.out.println("===================================");

            // OrderService에 추출한 정보를 전달
            OrderDTO createdOrder = (OrderDTO) orderService.insertOrder(orderDTO, paymentDTO, optionIdAndCount);
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "주문 성공", createdOrder));
        }  catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage(), e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getMessage()));
        }
    }

    @Operation(summary = "결제 취소 요청", description = "결제를 취소합니다.", tags = { "OrderController" })
    @PutMapping("/order/cancel/{orderId}")
    public ResponseEntity<ResponseDTO> cancelPayment(@PathVariable String orderId) {
        try {
            orderService.cancelPayment(orderId);
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "결제 취소 성공", "결제 취소 성공"));
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
    @GetMapping("/order/{username}")
    public ResponseEntity<ResponseDTO> getOrderList(@PathVariable String username,
                                                    @RequestParam(name = "offset", defaultValue = "1") String offset) {

        // 회원 username으로 userId 조회 후 세팅
        int userId = userService.getUserIdFromUserName(username);

        // 페이징 처리
        int total = orderService.selectOrderListPage(userId);

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(orderService.selectOrderList(userId, cri));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "주문 리스트 조회 성공", pagingResponseDTO));
    }

    @Operation(summary = "회원 주문 상세 조회 요청",
            description = "해당 회원의 주문건에 대한 상세 정보 조회가 진행됩니다.",
            tags = { "OrderController" })
    @GetMapping("/order/details/{orderId}")
    public ResponseEntity<ResponseDTO> getOrderDetail(@PathVariable String orderId) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "주문 상세정보 조회 성공", orderService.getOrderDetail(orderId)));
    }

    @Operation(summary = "회원 결제 상세 조회 요청",
            description = "해당 회원의 결제에 대한 상세 정보 조회가 진행됩니다.",
            tags = { "OrderController" })
    @GetMapping("/payment/details/{orderId}")
    public ResponseEntity<ResponseDTO> getPaymentDetail(@PathVariable String orderId) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "결제 상세정보 조회 성공", paymentService.getPaymentDetail(orderId)));
    }

    @Operation(summary = "회원 주문 상품 리스트 조회 요청",
            description = "해당 회원의 주문건에 대한 상품 리스트 조회가 진행됩니다.",
            tags = { "OrderController" })
    @GetMapping("/order/product/{orderId}")
    public ResponseEntity<ResponseDTO> getOrderDetailList(@PathVariable String orderId) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "주문 상품 리스트 조회 성공", orderService.getOrderDetailList(orderId)));
    }

    @Operation(summary = "판매자에 해당 되는 회원 주문 상품 리스트 조회 요청",
            description = "판매자에 해당 되는 회원의 주문건에 대한 상품 리스트 조회가 진행됩니다.",
            tags = { "OrderController" })
    @GetMapping("/order/seller/product/{orderId}")
    public ResponseEntity<ResponseDTO> getProducerOrderDetailList(@PathVariable String orderId,
                                                                  @RequestParam("producerUsername") String producerUsername) {

        int producerId = userService.getUserIdFromUserName(producerUsername);

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "주문 상품 리스트 조회 성공",
                        orderService.getProducerOrderDetailList(orderId, producerId)));
    }

    @Operation(summary = "해당 판매자의 주문 리스트 조회 요청",
            description = "해당 판매자의 주문건에 대한 리스트 조회가 진행됩니다.",
            tags = { "OrderController" })
    @GetMapping("/order/seller/{producerUsername}")
    public ResponseEntity<ResponseDTO> getOrderListByProducer(@RequestParam(name = "offset", defaultValue = "1") String offset,
                                                              @PathVariable String producerUsername) {

        int producerId = userService.getUserIdFromUserName(producerUsername);

        int total = orderService.getOrderListByProducer(producerId);

        Criteria cri = new Criteria(Integer.valueOf(offset), 15);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(orderService.getOrderListByProducerPaging(producerId, cri));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok()
                .body(
                        new ResponseDTO(
                                HttpStatus.OK,
                                "해당 판매자의 주문리스트 조회 성공",
                                pagingResponseDTO));
    }

    @Operation(summary = "관리자용 전체 주문 리스트 조회 요청",
            description = "전체 주문건에 대한 리스트 조회가 진행됩니다.",
            tags = { "OrderController" })
    @GetMapping("/order/admin")
    public ResponseEntity<ResponseDTO> getAllOrderList() {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "전체 주문리스트 조회 성공", orderService.getAllOrderList()));
    }

    // 불필요
//    @Operation(summary = "상품 결제 완료", description = "상품 결제 완료 후 장바구니 삭제 및 세션 정리가 진행됩니다.",
//            tags = { "OrderController" })
//    @GetMapping("/order/complete")
//    public void deleteSession(HttpSession httpSession) {
//        if (httpSession.getAttribute("cartIds") != null) {
//            List<Integer> cartIds = (List<Integer>) httpSession.getAttribute("cartIds");
//            for(Integer cartId : cartIds){
//                TblCart cart = cartRepository.findById(cartId)
//                        .orElseThrow(() -> new NoSuchElementException("삭제할 장바구니를 찾을 수 없습니다."));
//                cartRepository.delete(cart);
//            }
//        }
//
//        // 세션에서 임시 주문 정보 삭제
//        httpSession.removeAttribute("tempOrder");
//        httpSession.removeAttribute("cartIds");
//    }
}
