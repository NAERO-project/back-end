package naero.naeroserver.member.repository;

import naero.naeroserver.entity.user.TblProducer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<TblProducer, Integer> {
    Page<TblProducer> findAll(Pageable pageable);
    TblProducer findByProducerId(int id);

    // 판매자번호로 구매할 상품의 판매자 정보 조회
    TblProducer findByProducerId(Integer producerId);
}
