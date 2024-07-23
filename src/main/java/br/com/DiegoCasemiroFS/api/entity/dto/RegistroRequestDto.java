package br.com.DiegoCasemiroFS.api.entity.dto;

import br.com.DiegoCasemiroFS.api.entity.enums.Perfil;

public record RegistroRequestDto(String nome, String email, String senha, Perfil perfil) {
}
