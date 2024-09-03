package br.com.DiegoCasemiroFS.api.service;

import br.com.DiegoCasemiroFS.api.entity.Orders;
import br.com.DiegoCasemiroFS.api.entity.Users;
import br.com.DiegoCasemiroFS.api.entity.Vehicle;
import br.com.DiegoCasemiroFS.api.entity.dto.OrderRequestDto;
import br.com.DiegoCasemiroFS.api.entity.dto.OrderResponseDto;
import br.com.DiegoCasemiroFS.api.exception.AtualizaPedidoException;
import br.com.DiegoCasemiroFS.api.exception.PedidoException;
import br.com.DiegoCasemiroFS.api.exception.UsuarioException;
import br.com.DiegoCasemiroFS.api.exception.VeiculoException;
import br.com.DiegoCasemiroFS.api.repository.OrderRepository;
import br.com.DiegoCasemiroFS.api.repository.UserRepository;
import br.com.DiegoCasemiroFS.api.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    public OrderResponseDto findById(Long id) {
        Orders existingOrders = orderRepository.findById(id).orElseThrow(PedidoException::new);

        OrderResponseDto responseDto = new OrderResponseDto();

        responseDto.setId(existingOrders.getId());
        responseDto.setNomeVeiculo(existingOrders.getVehicle().getName());
        responseDto.setNomeUsuario(existingOrders.getUsers().getName());
        responseDto.setPreco(existingOrders.getVehicle().getPrice());
        responseDto.setDataCadastro(existingOrders.getRegisterDate());

        return responseDto;
    }


    public OrderResponseDto registerOrder(OrderRequestDto requestDto) {
        Vehicle vehicle = vehicleRepository.findById(requestDto.getVeiculoId()).orElseThrow(VeiculoException::new);
        Users users = userRepository.findById(requestDto.getUsuarioId()).orElseThrow(UsuarioException::new);

        Orders orders = new Orders();
        orders.setVehicle(vehicle);
        orders.setUsers(users);
        orders.setRegisterDate(LocalDateTime.now());

        Orders savedOrders = orderRepository.save(orders);

        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(savedOrders.getId());
        responseDto.setNomeVeiculo(savedOrders.getVehicle().getName());
        responseDto.setNomeUsuario(savedOrders.getUsers().getName());
        responseDto.setPreco(savedOrders.getVehicle().getPrice());
        responseDto.setDataCadastro(savedOrders.getRegisterDate());

        return responseDto;
    }

    public OrderResponseDto updateOrder(Long id, OrderRequestDto requestDto) {
        Orders orders = orderRepository.findById(id).orElseThrow(PedidoException::new);
        Vehicle vehicle = vehicleRepository.findById(requestDto.getVeiculoId()).orElseThrow(VeiculoException::new);
        Users users = userRepository.findById(requestDto.getUsuarioId()).orElseThrow(UsuarioException::new);

        if (requestDto.getUsuarioId().equals(orders.getUsers().getId())){

            orders.setVehicle(vehicle);
            orders.setUsers(users);
            orders.setRegisterDate(LocalDateTime.now());

            Orders updatedOrders = orderRepository.save(orders);

            OrderResponseDto responseDto = new OrderResponseDto();
            responseDto.setId(updatedOrders.getId());
            responseDto.setNomeVeiculo(updatedOrders.getVehicle().getName());
            responseDto.setNomeUsuario(updatedOrders.getUsers().getName());
            responseDto.setPreco(updatedOrders.getVehicle().getPrice());
            responseDto.setDataCadastro(updatedOrders.getRegisterDate());

            return responseDto;
        }
        throw new AtualizaPedidoException();
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}