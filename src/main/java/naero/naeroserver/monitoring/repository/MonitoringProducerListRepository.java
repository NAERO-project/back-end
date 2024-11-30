package naero.naeroserver.monitoring.repository;

import naero.naeroserver.entity.product.TblProduct;
import naero.naeroserver.monitoring.dto.SimplifiedProducerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitoringProducerListRepository extends JpaRepository<TblProduct, Integer> {

    @Query("SELECT new naero.naeroserver.monitoring.dto.SimplifiedProducerDTO(" +
            "p.producerId, p.busiNo, p.producerAdd, p.producerName, p.producerPhone, " +
            "p.withStatus, p.deliveryFee, p.deliveryCrit) " +
            "FROM TblProducer p")
    List<SimplifiedProducerDTO> findAllProducers();
}
