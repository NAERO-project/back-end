package naero.naeroserver.member.repository;

import naero.naeroserver.entity.user.TblUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<TblUser, Integer> {
    TblUser findById(int Id);
    TblUser findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByUserEmail(String email);

    TblUser findByUserEmail(String email);
//    TblUser updateByUsername(TblUser user);
    Page<TblUser> findAll(Pageable pageable);

}
