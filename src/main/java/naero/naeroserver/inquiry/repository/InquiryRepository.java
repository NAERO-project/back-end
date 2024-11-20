package naero.naeroserver.inquiry.repository;

import naero.naeroserver.entity.inquiry.TblInquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InquiryRepository extends JpaRepository<TblInquiry, Integer> {

    // 수정
    Optional<TblInquiry> findByInquiryIdAndUserIdAndProductId(Integer inquiryId, Integer userId, Integer productId);

    // 페이지 개수
    int countByProductId(Integer productId);

    // 상품 문의 전체 조회
    Page<TblInquiry> findByProductId(Integer productId, Pageable pageable);

    // 상품 문의 상세 조회
    TblInquiry findByProductIdAndInquiryId(Integer productId, Integer inquiryId);
}
