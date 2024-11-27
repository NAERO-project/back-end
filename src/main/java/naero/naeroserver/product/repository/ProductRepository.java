package naero.naeroserver.product.repository;

import naero.naeroserver.entity.product.TblCategoryLarge;
import naero.naeroserver.entity.product.TblCategoryMedium;
import naero.naeroserver.entity.product.TblProduct;
import naero.naeroserver.entity.user.TblProducer;
import naero.naeroserver.product.dto.ProducerProductDTO;
import naero.naeroserver.product.dto.ProductOptionDTO;
import naero.naeroserver.order.dto.OrderPageProductDTO;
import naero.naeroserver.product.dto.ProductProducerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductRepository extends JpaRepository<TblProduct, Integer> {
    //    상품 리스트 전체 조회 (페이징)
    @Query("SELECT p FROM TblProduct p WHERE p.productCheck = 'Y' ORDER BY p.productCreateAt DESC")
    List<TblProduct> findByProductCheck();

    @Query("SELECT p FROM TblProduct p WHERE p.productCheck = 'Y' ORDER BY p.productCreateAt DESC")
    Page<TblProduct> findPagedByProductCheck(Pageable paging);

    @Query("SELECT p " +
            "FROM TblProduct p " +
            "JOIN TblCategorySmall cs ON p.smallCategory.smallCategoryId = cs.smallCategoryId " +
            "JOIN TblCategoryMedium cm ON cs.mediumCategoryId = cm.mediumCategoryId " +
            "JOIN TblCategoryLarge cl ON cm.largeCategoryId = cl.largeCategoryId " +
//            "AND cm.mediumCategoryId = :mediumId " +
            "AND cl.largeCategoryId = :largeId " +
            "AND p.productCheck = 'Y' " +
            "ORDER BY p.productCreateAt desc ")
    List<TblProduct> findByProductCheckAndSmallCategory(@Param("largeId") Integer largeId);

    @Query("SELECT p " +
            "FROM TblProduct p " +
            "JOIN TblCategorySmall cs ON p.smallCategory.smallCategoryId = cs.smallCategoryId " +
            "JOIN TblCategoryMedium cm ON cs.mediumCategoryId = cm.mediumCategoryId " +
            "JOIN TblCategoryLarge cl ON cm.largeCategoryId = cl.largeCategoryId " +
            "AND cm.mediumCategoryId = :mediumId " +
            "AND cl.largeCategoryId = :largeId " +
            "AND p.productCheck = 'Y' " +
            "ORDER BY p.productCreateAt desc ")
    List<TblProduct> findByProductCheckAndSmallCategory(@Param("largeId") Integer largeId,
                                                        @Param("mediumId") Integer mediumId);

    @Query("SELECT p " +
            "FROM TblProduct p " +
            "JOIN TblCategorySmall cs ON p.smallCategory.smallCategoryId = cs.smallCategoryId " +
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
            "WHERE p.smallCategory.smallCategoryId = sc.smallCategoryId " +
            "AND sc.mediumCategoryId = mc.mediumCategoryId AND mc.mediumCategoryId = :mediumId " +
            "AND p.productCheck = 'Y' " +
            "ORDER BY p.productCreateAt DESC")
    List<TblProduct> findByFoodProductWithLimit(@Param("mediumId") Integer mediumId, Pageable pageable);

    //    브랜드 전체 페이지 상품 조회 (미리보기)
    @Query("SELECT p " +
            "FROM TblProduct p " +
            "JOIN TblProducer pi ON p.producerId = pi.producerId " +
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

    // 판매자 페이지 상품 조회
    @Query("SELECT new naero.naeroserver.product.dto.ProductOptionDTO(p, o.addPrice, o.optionDesc, o.optionQuantity) " +
            "FROM TblProduct p " +
            "LEFT JOIN TblOption o ON p.productId = o.productId " +
            "WHERE p.producerId = :producerId " +
            "AND p.productCheck = 'Y' " +
            "ORDER BY p.productCreateAt desc ")
    Page<ProductOptionDTO> findProductListByProducer(@Param("producerId") Integer producerId,
                                                 Pageable paging);

    @Query("SELECT p " +
            "FROM TblProduct p " +
            "JOIN TblCategorySmall cs ON p.smallCategory.smallCategoryId = cs.smallCategoryId " +
            "JOIN TblCategoryMedium cm ON cs.mediumCategoryId = cm.mediumCategoryId " +
            "WHERE p.producerId = :producerId " +
            "AND cm.mediumCategoryId = :mediumId " +
            "AND p.productCheck = 'Y' " +
            "ORDER BY p.productCreateAt desc ")
    List<TblProduct> findByProductCheckAndSmallCategoryIdAndProducerId(@Param("producerId") Integer producerId,
                                                                       @Param("mediumId") Integer mediumId);

    @Query("SELECT p " +
            "FROM TblProduct p " +
            "JOIN TblCategorySmall cs ON p.smallCategory.smallCategoryId = cs.smallCategoryId " +
            "JOIN TblCategoryMedium cm ON cs.mediumCategoryId = cm.mediumCategoryId " +
            "WHERE p.producerId = :producerId " +
            "AND cm.mediumCategoryId = :mediumId " +
            "AND p.productCheck = 'Y' " +
            "ORDER BY p.productCreateAt desc ")
    Page<TblProduct> findByProductCheckAndSmallCategoryIdAndProducerId(@Param("producerId") Integer producerId,
                                                                               @Param("mediumId") Integer mediumId,
                                                                               Pageable paging);


//    @Query("SELECT new naero.naeroserver.product.dto.ProductOptionDTO(p, op.addPrice, op.optionDesc, op.optionQuantity) " +
//            "FROM TblProduct p " +
//            "JOIN TblOption op ON p.productId = op.productId " +
//            "WHERE p.productId = :productId")
//    ProductOptionDTO findByIdAndOption(@Param("productId") Integer productId);

    @Query("SELECT p " +
            "FROM TblProduct p " +
            "WHERE p.productId = :productId")
    TblProduct findByIdAndOption(@Param("productId") Integer productId);

//    @Query("SELECT p, pi.producer FROM TblProduct p " +
//            "JOIN TblProducer pi ON p.productId = pi.id " +
//            "WHERE pi.id = :producerId AND p.productCheck = 'Y' " +
//            "ORDER BY p.productId DESC")
//    List<TblProduct> findByIdWithLimit(@Param("producerId") Integer producerId, Pageable pageable);

    // 주문 페이지 내 주문할 상품 정보 전달용
    @Query("SELECT new naero.naeroserver.order.dto.OrderPageProductDTO(o.optionId, o.productId, p.producerId, " +
            "pd.producerName, pd.deliveryFee, pd.deliveryCrit, p.productName, " +
            "p.productImg, p.productThumbnail, p.productPrice, o.addPrice, o.optionDesc, sc.smallCategoryId, sc.smallCategoryName) " +
            "FROM TblProduct p " +
            "JOIN TblProducer pd ON p.producerId = pd.producerId " +
            "JOIN TblOption o ON p.productId = o.productId " +
            "JOIN TblCategorySmall sc ON p.smallCategory.smallCategoryId = sc.smallCategoryId " +
            "WHERE o.optionId = :optionId")
    OrderPageProductDTO findProductDetailForOrder(@Param("optionId") Integer optionId);

    TblProduct findTblProductByProductId(Integer productId);

    List<TblProduct> findByProductNameContaining(String search);

    @Query("SELECT p " +
            "FROM TblProduct p " +
            "JOIN TblCategorySmall cs ON p.smallCategory.smallCategoryId = cs.smallCategoryId " +
            "WHERE p.smallCategory.smallCategoryId = :smallId " +
            "AND p.productCheck = 'Y' " +
            "ORDER BY p.productId desc ")
    List<TblProduct> findByProductIdAndSmallCategory(@Param("smallId") Integer smallId);

    // 카테고리 대분류
    @Query("SELECT cl " +
            "FROM  TblCategoryLarge cl" )
    List<TblCategoryLarge> findAllProductCategory01();

    // 카테고리 중분류
    @Query("SELECT cm " +
            "FROM TblCategoryMedium cm " +
            "JOIN TblCategoryLarge cl ON cm.largeCategoryId = cl.largeCategoryId " +
            "WHERE cl.largeCategoryId = :largeId ")
    List<TblCategoryMedium> findAllProductCategory02(@Param("largeId") Integer largeId);


    TblProduct findByProductId(int id);
}
