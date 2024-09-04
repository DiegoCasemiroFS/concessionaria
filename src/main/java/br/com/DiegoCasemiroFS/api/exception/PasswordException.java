package br.com.DiegoCasemiroFS.api.exception;

public class PasswordException extends RuntimeException {

    public PasswordException(){
        super("Senha incorreta!");
    }
}
