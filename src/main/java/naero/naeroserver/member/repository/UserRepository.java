package naero.naeroserver.member.repository;

import naero.naeroserver.entity.user.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<TblUser, Integer> {
    TblUser findById(int Id);
    TblUser findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByUserEmail(String email);

//    TblUser findTblUserByUserId(int userId);

    TblUser findTblUserById(int userId);
}
