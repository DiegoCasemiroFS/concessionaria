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
        Usuario usuario = this.repository.findByEmail(body.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.getSenha(), usuario.getPassword())) {
            String token = this.service.geraToken(usuario);
            return ResponseEntity.ok(new ResponseDto(usuario.getNome(), token, usuario.isAdmin()));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/registro")
    public ResponseEntity registro(@RequestBody RegistroRequestDto body){
        Optional<Usuario> user = this.repository.findByEmail(body.getEmail());

        if(user.isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setSenha(passwordEncoder.encode(body.getSenha()));
            usuario.setEmail(body.getEmail());
            usuario.setNome(body.getNome());
            usuario.setAdmin(body.isAdmin());
            this.repository.save(usuario);

            String token = this.service.geraToken(usuario);
            return ResponseEntity.ok(new ResponseDto(usuario.getNome(), token, usuario.isAdmin()));
        }
        return ResponseEntity.badRequest().build();
    }
}
