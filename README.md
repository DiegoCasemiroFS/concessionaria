# Concessionária
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

## Sobre o projeto

Esta API simula um aplicativo para a venda de Veículos. Ela foi desenvolvida utilizando o Spring Framework
e oferece funcionalidades para o gerenciamento de Usuários, Veículos e Pedidos.

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
    class Usuario{
        +Long id;
        +String name;
        +String email;
        +String senha;
        +boolean admin;
        
        +loadUserByUsername(String username)
        +login(LoginRequestDto body)
        +registro(UsuarioRequestDto body)
    }
    
    class Veiculo{
        +Long id;
        +String nome;
        +String marca;
        +Long modelo;
        +Long ano;
        +Double preco;
        +TipoVeiculo tipoVeiculo;
        
        +istVeiculos()
        +findById(Long id)
        +cadastraVeiculo(Veiculo veiculo)
        +atualizaVeiculo(Long id, Veiculo veiculo)
        +deletaVeiculo(Long id)
    }
    
    class Pedido{
        +Long id;
        +Veiculo veiculo;
        +Usuario usuario;
        +LocalDate dataCadastro;
        
        +findById(Long id)
        +cadastraPedido(PedidoRequestDto pedidoDto)
        +atualizaPedido(Long id, PedidoRequestDto pedidoDto)
        +deletaPedido(Long id)
    }
    
    Usuario "1" --> "n" Pedido
    Veiculo "1" --> "n" Pedido
```