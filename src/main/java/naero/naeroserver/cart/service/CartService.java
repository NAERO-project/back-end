package naero.naeroserver.cart.service;

import naero.naeroserver.cart.dto.CartListDTO;
import naero.naeroserver.cart.dto.CartDTO;
import naero.naeroserver.cart.repository.CartRepository;
import naero.naeroserver.entity.cart.TblCart;
import naero.naeroserver.entity.order.TblOrder;
import naero.naeroserver.entity.product.TblOption;
import naero.naeroserver.entity.user.TblUser;
import naero.naeroserver.member.repository.UserRepository;
import naero.naeroserver.product.repository.OptionRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    private static final Logger log = LoggerFactory.getLogger(CartService.class);
    private final CartRepository cartRepository;
    private final OptionRepository optionRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Value("${image.image-url}")
    private String IMG_URL;

    public CartService(CartRepository cartRepository, OptionRepository optionRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.optionRepository = optionRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    // 장바구니에 상품 추가
    @Transactional
    public Object insertCartItem(CartDTO cartDTO) {
        log.info("[CartService] insertCartItem() Start");
        log.info("[CartService] cartDTO : {}", cartDTO);

        try {
            // 상품 재고 확인
            TblOption option = optionRepository.findById(cartDTO.getOptionId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 상품에 대한 옵션이 존재하지 않습니다."));

            if (option.getOptionQuantity() < cartDTO.getCount()) throw new IllegalStateException("상품 재고가 부족합니다.");

            // 장바구니에 해당 상품이 이미 있는지 확인
            TblUser user = userRepository.findTblUserByUserId(cartDTO.getUserId());
            naero.naeroserver.entity.cart.TblCart cart = cartRepository.findByOptionIdAndUserId(option.getOptionId(), user.getUserId());

            if (cart != null) { // 장바구니에 해당 상품이 이미 있을 경우 수량만 추가
                cart.setCount(cart.getCount() + cartDTO.getCount());
            } else {    // 장바구니에 해당 상품이 없을 경우 새로 추가
                naero.naeroserver.entity.cart.TblCart newCart = modelMapper.map(cartDTO, naero.naeroserver.entity.cart.TblCart.class);
                cartRepository.save(newCart);
            }

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("해당 상품에 대한 옵션이 존재하지 않습니다.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "장바구니에 상품 추가 완료";
    }

    // 해당 회원의 장바구니 리스트 조회
    public Object getCartList(String userId) {

        List<CartListDTO> cartList = cartRepository.findAllCartOptionsAndProducts(Integer.valueOf(userId));

        for(int i = 0 ; i < cartList.size() ; i++) {
            cartList.get(i).setProductImg(IMG_URL + cartList.get(i).getProductImg());
            cartList.get(i).setProductThumbnail(IMG_URL + cartList.get(i).getProductThumbnail());
        }
        return cartList;
    }

    // 장바구니 상품 수정
    @Transactional
    public Object updateCartItem(CartDTO cartItem) {
        try {
            TblCart updateCartItem = cartRepository.findById(cartItem.getCartId()).get();
            updateCartItem.setCartId(cartItem.getCartId());
            updateCartItem.setOptionId(cartItem.getOptionId());
            updateCartItem.setCount(cartItem.getCount());
            updateCartItem.setPrice(cartItem.getPrice());
            updateCartItem.setUserId(cartItem.getUserId());

        } catch (Exception e) {
            log.error("[deleteCartItems] Exception!!", e);
            return "장바구니 상품 수정 실패";
        }

        return "장바구니 상품 수정 완료";
    }

    // 장바구니 상품 삭제
    @Transactional
    public Object deleteCartItems(List<String> cartIds) {   // 체크된 카트상품 배열로 전부 담아와서 한 번에 삭제
        int result = 0;

        try {
            for (String cartId : cartIds) {
                cartRepository.deleteById(Integer.valueOf(cartId));
                result++;
            }
        } catch (Exception e) {
            log.error("[deleteCartItems] Exception!!", e);
            return "장바구니 상품 삭제 실패";
        }

        return (result > 0) ? "장바구니 상품 삭제 완료" : "장바구니 상품 삭제 실패";
    }

}
