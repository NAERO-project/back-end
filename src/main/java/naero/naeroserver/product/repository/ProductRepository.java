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
    @Query("SELECT p FROM TblProduct p WHERE p.productCheck = 'Y' ORDER BY p.id DESC")
    List<TblProduct> findByProductCheck();

    @Query("SELECT p FROM TblProduct p WHERE p.productCheck = 'Y' ORDER BY p.id DESC")
    Page<TblProduct> findByProductCheck(Pageable paging);

    @Query("SELECT p FROM TblProduct p " +
            "JOIN p.smallCategory sc " +
            "JOIN sc.mediumCategory mc " +
            "JOIN mc.largeCategory lc " +
            "WHERE lc.id = :largeCategoryId AND mc.id = :mediumCategoryId AND p.productCheck = 'Y' " +
            "ORDER BY p.id DESC")
    List<TblProduct> findByProductCheckAndSmallCategoryId(@Param("largeCategoryId") Integer largeCategoryId,
                                                          @Param("mediumCategoryId") Integer mediumCategoryId);

    @Query("SELECT p FROM TblProduct p " +
            "JOIN p.smallCategory sc " +
            "JOIN sc.mediumCategory mc " +
            "JOIN mc.largeCategory lc " +
            "WHERE lc.id = :largeCategoryId AND mc.id = :mediumCategoryId AND p.productCheck = 'Y' " +
            "ORDER BY p.id DESC")
    Page<TblProduct> findByProductCheckAndSmallCategoryId(@Param("largeCategoryId") Integer largeCategoryId,
                                                          @Param("mediumCategoryId") Integer mediumCategoryId,
                                                          Pageable paging);

    //    상품 리스트 미리보기 조회
    @Query("SELECT p FROM TblProduct p WHERE p.productCheck = 'Y' ORDER BY p.id DESC")
    List<TblProduct> findAllProductWithLimit(Pageable pageable);

    @Query("SELECT p FROM TblProduct p " +
            "JOIN p.smallCategory sc " +
            "JOIN sc.mediumCategory mc " +
            "JOIN mc.largeCategory lc " +
            "WHERE lc.id = :largeCategoryId AND p.productCheck = 'Y' " +
            "ORDER BY p.id DESC")
    List<TblProduct> findByFoodProductWithLimit(@Param("largeCategoryId") Integer largeCategoryId, Pageable pageable);

    //    브랜드 전체 페이지 상품 조회
    @Query("SELECT p, pi.producerName, tu.id FROM TblProduct p " +
            "JOIN p.producer pi " +
            "JOIN pi.producer tu " +
            "WHERE tu.id = :id AND p.productCheck = 'Y' " +
            "ORDER BY p.id DESC")
    List<TblProduct> findByIdWithLimit(@Param("id") Integer id, Pageable pageable);










    /* 대분류 카테고리 정리 2024-11-14 */


    //    브랜드별 페이지 전체 상품 조회 (페이징)
    @Query("SELECT p, pi.producerName FROM TblProduct p " +
            "JOIN p.producer pi " +
            "JOIN pi.producer tu " +
            "WHERE tu.id = :id AND p.productCheck = 'Y' " +
            "ORDER BY p.id DESC")
    List<TblProduct> findByProductCheckAndId(@Param("id") Integer id);

    @Query("SELECT p FROM TblProduct p " +
            "JOIN p.producer pi " +
            "JOIN pi.producer tu " +
            "WHERE tu.id = :id AND p.productCheck = 'Y' " +
            "ORDER BY p.id DESC")
    Page<TblProduct> findByProductCheckAndId(@Param("id") Integer id, Pageable paging);

    @Query("SELECT p FROM TblProduct p " +
            "JOIN p.smallCategory sc " +
            "JOIN sc.mediumCategory mc " +
            "JOIN mc.largeCategory lc " +
            "JOIN p.producer pi " +
            "JOIN pi.producer tu " +
            "WHERE lc.id = :largeCategoryId AND tu.id = :id AND p.productCheck = 'Y' " +
            "ORDER BY p.id DESC")
    List<TblProduct> findByProductCheckAndSmallCategoryIdAndId(
            @Param("largeCategoryId") Integer largeCategoryId,
            @Param("id") Integer id);

    @Query("SELECT p FROM TblProduct p " +
            "JOIN p.smallCategory sc " +
            "JOIN sc.mediumCategory mc " +
            "JOIN mc.largeCategory lc " +
            "JOIN p.producer pi " +
            "JOIN pi.producer tu " +
            "WHERE lc.id = :largeCategoryId AND tu.id = :producerId AND p.productCheck = 'Y' " +
            "ORDER BY p.id DESC")
    Page<TblProduct> findByProductCheckAndSmallCategoryIdAndId(
            @Param("largeCategoryId") Integer largeCategoryId,
            @Param("producerId") Integer producerId,
            Pageable paging);


}
