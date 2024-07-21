package br.com.DiegoCasemiroFS.api.entity;

import br.com.DiegoCasemiroFS.api.entity.enums.TipoVeiculo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Campo tipoVeiculo e obrigatório")
    @NotNull(message = "Campo tipoVeiculo não deve ser nulo")
    private TipoVeiculo tipoVeiculo;

    @Column(length = 20)
    @NotEmpty(message = "Campo marca e obrigatório")
    @NotNull(message = "Campo marca não deve ser nulo")
    private String marca;

    @Column(length = 20)
    @NotEmpty(message = "Campo nome e obrigatório")
    @NotNull(message = "Campo nome não pode ser nulo")
    private String nome;

    @Column(length = 4)
    @NotEmpty(message = "Campo ano e obrigatório")
    @NotNull(message = "Campo ano não deve ser nulo")
    private Long ano;

    @Column(length = 4)
    @NotEmpty(message = "Campo modelo e obrigatório")
    @NotNull(message = "Campo modelo não deve ser nulo")
    private Long modelo;

    @Column(length = 20)
    @NotEmpty(message = "Campo preco e obrigatório")
    @NotNull(message = "Campo preco não deve ser nulo")
    private Double preco;

}
