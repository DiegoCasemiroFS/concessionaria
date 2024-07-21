package br.com.DiegoCasemiroFS.api.exception;

public class UsuarioException extends RuntimeException {

    public UsuarioException(){
        super("Usuario não Encontrado");
    }
}
