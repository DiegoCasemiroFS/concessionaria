package br.com.DiegoCasemiroFS.api.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

    private Long id;
    private String vehicleName;
    private String username;
    private Double price;
    private LocalDateTime registerDate;
}
