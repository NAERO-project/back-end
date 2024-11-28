package naero.naeroserver.product.repository;

import naero.naeroserver.entity.product.TblCategoryMedium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryMediumRepository extends JpaRepository<TblCategoryMedium, Integer> {

    // 대분류번호로 중분류 조회
    List<TblCategoryMedium> findTblCategoryMediumByLargeCategoryId(int largeCategory);
}
