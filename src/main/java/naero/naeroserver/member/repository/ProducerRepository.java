package naero.naeroserver.member.repository;

import naero.naeroserver.entity.user.TblProducer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<TblProducer, Integer> {
}
