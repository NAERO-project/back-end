package naero.naeroserver.product.repository;

import naero.naeroserver.entity.product.TblCategorySmall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorySmallRepository extends JpaRepository<TblCategorySmall, Integer> {

    // 중분류 번호로 소분류 조회
    List<TblCategorySmall> findTblCategorySmallByMediumCategoryId(int mediumCategory);
}
