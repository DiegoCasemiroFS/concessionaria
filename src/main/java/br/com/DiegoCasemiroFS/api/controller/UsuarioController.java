package br.com.DiegoCasemiroFS.api.controller;

import br.com.DiegoCasemiroFS.api.entity.dto.RegistroRequestDto;
import br.com.DiegoCasemiroFS.api.entity.dto.ResponseDto;
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
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto body) {
            ResponseDto response = usuarioService.login(body);
            return ResponseEntity.ok(response);
    }

    @PostMapping("/registro")
    public ResponseEntity<ResponseDto> registro(@RequestBody RegistroRequestDto body) {
            ResponseDto response = usuarioService.registro(body);
            return ResponseEntity.ok(response);
    }
}
