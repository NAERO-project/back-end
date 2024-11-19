package naero.naeroserver.product.repository;

import naero.naeroserver.entity.product.TblProduct;
import naero.naeroserver.product.dto.ProductOptionDTO;
import naero.naeroserver.product.dto.ProductSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ProductRepository extends JpaRepository<TblProduct, Integer> {
    //    상품 리스트 전체 조회 (페이징)
    @Query("SELECT p FROM TblProduct p WHERE p.productCheck = 'Y' ORDER BY p.productCreateAt DESC")
    List<TblProduct> findByProductCheck();

    @Query("SELECT p FROM TblProduct p WHERE p.productCheck = 'Y' ORDER BY p.productCreateAt DESC")
    Page<TblProduct> findPagedByProductCheck(Pageable paging);

    @Query("SELECT p " +
            "FROM TblProduct p " +
            "JOIN TblCategorySmall cs ON p.smallCategory = cs.smallCategoryId " +
            "JOIN TblCategoryMedium cm ON cs.mediumCategoryId = cm.mediumCategoryId " +
            "AND cm.mediumCategoryId = :mediumId " +
            "AND p.productCheck = 'Y' " +
            "ORDER BY p.productCreateAt desc ")
    List<TblProduct> findByProductCheckAndSmallCategory(@Param("mediumId") Integer mediumId);

    @Query("SELECT p " +
            "FROM TblProduct p " +
            "JOIN TblCategorySmall cs ON p.smallCategory = cs.smallCategoryId " +
            "JOIN TblCategoryMedium cm ON cs.mediumCategoryId = cm.mediumCategoryId " +
            "AND cm.mediumCategoryId = :mediumId " +
            "AND p.productCheck = 'Y' " +
            "ORDER BY p.productCreateAt desc ")
    Page<TblProduct> findPagedProductCheckAndSmallCategory(@Param("mediumId") Integer mediumId,
                                                          Pageable paging);

    //    상품 리스트 미리보기 조회
    @Query("SELECT p FROM TblProduct p WHERE p.productCheck = 'Y' ORDER BY p.productCreateAt DESC")
    List<TblProduct> findAllProductWithLimit(Pageable pageable);

    @Query("SELECT p FROM TblProduct p, TblCategorySmall sc, TblCategoryMedium mc " +
            "WHERE p.smallCategory = sc.smallCategoryId " +
            "AND sc.mediumCategoryId = mc.mediumCategoryId AND mc.mediumCategoryId = :mediumId " +
            "AND p.productCheck = 'Y' " +
            "ORDER BY p.productCreateAt DESC")
    List<TblProduct> findByFoodProductWithLimit(@Param("mediumId") Integer mediumId, Pageable pageable);

    //    브랜드 전체 페이지 상품 조회 (미리보기)
    @Query("SELECT p " +
            "FROM TblProduct p " +
            "WHERE p.producerId = :producerId " +
            "AND p.productCheck = 'Y' " +
            "ORDER BY p.productCreateAt desc ")
    List<TblProduct> findByProducerIdWithLimit(@Param("producerId") Integer producerId,
                                               Pageable pageable);

    //    브랜드별 페이지 전체 상품 조회 (페이징)
    @Query("SELECT p " +
            "FROM TblProduct p " +
            "WHERE p.producerId = :producerId " +
            "AND p.productCheck = 'Y' " +
            "ORDER BY p.productCreateAt desc ")
    List<TblProduct> findByProductCheckAndId(@Param("producerId") Integer producerId);

    @Query("SELECT p " +
            "FROM TblProduct p " +
            "WHERE p.producerId = :producerId " +
            "AND p.productCheck = 'Y' " +
            "ORDER BY p.productCreateAt desc ")
    Page<TblProduct> findByPageProductCheckAndId(@Param("producerId") Integer producerId,
                                                     Pageable paging);

    @Query("SELECT p " +
            "FROM TblProduct p " +
            "JOIN TblCategorySmall cs ON p.smallCategory = cs.smallCategoryId " +
            "JOIN TblCategoryMedium cm ON cs.mediumCategoryId = cm.mediumCategoryId " +
            "WHERE p.producerId = :producerId " +
            "AND cm.mediumCategoryId = :mediumId " +
            "AND p.productCheck = 'Y' " +
            "ORDER BY p.productCreateAt desc ")
    List<TblProduct> findByProductCheckAndSmallCategoryIdAndProducerId(@Param("producerId") Integer producerId,
                                                                       @Param("mediumId") Integer mediumId);

    @Query("SELECT p " +
            "FROM TblProduct p " +
            "JOIN TblCategorySmall cs ON p.smallCategory = cs.smallCategoryId " +
            "JOIN TblCategoryMedium cm ON cs.mediumCategoryId = cm.mediumCategoryId " +
            "WHERE p.producerId = :producerId " +
            "AND cm.mediumCategoryId = :mediumId " +
            "AND p.productCheck = 'Y' " +
            "ORDER BY p.productCreateAt desc ")
    Page<TblProduct> findByProductCheckAndSmallCategoryIdAndProducerId(@Param("producerId") Integer producerId,
                                                                               @Param("mediumId") Integer mediumId,
                                                                               Pageable paging);


    @Query("SELECT new naero.naeroserver.product.dto.ProductOptionDTO(p, op.addPrice, op.optionDesc, op.optionQuantity) " +
            "FROM TblProduct p " +
            "JOIN TblOption op ON p.productId = op.productId " +
            "WHERE p.productId = :productId")
    List<ProductOptionDTO> findByIdAndOption(@Param("productId") Integer productId);

    List<TblProduct> findByProductNameContaining(String search);
}
