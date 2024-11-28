package naero.naeroserver.product.repository;

import naero.naeroserver.entity.product.TblOption;
import naero.naeroserver.entity.product.TblProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<TblOption, Integer> {

    // 단일 상품 결제 시 해당 상품 옵션아이디 조회
    List<TblOption> findByProductId(Integer productId);

    // 옵션 아이디로 옵션 재고 조회
    Integer findOptionQuantityByOptionId(int optionId);

    // 옵션 아이디로 옵션(상품) 조회
    TblOption findTblOptionByOptionId(Integer optionId);

    // 옵션아이디로 상품아이디 조회
    TblOption findProductIdByOptionId(int optionId);

    void deleteByProductId(int productId);
}
