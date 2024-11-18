package naero.naeroserver.member.repository;

import naero.naeroserver.entity.user.TblProducer;
import naero.naeroserver.entity.user.TblUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<TblProducer, Integer> {
    Page<TblProducer> findAll(Pageable pageable);
}
