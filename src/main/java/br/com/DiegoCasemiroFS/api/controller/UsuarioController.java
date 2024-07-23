package br.com.DiegoCasemiroFS.api.controller;

import br.com.DiegoCasemiroFS.api.entity.Usuario;
import br.com.DiegoCasemiroFS.api.entity.dto.RegistroRequestDto;
import br.com.DiegoCasemiroFS.api.entity.dto.ResponseDto;
import br.com.DiegoCasemiroFS.api.entity.dto.LoginRequestDto;
import br.com.DiegoCasemiroFS.api.repository.UsuarioRepository;
import br.com.DiegoCasemiroFS.api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto body){
        Usuario usuario = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.senha(), usuario.getPassword())) {
            String token = this.service.gerarToken(usuario);
            return ResponseEntity.ok(new ResponseDto(usuario.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/registro")
    public ResponseEntity registro(@RequestBody RegistroRequestDto body){
        Optional<Usuario> user = this.repository.findByEmail(body.email());

        if(user.isEmpty()) {
            Usuario newUser = new Usuario();
            newUser.setSenha(passwordEncoder.encode(body.senha()));
            newUser.setEmail(body.email());
            newUser.setNome(body.nome());
            this.repository.save(newUser);

            String token = this.service.gerarToken(newUser);
            return ResponseEntity.ok(new ResponseDto(newUser.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
