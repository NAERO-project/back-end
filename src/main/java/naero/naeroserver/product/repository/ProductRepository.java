package naero.naeroserver.product.repository;

import naero.naeroserver.entity.product.TblProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<TblProduct, Integer> {
    List<TblProduct> findByProductCheck(String status);

    Page<TblProduct> findByProductCheck(String status, Pageable paging);

    List<TblProduct> findByProductCheckAndSmallCategoryIdOrSmallCategoryId(String status, Integer smallCategoryId1, Integer smallCategoryId2);

    Page<TblProduct> findByProductCheckAndSmallCategoryIdOrSmallCategoryId(String status, Integer smallCategoryId1, Integer smallCategoryId2, Pageable paging);

    List<TblProduct> findByProductCheckAndSmallCategoryId(String status, Integer smallCategoryId);

    Page<TblProduct> findByProductCheckAndSmallCategoryId(String status, Integer smallCategoryId, Pageable paging);
}
