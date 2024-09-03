package br.com.DiegoCasemiroFS.api.repository;

import br.com.DiegoCasemiroFS.api.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
