package naero.naeroserver.cart.service;

import naero.naeroserver.cart.controller.CartController;
import naero.naeroserver.cart.dto.CartDTO;
import naero.naeroserver.cart.repository.CartRepository;
import naero.naeroserver.entity.cart.TblCart;
import naero.naeroserver.entity.product.TblOption;
import naero.naeroserver.entity.user.TblUser;
import naero.naeroserver.member.repository.UserRepository;
import naero.naeroserver.product.controller.ProductController;
import naero.naeroserver.product.repository.OptionRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private static final Logger log = LoggerFactory.getLogger(CartService.class);
    private final CartRepository cartRepository;
    private final OptionRepository optionRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

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
            TblUser user = userRepository.findById(cartDTO.getUserId());
            TblCart cart = cartRepository.findByOptionAndUser(option, user);

            if (cart != null) { // 장바구니에 해당 상품이 이미 있을 경우 수량만 추가
                cart.setCount(cart.getCount() + cartDTO.getCount());
            } else {    // 장바구니에 해당 상품이 없을 경우 새로 추가
                TblCart newCart = modelMapper.map(cartDTO, TblCart.class);
                cartRepository.save(newCart);
            }

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("해당 상품에 대한 옵션이 존재하지 않습니다.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "장바구니에 상품 추가 완료";
    }
}
