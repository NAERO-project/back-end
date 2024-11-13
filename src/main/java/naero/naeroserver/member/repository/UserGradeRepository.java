package naero.naeroserver.member.repository;

import naero.naeroserver.entity.user.TblGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGradeRepository extends JpaRepository<TblGrade,Integer> {
    TblGrade findById(int id);
}
