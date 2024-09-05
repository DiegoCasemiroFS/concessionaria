# Concessionária
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

## Sobre o projeto

- Esta API simula um aplicativo para a venda de Veículos. Ela foi desenvolvida utilizando o Spring Framework
e oferece funcionalidades para o gerenciamento de Usuários, Veículos e Pedidos.

## Instruções de Uso

- Para manuseio da API é necessário que criar um Usuário e fazer login com o mesmo.
- Somente Usuários do tipo admin podem cadastrar, atualizar ou deletar Veículos.
- Todos os Usuários podem passear pela Vitrine ou realizar um Pedido.
- Para realizar um Pedido, basta informar o Id do Usuário e do Veículo.
- Para atualizar um Pedido, o Id do Usuário não pode ser alterado, somente o Id do Veículo.

## Tecnologias Utilizadas

- Spring WEB
- Spring Data JPA
- Spring Security
- Spring Documentation
- Lombok
- H2 Database
- Autenticação JWT

## Download

```bash
git clone https://github.com/DiegoCasemiroFS/concessionaria.git
```

## Documentação 

```bash
http://localhost:8080/v3/api-docs
```

```bash
http://localhost:8080/swagger-ui/index.html
```

## Diagrama das Classes

```mermaid
classDiagram
    class User{
        +Long id;
        +String name;
        +String email;
        +String password;
        +boolean admin;
        
        +login(LoginRequestDto requestDto)
        +registerUser(UserRequestDto requestDto)
        +updateUser(Long id, UserRequestDto requestDto)
    }
    
    class Vehicle{
        +Long id;
        +String name;
        +String brand;
        +Long model;
        +Long carYear;
        +Double price;
        +VehicleType vehicleType;
        
        +findAll()
        +findById(Long id)
        +registerVehicle(VehicleRequestDto requestDto)
        +updateVehicle(Long id, VehicleRequestDto requestDto)
        +deleteVehicle(Long id)
    }
    
    class Order{
        +Long id;
        +Veiculo vehicle;
        +Usuario users;
        +LocalDate dataCadastro;
        
        +findById(Long id)
        +registerOrder(OrderRequestDto requestDto)
        +updateOrder(Long id, OrderRequestDto requestDto)
        +deleteOrder(Long id)
    }
    
    User "1" --> "n" Order
    Vehicle "1" --> "n" Order
```
