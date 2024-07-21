package br.com.DiegoCasemiroFS.api.entity;

import br.com.DiegoCasemiroFS.api.entity.enums.Perfil;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotEmpty(message = "Campo nome e obrigatório")
    @NotNull(message = "Campo nome não pode ser nulo")
    private String nome;

    @Column(length = 80)
    @Email(message = "Informe um email valido")
    @NotEmpty(message = "Campo email e obrigatório")
    @NotNull(message = "Campo email não deve ser nulo")
    private String email;

    @Column(length = 14)
    @CPF(message = "Informe um cpf valido")
    @NotEmpty(message = "Campo CPF e obrigatório")
    @NotNull(message = "Campo CPF não deve ser nulo")
    private String cpf;

    private Perfil perfil;
}
