package naero.naeroserver.coupon.repository;

import naero.naeroserver.coupon.dto.MyPageCouponListDTO;
import naero.naeroserver.entity.coupon.TblCouponList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CouponListRepository extends JpaRepository<TblCouponList, Integer> {

    // 해당 회원번호로 발급된 쿠폰 총 개수 조회
    long countByUserId(int userId);

    // 해당 회원번호로 발급된 쿠폰 리스트 조회(페이징 처리됨)
    Page<TblCouponList> findByUserId(int userId, Pageable paging);

    @Query("SELECT new naero.naeroserver.coupon.dto.MyPageCouponListDTO(" +
            "c.couponId, c.couponName, " +
            "c.producerId, c.salePrice, c.maxSalePrice, c.usablePrice, " +
            "c.endDate, c.couponType, cl.couponGetId, cl.useStatus) " +
            "FROM TblCouponList cl " +
            "JOIN TblCoupon c ON c.couponId = cl.couponId " +
            "WHERE cl.userId = :userId")
    Page<MyPageCouponListDTO> findCouponListByUserId(int userId, Pageable paging);
}
