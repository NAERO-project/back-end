package naero.naeroserver.cart.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import naero.naeroserver.cart.dto.CartDTO;
import naero.naeroserver.cart.service.CartService;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.entity.order.TblOrder;
import naero.naeroserver.member.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/*")
public class CartController {

    private static final Logger log = LoggerFactory.getLogger(CartController.class);
    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @Operation(summary = "장바구니 등록 요청", description = "장바구니에 상품 정보가 등록됩니다.", tags = { "CartController" })
    @PostMapping("/cart/insert")
    public ResponseEntity<ResponseDTO> insertCartItem(@RequestParam(name = "username") String username, @RequestBody CartDTO cartDTO) {

        int userId = 0;
        if (username != null) {
            userId = userService.getUserIdFromUserName(username);
            cartDTO.setUserId(userId);
        }

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

    @Operation(summary = "장바구니 조회 요청", description = "장바구니 리스트 조회가 진행됩니다.", tags = { "CartController" })
    @GetMapping("/cart/{username}")
    public ResponseEntity<ResponseDTO> getCartList(@PathVariable String username) {

        int userId = 0;
        if (username != null) {
            userId = userService.getUserIdFromUserName(username);
        }

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "장바구니 조회 성공", cartService.getCartList(userId)));
    }

    @Operation(summary = "장바구니 수정 요청", description = "장바구니 상품 수정이 진행됩니다.", tags = { "CartController" })
    @PutMapping("/cart/update")
    public ResponseEntity<ResponseDTO> updateCartItem(@RequestBody CartDTO cartItem) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "장바구니 수정 성공", cartService.updateCartItem(cartItem)));
    }

    @Operation(summary = "장바구니 상품 삭제 요청", description = "장바구니에서 여러 상품을 삭제합니다.", tags = { "CartController" })
    @DeleteMapping("/cart/delete")
    public ResponseEntity<ResponseDTO> deleteCartItems(@RequestBody List<String> cartIds) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "장바구니 삭제 완료", cartService.deleteCartItems(cartIds)));
    }

}
