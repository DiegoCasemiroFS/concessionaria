package br.com.DiegoCasemiroFS.api.exception;

public class CadastroException extends RuntimeException {

    public CadastroException(){
        super("Email ou Senha incorretos");
    }
}
