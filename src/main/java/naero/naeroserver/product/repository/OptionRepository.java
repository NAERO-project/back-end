package naero.naeroserver.product.repository;

import naero.naeroserver.entity.product.TblOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<TblOption, Integer> {
}
