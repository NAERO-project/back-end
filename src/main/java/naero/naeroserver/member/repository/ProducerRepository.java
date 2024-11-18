package naero.naeroserver.member.repository;

import naero.naeroserver.entity.order.TblOrderDetail;
import naero.naeroserver.entity.user.TblProducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProducerRepository extends JpaRepository<TblProducer, Integer> {

    // 판매자번호로 구매할 상품의 판매자 정보 조회
    TblProducer findByProducerId(Integer producerId);
}
