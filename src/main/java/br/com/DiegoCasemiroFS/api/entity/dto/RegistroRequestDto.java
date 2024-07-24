package br.com.DiegoCasemiroFS.api.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistroRequestDto{

    private String nome;
    private String email;
    private String senha;
    private boolean admin;
}
