package naero.naeroserver.product.repository;

import naero.naeroserver.entity.product.TblOption;
import naero.naeroserver.entity.product.TblProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<TblOption, Integer> {

    // 단일 상품 결제 시 해당 상품 옵션아이디 조회
    TblOption findByProduct(TblProduct product);

    // 옵션 아이디로 옵션 재고 조회
    Integer findOptionQuantityById(int optionId);
}
