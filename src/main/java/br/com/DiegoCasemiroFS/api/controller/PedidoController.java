package br.com.DiegoCasemiroFS.api.controller;

import br.com.DiegoCasemiroFS.api.entity.Pedido;
import br.com.DiegoCasemiroFS.api.entity.dto.PedidoRequestDto;
import br.com.DiegoCasemiroFS.api.entity.dto.PedidoResponseDto;
import br.com.DiegoCasemiroFS.api.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping("/{id}")
    public Pedido findById(@PathVariable Long id){
        return pedidoService.findById(id);
    }

    @PostMapping("/cadastra")
    public ResponseEntity<PedidoResponseDto> cadastra(@RequestBody PedidoRequestDto pedidoDto){
        PedidoResponseDto responseDto = pedidoService.cadastraPedido(pedidoDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDto> atualiza(@PathVariable Long id, @RequestBody PedidoRequestDto pedidoDto) {
        PedidoResponseDto updatedPedido = pedidoService.atualizaPedido(id, pedidoDto);
        return ResponseEntity.ok(updatedPedido);
    }

    @DeleteMapping("/{id}")
    public void deleta(@PathVariable Long id){
        pedidoService.deletaPedido(id);
    }
}