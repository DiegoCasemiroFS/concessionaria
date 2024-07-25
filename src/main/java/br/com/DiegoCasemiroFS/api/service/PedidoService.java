package br.com.DiegoCasemiroFS.api.service;

import br.com.DiegoCasemiroFS.api.entity.Pedido;
import br.com.DiegoCasemiroFS.api.entity.Usuario;
import br.com.DiegoCasemiroFS.api.entity.Veiculo;
import br.com.DiegoCasemiroFS.api.entity.dto.PedidoRequestDto;
import br.com.DiegoCasemiroFS.api.entity.dto.PedidoResponseDto;
import br.com.DiegoCasemiroFS.api.exception.PedidoException;
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

    public PedidoResponseDto listagem(Long id) {
        Pedido existingPedido = pedidoRepository.findById(id).orElseThrow(PedidoException::new);

        PedidoResponseDto responseDto = new PedidoResponseDto();

        responseDto.setId(existingPedido.getId());
        responseDto.setNomeVeiculo(existingPedido.getVeiculo().getNome());
        responseDto.setNomeUsuario(existingPedido.getUsuario().getNome());
        responseDto.setPreco(existingPedido.getVeiculo().getPreco());
        responseDto.setDataCadastro(existingPedido.getDataCadastro());

        return responseDto;
    }


    public PedidoResponseDto cadastraPedido(PedidoRequestDto pedidoDto) {
        Veiculo veiculo = veiculoRepository.findById(pedidoDto.getVeiculoId()).orElseThrow(() -> new RuntimeException("Veiculo não encontrado"));
        Usuario usuario = usuarioRepository.findById(pedidoDto.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setVeiculo(veiculo);
        pedido.setUsuario(usuario);
        pedido.setDataCadastro(LocalDateTime.now());

        Pedido savedPedido = pedidoRepository.save(pedido);

        PedidoResponseDto responseDto = new PedidoResponseDto();
        responseDto.setId(savedPedido.getId());
        responseDto.setNomeVeiculo(savedPedido.getVeiculo().getNome());
        responseDto.setNomeUsuario(savedPedido.getUsuario().getNome());
        responseDto.setPreco(savedPedido.getVeiculo().getPreco());
        responseDto.setDataCadastro(savedPedido.getDataCadastro());

        return responseDto;
    }

    public PedidoResponseDto atualizaPedido(Long id, PedidoRequestDto pedidoDto) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        Veiculo veiculo = veiculoRepository.findById(pedidoDto.getVeiculoId()).orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        Usuario usuario = usuarioRepository.findById(pedidoDto.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        pedido.setVeiculo(veiculo);
        pedido.setUsuario(usuario);
        pedido.setDataCadastro(LocalDateTime.now());

        Pedido updatedPedido = pedidoRepository.save(pedido);

        PedidoResponseDto responseDto = new PedidoResponseDto();
        responseDto.setId(updatedPedido.getId());
        responseDto.setNomeVeiculo(updatedPedido.getVeiculo().getNome());
        responseDto.setNomeUsuario(updatedPedido.getUsuario().getNome());
        responseDto.setPreco(updatedPedido.getVeiculo().getPreco());
        responseDto.setDataCadastro(updatedPedido.getDataCadastro());

        return responseDto;
    }


    public void deletaPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}