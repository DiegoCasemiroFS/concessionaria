package br.com.DiegoCasemiroFS.api.service;

import br.com.DiegoCasemiroFS.api.entity.Vehicle;
import br.com.DiegoCasemiroFS.api.entity.dto.VehicleRequestDto;
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

    public Vehicle registerVehicle(VehicleRequestDto requestDto){

        Vehicle vehicle = new Vehicle();

        vehicle.setName(requestDto.getName());
        vehicle.setBrand(requestDto.getBrand());
        vehicle.setModel(requestDto.getModel());
        vehicle.setCarYear(requestDto.getCarYear());
        vehicle.setPrice(requestDto.getPrice());
        vehicle.setVehicleType(requestDto.getVehicleType());

        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, VehicleRequestDto requestDto) {

        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(VehicleNotFoundException::new);

        vehicle.setName(requestDto.getName());
        vehicle.setBrand(requestDto.getBrand());
        vehicle.setModel(requestDto.getModel());
        vehicle.setCarYear(requestDto.getCarYear());
        vehicle.setPrice(requestDto.getPrice());
        vehicle.setVehicleType(requestDto.getVehicleType());

        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id){
        vehicleRepository.findById(id)
                .map(f -> {
                    vehicleRepository.delete(f);
                    return Void.TYPE;
                }).orElseThrow(VehicleNotFoundException::new);
    }
}
