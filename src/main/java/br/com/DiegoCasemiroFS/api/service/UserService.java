package br.com.DiegoCasemiroFS.api.service;

import br.com.DiegoCasemiroFS.api.entity.Users;
import br.com.DiegoCasemiroFS.api.entity.dto.UserRequestDto;
import br.com.DiegoCasemiroFS.api.entity.dto.LoginRequestDto;
import br.com.DiegoCasemiroFS.api.entity.dto.UserResponseDto;
import br.com.DiegoCasemiroFS.api.exception.CadastroException;
import br.com.DiegoCasemiroFS.api.exception.LoginException;
import br.com.DiegoCasemiroFS.api.exception.UsuarioException;
import br.com.DiegoCasemiroFS.api.repository.UserRepository;
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
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " não encontrado")
        );
    }

    public UserResponseDto login(LoginRequestDto requestDto) {
        Users users = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(UsuarioException::new);

        if (passwordEncoder.matches(requestDto.getSenha(), users.getPassword())) {
            String token = jwtService.geraToken(users);
            return new UserResponseDto(users.getId(), users.getName(), token, users.isAdmin());
        }
        throw new LoginException();
    }

    public UserResponseDto registerUser(UserRequestDto requestDto) {
        Optional<Users> user = userRepository.findByEmail(requestDto.getEmail());

        if (user.isEmpty()) {
            Users users = new Users();
            users.setPassword(passwordEncoder.encode(requestDto.getSenha()));
            users.setEmail(requestDto.getEmail());
            users.setName(requestDto.getNome());
            users.setAdmin(requestDto.isAdmin());
            userRepository.save(users);

            String token = jwtService.geraToken(users);
            return new UserResponseDto(
                    users.getId(),
                    users.getName(),
                    "Realize o login para ter acesso ao Token",
                    users.isAdmin());
        }
        throw new CadastroException();
    }

    public UserRequestDto updateUser(Long id, UserRequestDto requestDto){

        Users users = userRepository.findById(id).orElseThrow(UsuarioException::new);

            users.setName(requestDto.getNome());
            users.setEmail(requestDto.getEmail());
            users.setPassword(requestDto.getSenha());
            userRepository.save(users);


        return new UserRequestDto(
                users.getName(),
                users.getEmail(),
                users.getPassword(),
                users.isAdmin());
    }
}