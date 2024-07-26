package br.com.DiegoCasemiroFS.api.service;

import br.com.DiegoCasemiroFS.api.entity.Usuario;
import br.com.DiegoCasemiroFS.api.entity.dto.UsuarioRequestDto;
import br.com.DiegoCasemiroFS.api.entity.dto.LoginRequestDto;
import br.com.DiegoCasemiroFS.api.entity.dto.UsuarioResponseDto;
import br.com.DiegoCasemiroFS.api.exception.CadastroException;
import br.com.DiegoCasemiroFS.api.exception.LoginException;
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
            return new UsuarioResponseDto(usuario.getId(), usuario.getNome(), token, usuario.isAdmin());
        }
        throw new LoginException();
    }

    public UsuarioResponseDto cadastro(UsuarioRequestDto body) {
        Optional<Usuario> user = usuarioRepository.findByEmail(body.getEmail());

        if (user.isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setSenha(passwordEncoder.encode(body.getSenha()));
            usuario.setEmail(body.getEmail());
            usuario.setNome(body.getNome());
            usuario.setAdmin(body.isAdmin());
            usuarioRepository.save(usuario);

            String token = jwtService.geraToken(usuario);
            return new UsuarioResponseDto(
                    usuario.getId(),
                    usuario.getNome(),
                    "Realize o login para ter acesso ao Token",
                    usuario.isAdmin());
        }
        throw new CadastroException();
    }

    public Usuario atualziaCadastro(Long id, UsuarioRequestDto usuarioRequestDto){
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(usuarioRequestDto.getNome());
                    usuario.setEmail(usuarioRequestDto.getEmail());
                    usuario.setSenha(usuarioRequestDto.getSenha());
                    return usuarioRepository.save(usuario);

                }).orElseThrow(UsuarioException::new);
    }

}
