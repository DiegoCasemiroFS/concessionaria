package br.com.DiegoCasemiroFS.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    @JsonIgnore
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;

    private LocalDateTime dataCadastro;

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", veiculo=" + veiculo +
                ", usuario=" + usuario +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}
