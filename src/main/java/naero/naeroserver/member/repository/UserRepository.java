package naero.naeroserver.member.repository;

import naero.naeroserver.entity.user.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<TblUser, Integer> {
    public TblUser findByIdAndWithStatus(int Id, String status);

}
