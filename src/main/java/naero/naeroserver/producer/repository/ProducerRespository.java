package naero.naeroserver.producer.repository;

import naero.naeroserver.entity.user.TblProducer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProducerRespository extends JpaRepository<TblProducer,Integer> {


    Optional<Object> findByProducerId(Integer producerId);
}
