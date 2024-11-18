package naero.naeroserver.product.repository;

import naero.naeroserver.entity.product.TblProduct;
import naero.naeroserver.order.dto.OrderPageProductDTO;
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
            "JOIN TblCategorySmall sc ON p.smallCategoryId = sc.smallCategoryId " +
            "JOIN TblCategoryMedium mc ON sc.mediumCategoryId = mc.mediumCategoryId " +
            "JOIN TblCategoryLarge lc ON mc.largeCategoryId = lc.largeCategoryId " +
            "WHERE lc.largeCategoryId = :largeCategoryId AND p.productCheck = 'Y' " +
            "ORDER BY p.productId DESC")
    List<TblProduct> findByProductCheckAndSmallCategoryId(@Param("largeCategoryId") Integer largeCategoryId);

    @Query("SELECT p FROM TblProduct p " +
            "JOIN TblCategorySmall sc ON p.smallCategoryId = sc.smallCategoryId " +
            "JOIN TblCategoryMedium mc ON sc.mediumCategoryId = mc.mediumCategoryId " +
            "JOIN TblCategoryLarge lc ON mc.largeCategoryId = lc.largeCategoryId " +
            "WHERE lc.largeCategoryId = :largeCategoryId AND p.productCheck = 'Y' " +
            "ORDER BY p.productId DESC")
    Page<TblProduct> findByProductCheckAndSmallCategoryId(@Param("largeCategoryId") Integer largeCategoryId, Pageable paging);

    //    상품 리스트 미리보기 조회
    @Query("SELECT p FROM TblProduct p WHERE p.productCheck = 'Y' ORDER BY p.id DESC")
    List<TblProduct> findAllProductWithLimit(Pageable pageable);

    @Query("SELECT p FROM TblProduct p " +
            "JOIN TblCategorySmall sc ON p.smallCategoryId = sc.smallCategoryId " +
            "JOIN TblCategoryMedium mc ON sc.mediumCategoryId = mc.mediumCategoryId " +
            "JOIN TblCategoryLarge lc ON mc.largeCategoryId = lc.largeCategoryId " +
            "WHERE lc.largeCategoryId = :largeCategoryId AND p.productCheck = 'Y' " +
            "ORDER BY p.productId DESC")
    List<TblProduct> findByFoodProductWithLimit(@Param("largeCategoryId") Integer largeCategoryId, Pageable pageable);

    @Query("SELECT p, pi.producer FROM TblProduct p " +
            "JOIN TblProducer pi ON p.productId = pi.id " +
            "WHERE pi.id = :producerId AND p.productCheck = 'Y' " +
            "ORDER BY p.productId DESC")
    List<TblProduct> findByIdWithLimit(@Param("producerId") Integer producerId, Pageable pageable);

    // 주문 페이지 내 주문할 상품 정보 전달용
    @Query("SELECT new naero.naeroserver.order.dto.OrderPageProductDTO(o.optionId, o.productId, p.producerId, " +
            "pd.producerName, pd.deliveryFee, pd.deliveryCrit, p.productName, " +
            "p.productImg, p.productThumbnail, p.productPrice, o.addPrice, o.optionDesc, sc.smallCategoryId, sc.smallCategoryName) " +
            "FROM TblProduct p " +
            "JOIN TblProducer pd ON p.producerId = pd.producerId " +
            "JOIN TblOption o ON p.productId = o.productId " +
            "JOIN TblCategorySmall sc ON p.smallCategoryId = sc.smallCategoryId " +
            "WHERE o.optionId = :optionId")
    OrderPageProductDTO findProductDetailForOrder(@Param("optionId") Integer optionId);

    TblProduct findTblProductByProductId(Integer productId);
}
