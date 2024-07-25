package br.com.DiegoCasemiroFS.api.service;

import br.com.DiegoCasemiroFS.api.entity.Usuario;
import br.com.DiegoCasemiroFS.api.entity.dto.UsuarioRequestDto;
import br.com.DiegoCasemiroFS.api.entity.dto.LoginRequestDto;
import br.com.DiegoCasemiroFS.api.entity.dto.UsuarioResponseDto;
import br.com.DiegoCasemiroFS.api.exception.UsuarioException;
import br.com.DiegoCasemiroFS.api.repository.UsuarioRepository;
import br.com.DiegoCasemiroFS.api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " não encontrado")
        );
    }

    public UsuarioResponseDto login(LoginRequestDto body) {
        Usuario usuario = usuarioRepository.findByEmail(body.getEmail())
                .orElseThrow(UsuarioException::new);

        if (passwordEncoder.matches(body.getSenha(), usuario.getPassword())) {
            String token = jwtService.geraToken(usuario);
            return new UsuarioResponseDto(usuario.getNome(), token, usuario.isAdmin());
        }
        throw new RuntimeException("Usuario ou senha incorretos");
    }

    public UsuarioResponseDto registro(UsuarioRequestDto body) {
        Optional<Usuario> user = usuarioRepository.findByEmail(body.getEmail());

        if (user.isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setSenha(passwordEncoder.encode(body.getSenha()));
            usuario.setEmail(body.getEmail());
            usuario.setNome(body.getNome());
            usuario.setAdmin(body.isAdmin());
            usuarioRepository.save(usuario);

            String token = jwtService.geraToken(usuario);
            return new UsuarioResponseDto(usuario.getNome(), "Realize o login para ter acesso ao Token", usuario.isAdmin());
        }
        throw new RuntimeException("Email já cadastrado");
    }
}
