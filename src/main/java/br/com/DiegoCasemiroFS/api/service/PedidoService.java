package br.com.DiegoCasemiroFS.api.service;

import br.com.DiegoCasemiroFS.api.entity.Pedido;
import br.com.DiegoCasemiroFS.api.entity.Usuario;
import br.com.DiegoCasemiroFS.api.entity.Veiculo;
import br.com.DiegoCasemiroFS.api.entity.dto.PedidoDto;
import br.com.DiegoCasemiroFS.api.repository.PedidoRepository;
import br.com.DiegoCasemiroFS.api.repository.UsuarioRepository;
import br.com.DiegoCasemiroFS.api.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final VeiculoRepository veiculoRepository;
    private final UsuarioRepository usuarioRepository;

    public Optional<Pedido> findById(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido;
    }

    public Pedido cadastraPedido(PedidoDto pedidoDto) {
        Veiculo veiculo = veiculoRepository.findById(pedidoDto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veiculo not found"));
        Usuario usuario = usuarioRepository.findById(pedidoDto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario not found"));

        Pedido pedido = new Pedido();
        pedido.setVeiculo(veiculo);
        pedido.setUsuario(usuario);
        pedido.setDataCadastro(LocalDateTime.now());

        Pedido savedPedido = pedidoRepository.save(pedido);
        return savedPedido;
    }

    public Optional<Pedido> atualizaPedido(Long id, Pedido pedido) {
        return pedidoRepository.findById(id).map(existingPedido -> {
            existingPedido.setVeiculo(pedido.getVeiculo());
            existingPedido.setUsuario(pedido.getUsuario());
            existingPedido.setDataCadastro(pedido.getDataCadastro());
            Pedido updatedPedido = pedidoRepository.save(existingPedido);
            return updatedPedido;
        });
    }

    public void deletaPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}