package naero.naeroserver.review.repository;

import naero.naeroserver.entity.inquiry.TblReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<TblReview, Integer> {

    Page<TblReview> findByUserId(Integer userId, Pageable pageable);

    Page<TblReview> findByProductId(Integer productId, Pageable pageable);
}
