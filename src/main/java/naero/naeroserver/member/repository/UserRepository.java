package naero.naeroserver.member.repository;

import naero.naeroserver.entity.user.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<TblUser, Integer> {
    TblUser findById(int Id);
    TblUser findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByUserEmail(String email);
    public TblUser findByIdAndWithStatus(int Id, String status);

}
