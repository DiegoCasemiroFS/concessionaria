package br.com.DiegoCasemiroFS.api.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(){
        super("Pedido não encontrado, informe um Id válido!");
    }
}

