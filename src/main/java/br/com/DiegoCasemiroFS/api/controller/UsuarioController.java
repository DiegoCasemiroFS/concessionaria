package br.com.DiegoCasemiroFS.api.controller;

import br.com.DiegoCasemiroFS.api.entity.dto.UsuarioRequestDto;
import br.com.DiegoCasemiroFS.api.entity.dto.UsuarioResponseDto;
import br.com.DiegoCasemiroFS.api.entity.dto.LoginRequestDto;
import br.com.DiegoCasemiroFS.api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
            UsuarioResponseDto response = usuarioService.login(loginRequestDto);
            return ResponseEntity.ok(response);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioResponseDto> cadastro(@RequestBody UsuarioRequestDto usuarioRequestDto) {
            UsuarioResponseDto response = usuarioService.cadastro(usuarioRequestDto);
            return ResponseEntity.ok(response);
    }
}
