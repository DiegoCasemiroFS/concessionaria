package br.com.DiegoCasemiroFS.api.exception;

public class UpdateOrderException extends RuntimeException {

    public UpdateOrderException(){
        super("Não foi possível alterar o Usuário da requisição, informe um Id válido!");
    }
}

