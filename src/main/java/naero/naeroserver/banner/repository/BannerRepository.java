package naero.naeroserver.banner.repository;

import naero.naeroserver.entity.product.TblBanner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<TblBanner, Integer> {

}
