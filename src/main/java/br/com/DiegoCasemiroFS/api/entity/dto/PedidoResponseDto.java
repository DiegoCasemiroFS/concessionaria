package br.com.DiegoCasemiroFS.api.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponseDto {

    private Long id;
    private String nomeVeiculo;
    private String nomeUsuario;
    private Double preco;
    private LocalDateTime dataCadastro;
}
