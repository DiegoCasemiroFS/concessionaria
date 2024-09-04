package br.com.DiegoCasemiroFS.api.service;

import br.com.DiegoCasemiroFS.api.entity.Vehicle;
import br.com.DiegoCasemiroFS.api.exception.VehicleNotFoundException;
import br.com.DiegoCasemiroFS.api.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public List<Vehicle> findAll(){
        return vehicleRepository.findAllByOrderByPriceAsc();
    }

    public Vehicle findById(Long id){
        return vehicleRepository.findById(id)
                .map(vehicleRepository::save)
                .orElseThrow(VehicleNotFoundException::new);
    }

    public Vehicle registerVehicle(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        return vehicleRepository.findById(id)
                .map(f -> {
                    f.setName(vehicle.getName());
                    f.setBrand(vehicle.getBrand());
                    f.setModel(vehicle.getModel());
                    f.setCarYear(vehicle.getCarYear());
                    f.setPrice(vehicle.getPrice());
                    f.setVehicleType(vehicle.getVehicleType());

                    return vehicleRepository.save(f);
                })
                .orElseThrow(VehicleNotFoundException::new);
    }

    public void deleteVehicle(Long id){
        vehicleRepository.findById(id)
                .map(f -> {
                    vehicleRepository.delete(f);
                    return Void.TYPE;
                }).orElseThrow(VehicleNotFoundException::new);
    }
}
