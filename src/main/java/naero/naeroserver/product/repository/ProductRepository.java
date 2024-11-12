package naero.naeroserver.product.repository;

import naero.naeroserver.entity.product.TblProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<TblProduct, Integer> {
    @Query("SELECT p FROM TblProduct p WHERE p.productCheck = 'Y' ORDER BY p.productId DESC")
    List<TblProduct> findAllProductWithLimit(Pageable pageable);

    @Query("SELECT p FROM TblProduct p " +
            "JOIN p.smallCategory sc " +
            "JOIN sc.mediumCategory mc " +
            "JOIN mc.largeCategory lc " +
            "WHERE lc.id = :largeCategoryId AND p.productCheck = 'Y' " +
            "ORDER BY p.productId DESC")
    List<TblProduct> findByFoodProductWithLimit(@Param("largeCategoryId") Integer largeCategoryId, Pageable pageable);

    List<TblProduct> findByProductCheck(String status);

    Page<TblProduct> findByProductCheck(String status, Pageable paging);

    List<TblProduct> findByProductCheckAndSmallCategoryIdOrSmallCategoryId(String status, Integer smallCategoryId1, Integer smallCategoryId2);

    Page<TblProduct> findByProductCheckAndSmallCategoryIdOrSmallCategoryId(String status, Integer smallCategoryId1, Integer smallCategoryId2, Pageable paging);

    List<TblProduct> findByProductCheckAndSmallCategoryId(String status, Integer smallCategoryId);

    Page<TblProduct> findByProductCheckAndSmallCategoryId(String status, Integer smallCategoryId, Pageable paging);
}
