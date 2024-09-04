package br.com.DiegoCasemiroFS.api.exception;

public class VehicleNotFoundException extends RuntimeException{

    public VehicleNotFoundException(){
        super("Veículo não encontrado, informe um Id válido!");
    }
}
