package br.com.DiegoCasemiroFS.api.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private Long vehicleId;
    private Long userId;
}
