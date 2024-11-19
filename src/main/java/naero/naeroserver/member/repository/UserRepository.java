package naero.naeroserver.member.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import naero.naeroserver.entity.user.TblUser;
import naero.naeroserver.member.dto.ManageSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<TblUser, Integer> {


    TblUser findById(int Id);
    TblUser findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByUserEmail(String email);
    public TblUser findByIdAndWithStatus(int Id, String status);

    TblUser findByUserEmail(String email);
//    TblUser updateByUsername(TblUser user);
    Page<TblUser> findAll(Pageable pageable);


}
