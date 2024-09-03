package br.com.DiegoCasemiroFS.api.repository;

import br.com.DiegoCasemiroFS.api.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAllByOrderByPriceAsc();
}
