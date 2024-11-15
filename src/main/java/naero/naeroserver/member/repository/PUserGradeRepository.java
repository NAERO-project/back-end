package naero.naeroserver.member.repository;

import naero.naeroserver.entity.user.TblProducerGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PUserGradeRepository extends JpaRepository<TblProducerGrade, Integer> {
}
