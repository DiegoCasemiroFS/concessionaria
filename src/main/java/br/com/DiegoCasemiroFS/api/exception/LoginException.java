package br.com.DiegoCasemiroFS.api.exception;

public class LoginException extends RuntimeException {

    public LoginException(){
        super("Email ou Senha incorretos");
    }
}
