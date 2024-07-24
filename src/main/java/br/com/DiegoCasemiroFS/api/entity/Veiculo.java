package br.com.DiegoCasemiroFS.api.entity;

import br.com.DiegoCasemiroFS.api.entity.enums.TipoVeiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String marca;

    private Long modelo;

    private Long ano;

    private Double preco;

    private TipoVeiculo tipoVeiculo;

    @OneToMany(mappedBy = "veiculo")
    @JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();

    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo=" + modelo +
                ", ano=" + ano +
                ", preco=" + preco +
                ", tipoVeiculo=" + tipoVeiculo +
                '}';
    }
}