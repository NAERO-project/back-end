package naero.naeroserver.inquiry.repository;

import naero.naeroserver.entity.inquiry.TblInquiry;
import naero.naeroserver.entity.inquiry.TblResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<TblResponse, Integer> {
    // 상품 문의 삭제 시 해당 상품 문의 답변도 삭제
    void deleteByInquiry(TblInquiry inquiry);
}
