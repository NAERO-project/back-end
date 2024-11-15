package naero.naeroserver.banner.repository;

import naero.naeroserver.entity.product.TblBanner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BannerRepository extends JpaRepository<TblBanner, Integer> {

    @Query("SELECT b FROM TblBanner b " +
            "JOIN b.producer pi " +
            "WHERE pi.id = :id " +
            "ORDER BY b.bannerId")
    List<TblBanner> findAllByProducerId(@Param("id") int id);

    @Query("SELECT b FROM TblBanner b " +
            "JOIN b.producer pi " +
            "WHERE pi.id = :id " +
            "ORDER BY b.bannerId")
    Page<TblBanner> findAllByProducerId(@Param("id") int id, Pageable pageable);
}
