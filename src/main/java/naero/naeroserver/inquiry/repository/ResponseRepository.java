package naero.naeroserver.inquiry.repository;

import naero.naeroserver.entity.inquiry.TblInquiry;
import naero.naeroserver.entity.inquiry.TblResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ResponseRepository extends JpaRepository<TblResponse, Integer> {

    void deleteByInquiryId(Integer inquiry);


    Page<TblResponse> findByProducerId(Integer producer, Pageable pageable);
}
