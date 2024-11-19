package naero.naeroserver.coupon.repository;

import naero.naeroserver.entity.coupon.TblCoupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponRepository extends JpaRepository<TblCoupon, Integer> {

    // 해당 판매자 쿠폰 리스트 조회
    Page<TblCoupon> findByProducerId(Integer userId, Pageable paging);

    // 해당 판매자 등록된 쿠폰 총 개수 조회
    long countByProducerId(Integer userId);

}
