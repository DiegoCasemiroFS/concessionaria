package br.com.DiegoCasemiroFS.api.entity;

import br.com.DiegoCasemiroFS.api.entity.enums.Perfil;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotEmpty(message = "Campo nome e obrigatório")
    private String nome;

    @Column(length = 80)
    @Email(message = "Informe um email valido")
    @NotEmpty(message = "Campo email e obrigatório")
    private String email;

    @Column(length = 80)
    @NotEmpty(message = "Campo senha e obrigatório")
    private String senha;

    @Column(length = 14)
    @CPF(message = "Informe um CPF valido")
    @NotEmpty(message = "Campo CPF e obrigatório")
    private String cpf;

    @NotEmpty(message = "Campo perfil e obrigatório")
    @NotNull(message = "Campo perfil não deve ser nulo")
    private Perfil perfil;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USUARIO"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
