package br.com.DiegoCasemiroFS.api.controller;

import br.com.DiegoCasemiroFS.api.entity.Vehicle;
import br.com.DiegoCasemiroFS.api.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/showcase")
public class ShowcaseController {

    private final VehicleService vehicleService;

    @GetMapping("/findAll")
    public List<Vehicle> findAll(){
        return vehicleService.findAll();
    }

    @GetMapping(("/findById/{id}"))
    public Vehicle findById(@PathVariable Long id){
        return vehicleService.findById(id);
    }
}
