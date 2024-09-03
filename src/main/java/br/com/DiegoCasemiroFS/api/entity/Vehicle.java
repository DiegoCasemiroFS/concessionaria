package br.com.DiegoCasemiroFS.api.entity;

import br.com.DiegoCasemiroFS.api.entity.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String brand;

    private Long model;

    private Long carYear;

    private Double price;

    private VehicleType vehicleType;

    @OneToMany(mappedBy = "vehicle")
    @JsonIgnore
    private List<Orders> orders = new ArrayList<>();

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", model=" + model +
                ", year=" + carYear +
                ", price=" + price +
                ", vehicleType=" + vehicleType +
                ", orders=" + orders +
                '}';
    }
}