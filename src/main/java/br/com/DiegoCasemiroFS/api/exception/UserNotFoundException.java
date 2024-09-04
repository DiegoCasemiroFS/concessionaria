package br.com.DiegoCasemiroFS.api.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){
        super("Usuario não Encontrado, informe um Id válido!");
    }
}
