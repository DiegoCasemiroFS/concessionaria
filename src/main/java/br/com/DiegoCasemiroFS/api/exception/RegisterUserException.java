package br.com.DiegoCasemiroFS.api.exception;

public class RegisterUserException extends RuntimeException {

    public RegisterUserException(){
        super("Este Email já foi cadastrado!");
    }
}
