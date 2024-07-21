package br.com.DiegoCasemiroFS.api.repository;

import br.com.DiegoCasemiroFS.api.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findAllByOrderByPrecoAsc();
    List<Veiculo> findByNomeContainingIgnoreCase(String nome);
    List<Veiculo> findByMarcaContainingIgnoreCase(String marca);
}
