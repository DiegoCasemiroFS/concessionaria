package br.com.DiegoCasemiroFS.api.repository;

import br.com.DiegoCasemiroFS.api.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
