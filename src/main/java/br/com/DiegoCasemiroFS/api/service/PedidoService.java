package br.com.DiegoCasemiroFS.api.service;

import br.com.DiegoCasemiroFS.api.entity.Pedido;
import br.com.DiegoCasemiroFS.api.exception.PedidoException;
import br.com.DiegoCasemiroFS.api.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public Pedido createPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido updatePedido(Long id, Pedido pedido) {
        return pedidoRepository.findById(id)
                .map(f -> {
                    f.setId(pedido.getId());
                    pedidoRepository.save(pedido);
                    return pedido;
                }).orElseThrow(() -> new PedidoException());
    }

    public void deletePedido(Long id) {
        pedidoRepository.findById(id)
                .map(f -> {
                    pedidoRepository.delete(f);
                    return Void.TYPE;
                }).orElseThrow(() -> new PedidoException());
    }
}
