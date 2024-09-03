package br.com.DiegoCasemiroFS.api.controller;

import br.com.DiegoCasemiroFS.api.entity.Vehicle;
import br.com.DiegoCasemiroFS.api.service.VehicleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping("/register")
    public Vehicle registerVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.registerVehicle(vehicle);
    }

    @PutMapping(("/update/{id}"))
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle){
        return vehicleService.updateVehicle(id, vehicle);
    }

    @DeleteMapping(("/{id}"))
    public void deleteVehicle(@PathVariable Long id){
        vehicleService.deleteVehicle(id);
    }
}
