package br.com.DiegoCasemiroFS.api.exception;

public class AtualizaPedidoException extends RuntimeException {

    public AtualizaPedidoException(){
        super("Não é possível alterar o Usuário da requisição");
    }
}

