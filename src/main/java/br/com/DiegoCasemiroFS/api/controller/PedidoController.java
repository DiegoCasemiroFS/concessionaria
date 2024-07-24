package br.com.DiegoCasemiroFS.api.controller;

import br.com.DiegoCasemiroFS.api.entity.Pedido;
import br.com.DiegoCasemiroFS.api.entity.dto.PedidoDto;
import br.com.DiegoCasemiroFS.api.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping("/{id}")
    public Optional<Pedido> findById(@PathVariable Long id){
        Optional<Pedido> pedido = pedidoService.findById(id);
        return pedido;
    }

    @PostMapping("/cadastra")
    public Pedido cadastra(@RequestBody PedidoDto pedido){
        Pedido createdPedido = pedidoService.cadastraPedido(pedido);
        return createdPedido;
    }

    @PutMapping("/{id}")
    public Optional<Pedido> atualiza(@PathVariable Long id, @RequestBody Pedido pedido){
        Optional<Pedido> updatedPedido = pedidoService.atualizaPedido(id, pedido);
        return updatedPedido;
    }

    @DeleteMapping("/{id}")
    public void deleta(@PathVariable Long id){
        pedidoService.deletaPedido(id);
        System.out.println("Pedido deletado: ID " + id);
    }
}