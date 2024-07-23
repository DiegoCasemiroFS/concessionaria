package br.com.DiegoCasemiroFS.api.controller;

import br.com.DiegoCasemiroFS.api.entity.Usuario;
import br.com.DiegoCasemiroFS.api.entity.dto.TokenDto;
import br.com.DiegoCasemiroFS.api.entity.dto.UsuarioDto;
import br.com.DiegoCasemiroFS.api.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/usuario")
public class UsuarioController {

    private final AuthenticationManager manager;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid UsuarioDto usuarioDto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(usuarioDto.email(), usuarioDto.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJwt = jwtService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDto(tokenJwt));
    }
}
