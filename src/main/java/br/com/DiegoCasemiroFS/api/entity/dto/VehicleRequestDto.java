package br.com.DiegoCasemiroFS.api.entity.dto;

import br.com.DiegoCasemiroFS.api.entity.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VehicleRequestDto {

    private String name;
    private String brand;
    private Long model;
    private Long carYear;
    private Double price;
    private VehicleType vehicleType;
}