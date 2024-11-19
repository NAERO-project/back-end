package naero.naeroserver.order.repository;

import naero.naeroserver.entity.order.TblAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<TblAddress, Integer> {

    List<TblAddress> findTblAddressByUserId(Integer userId);
}
