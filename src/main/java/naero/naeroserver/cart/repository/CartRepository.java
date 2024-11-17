package naero.naeroserver.cart.repository;

import naero.naeroserver.cart.dto.CartListDTO;
import naero.naeroserver.entity.cart.TblCart;
import naero.naeroserver.entity.product.TblOption;
import naero.naeroserver.entity.user.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<TblCart, Integer> {

    // 옵션번호와 회원번호로 장바구니 상품 조회
    TblCart findByOptionIdAndUserId(Integer optionId, Integer userId);

    // 회원번호로 장바구니 리스트 조회
    @Query("SELECT new naero.naeroserver.cart.dto.CartListDTO(" +
            "c.cartId, c.optionId, c.count," +
            "o.addPrice, o.optionQuantity," +
            "p.productId, p.productName, p.productPrice, p.productThumbnail, p.productImg, p.productCheck) " +
            "FROM TblCart c " +
            "LEFT JOIN TblOption o ON c.optionId = o.optionId " +
            "LEFT JOIN TblProduct p ON o.productId = p.productId " +
            "WHERE c.userId = :userId")
    List<CartListDTO> findAllCartOptionsAndProducts(@Param("userId") Integer userId);
}
