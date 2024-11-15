package naero.naeroserver.cart.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.cart.dto.CartDTO;
import naero.naeroserver.cart.service.CartService;
import naero.naeroserver.common.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/*")
public class CartController {

    private static final Logger log = LoggerFactory.getLogger(CartController.class);
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Operation(summary = "회원 조회 요청", description = "회원 한명이 조회됩니다.", tags = { "MemberController" })
    @PostMapping("/cart/add")
    public ResponseEntity<ResponseDTO> insertCartItem(@ModelAttribute CartDTO cartDTO) {
        try {
            return ResponseEntity.ok().body(
                    new ResponseDTO(HttpStatus.OK, "장바구니 추가 성공", cartService.insertCartItem(cartDTO)));
        }
        catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null));
        }
    }


}
