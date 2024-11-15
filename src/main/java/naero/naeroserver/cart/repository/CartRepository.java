package naero.naeroserver.cart.repository;

import naero.naeroserver.entity.cart.TblCart;
import naero.naeroserver.entity.product.TblOption;
import naero.naeroserver.entity.user.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<TblCart, Integer> {

    // 옵션번호와 회원번호로 장바구니 상품 조회
    TblCart findByOptionAndUser(TblOption option, TblUser user);
}
