package naero.naeroserver.product.repository;

import naero.naeroserver.entity.user.TblProducer;
import naero.naeroserver.product.dto.ProductProducerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductProducerRepository extends JpaRepository<TblProducer, Integer> {

    @Query("SELECT new naero.naeroserver.product.dto.ProductProducerDTO(pi.producerId, pi.producerName) " +
            "FROM TblProducer pi " +
            "JOIN TblProduct p ON pi.producerId = p.producerId " +
            "ORDER BY pi.producerId desc ")
    List<ProductProducerDTO> findByProducerName();
}
