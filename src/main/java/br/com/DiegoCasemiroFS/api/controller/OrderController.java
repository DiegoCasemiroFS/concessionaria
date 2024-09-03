package br.com.DiegoCasemiroFS.api.controller;

import br.com.DiegoCasemiroFS.api.entity.dto.OrderRequestDto;
import br.com.DiegoCasemiroFS.api.entity.dto.OrderResponseDto;
import br.com.DiegoCasemiroFS.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/findById/{id}")
    public OrderResponseDto findById(@PathVariable Long id){
        return orderService.findById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<OrderResponseDto> registerOrder(@RequestBody OrderRequestDto requestDto){
        OrderResponseDto responseDto = orderService.registerOrder(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable Long id, @RequestBody OrderRequestDto requestDto) {
        OrderResponseDto updatedPedido = orderService.updateOrder(id, requestDto);
        return ResponseEntity.ok(updatedPedido);
    }

    @DeleteMapping("/{id}")
    public void deletaPedido(@PathVariable Long id){
        orderService.deleteOrder(id);
    }
}