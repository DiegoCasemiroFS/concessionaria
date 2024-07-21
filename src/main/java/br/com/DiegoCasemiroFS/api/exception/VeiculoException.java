package br.com.DiegoCasemiroFS.api.exception;

public class VeiculoException extends RuntimeException{

    public VeiculoException(){
        super("Veículo não encontrado");
    }
}
