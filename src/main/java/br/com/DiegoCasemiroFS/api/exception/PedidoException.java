package br.com.DiegoCasemiroFS.api.exception;

public class PedidoException extends RuntimeException {

    public PedidoException(){
        super("Pedido não encontrado");
    }
}

