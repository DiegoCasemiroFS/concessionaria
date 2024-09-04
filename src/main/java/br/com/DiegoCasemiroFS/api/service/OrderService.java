package br.com.DiegoCasemiroFS.api.service;

import br.com.DiegoCasemiroFS.api.entity.Orders;
import br.com.DiegoCasemiroFS.api.entity.Users;
import br.com.DiegoCasemiroFS.api.entity.Vehicle;
import br.com.DiegoCasemiroFS.api.entity.dto.OrderRequestDto;
import br.com.DiegoCasemiroFS.api.entity.dto.OrderResponseDto;
import br.com.DiegoCasemiroFS.api.exception.UpdateOrderException;
import br.com.DiegoCasemiroFS.api.exception.OrderNotFoundException;
import br.com.DiegoCasemiroFS.api.exception.UserNotFoundException;
import br.com.DiegoCasemiroFS.api.exception.VehicleNotFoundException;
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
        Orders existingOrders = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);

        OrderResponseDto responseDto = new OrderResponseDto();

        responseDto.setId(existingOrders.getId());
        responseDto.setVehicleName(existingOrders.getVehicle().getName());
        responseDto.setUsername(existingOrders.getUsers().getName());
        responseDto.setPrice(existingOrders.getVehicle().getPrice());
        responseDto.setRegisterDate(existingOrders.getRegisterDate());

        return responseDto;
    }


    public OrderResponseDto registerOrder(OrderRequestDto requestDto) {
        Vehicle vehicle = vehicleRepository.findById(requestDto.getVehicleId()).orElseThrow(VehicleNotFoundException::new);
        Users users = userRepository.findById(requestDto.getUserId()).orElseThrow(UserNotFoundException::new);

        Orders orders = new Orders();
        orders.setVehicle(vehicle);
        orders.setUsers(users);
        orders.setRegisterDate(LocalDateTime.now());

        Orders savedOrders = orderRepository.save(orders);

        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(savedOrders.getId());
        responseDto.setVehicleName(savedOrders.getVehicle().getName());
        responseDto.setUsername(savedOrders.getUsers().getName());
        responseDto.setPrice(savedOrders.getVehicle().getPrice());
        responseDto.setRegisterDate(savedOrders.getRegisterDate());

        return responseDto;
    }

    public OrderResponseDto updateOrder(Long id, OrderRequestDto requestDto) {
        Orders orders = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        Vehicle vehicle = vehicleRepository.findById(requestDto.getVehicleId()).orElseThrow(VehicleNotFoundException::new);
        Users users = userRepository.findById(requestDto.getUserId()).orElseThrow(UserNotFoundException::new);

        if (requestDto.getUserId().equals(orders.getUsers().getId())){

            orders.setVehicle(vehicle);
            orders.setUsers(users);
            orders.setRegisterDate(LocalDateTime.now());

            Orders updatedOrders = orderRepository.save(orders);

            OrderResponseDto responseDto = new OrderResponseDto();
            responseDto.setId(updatedOrders.getId());
            responseDto.setVehicleName(updatedOrders.getVehicle().getName());
            responseDto.setUsername(updatedOrders.getUsers().getName());
            responseDto.setPrice(updatedOrders.getVehicle().getPrice());
            responseDto.setRegisterDate(updatedOrders.getRegisterDate());

            return responseDto;
        }
        throw new UpdateOrderException();
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}