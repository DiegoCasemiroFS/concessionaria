package br.com.DiegoCasemiroFS.api.controller;

import br.com.DiegoCasemiroFS.api.entity.Pedido;
import br.com.DiegoCasemiroFS.api.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping("/cria")
    public Pedido cria(@RequestBody Pedido pedido) {
        return pedidoService.createPedido(pedido);
    }

    @PutMapping(("/{id}"))
    public Pedido atualiza(@PathVariable Long id , @RequestBody Pedido pedido) {
        return pedidoService.updatePedido(id, pedido);
    }

    @DeleteMapping(("/{id}"))
    public void deleta(@PathVariable Long id) {
        pedidoService.deletePedido(id);
    }

}