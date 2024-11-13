package naero.naeroserver.product.repository;

import naero.naeroserver.entity.product.TblProduct;
import naero.naeroserver.entity.user.TblUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<TblProduct, Integer> {
    //    상품 리스트 전체 조회 (페이징)
    @Query("SELECT p FROM TblProduct p WHERE p.productCheck = 'Y' ORDER BY p.productId DESC")
    List<TblProduct> findByProductCheck();

    @Query("SELECT p FROM TblProduct p WHERE p.productCheck = 'Y' ORDER BY p.productId DESC")
    Page<TblProduct> findByProductCheck(Pageable paging);

    @Query("SELECT p FROM TblProduct p " +
            "JOIN p.smallCategory sc " +
            "JOIN sc.mediumCategory mc " +
            "JOIN mc.largeCategory lc " +
            "WHERE lc.id = :largeCategoryId AND p.productCheck = 'Y' " +
            "ORDER BY p.productId DESC")
    List<TblProduct> findByProductCheckAndSmallCategoryId(@Param("largeCategoryId") Integer largeCategoryId);

    @Query("SELECT p FROM TblProduct p " +
            "JOIN p.smallCategory sc " +
            "JOIN sc.mediumCategory mc " +
            "JOIN mc.largeCategory lc " +
            "WHERE lc.id = :largeCategoryId AND p.productCheck = 'Y' " +
            "ORDER BY p.productId DESC")
    Page<TblProduct> findByProductCheckAndSmallCategoryId(@Param("largeCategoryId") Integer largeCategoryId, Pageable paging);

    //    상품 리스트 미리보기 조회
    @Query("SELECT p FROM TblProduct p WHERE p.productCheck = 'Y' ORDER BY p.productId DESC")
    List<TblProduct> findAllProductWithLimit(Pageable pageable);

    @Query("SELECT p FROM TblProduct p " +
            "JOIN p.smallCategory sc " +
            "JOIN sc.mediumCategory mc " +
            "JOIN mc.largeCategory lc " +
            "WHERE lc.id = :largeCategoryId AND p.productCheck = 'Y' " +
            "ORDER BY p.productId DESC")
    List<TblProduct> findByFoodProductWithLimit(@Param("largeCategoryId") Integer largeCategoryId, Pageable pageable);

    //    브랜드 전체 페이지 상품 조회
    @Query("SELECT p, pi.producerName, tu.id FROM TblProduct p " +
            "JOIN p.producer pi " +
            "JOIN pi.producer tu " +
            "WHERE tu.id = :id AND p.productCheck = 'Y' " +
            "ORDER BY p.productId DESC")
    List<TblProduct> findByProductIdWithLimit(@Param("id") Integer producerId, Pageable pageable);

    //    브랜드별 페이지 전체 상품 조회 (페이징)
    @Query("SELECT p, pi.producerName FROM TblProduct p " +
            "JOIN p.producer pi " +
            "JOIN pi.producer tu " +
            "WHERE tu.id = :id AND p.productCheck = 'Y' " +
            "ORDER BY p.productId DESC")
    List<TblProduct> findByProductCheckAndProducerId(@Param("id") Integer producerId);

    @Query("SELECT p FROM TblProduct p " +
            "JOIN p.producer pi " +
            "JOIN pi.producer tu " +
            "WHERE tu.id = :id AND p.productCheck = 'Y' " +
            "ORDER BY p.productId DESC")
    Page<TblProduct> findByProductCheckAndProducerId(@Param("id") Integer producerId, Pageable paging);

    @Query("SELECT p FROM TblProduct p " +
            "JOIN p.smallCategory sc " +
            "JOIN sc.mediumCategory mc " +
            "JOIN mc.largeCategory lc " +
            "JOIN p.producer pi " +
            "JOIN pi.producer tu " +
            "WHERE lc.id = :largeCategoryId AND tu.id = :id AND p.productCheck = 'Y' " +
            "ORDER BY p.productId DESC")
    List<TblProduct> findByProductCheckAndSmallCategoryIdAndProducerId(
            @Param("largeCategoryId") Integer largeCategoryId,
            @Param("id") Integer producerId);

    @Query("SELECT p FROM TblProduct p " +
            "JOIN p.smallCategory sc " +
            "JOIN sc.mediumCategory mc " +
            "JOIN mc.largeCategory lc " +
            "JOIN p.producer pi " +
            "JOIN pi.producer tu " +
            "WHERE lc.id = :largeCategoryId AND tu.id = :id AND p.productCheck = 'Y' " +
            "ORDER BY p.productId DESC")
    Page<TblProduct> findByProductCheckAndSmallCategoryIdAndProducerId(
            @Param("largeCategoryId") Integer largeCategoryId,
            @Param("id") Integer producerId,
            Pageable paging);

}
