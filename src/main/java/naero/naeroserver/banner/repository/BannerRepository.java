package naero.naeroserver.banner.repository;

import naero.naeroserver.entity.product.TblBanner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BannerRepository extends JpaRepository<TblBanner, Integer> {

    List<TblBanner> findAllByBannerAcceptStatus(String status);

    @Query("SELECT b FROM TblBanner b " +
            "JOIN TblProducer pi ON b.producerId = pi.id " +
            "WHERE pi.id = :producerId " +
            "ORDER BY b.bannerId")
    List<TblBanner> findAllByAndProducerId(@Param("producerId") int producerId);

    @Query("SELECT b FROM TblBanner b " +
            "JOIN TblProducer pi ON b.producerId = pi.id " +
            "WHERE pi.id = :producerId " +
            "ORDER BY b.bannerId")
    Page<TblBanner> findAllByAndProducerId(@Param("producerId") int producerId,
                                            Pageable paging);
}
