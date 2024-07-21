package br.com.DiegoCasemiroFS.api.controller;

import br.com.DiegoCasemiroFS.api.entity.Veiculo;
import br.com.DiegoCasemiroFS.api.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService veiculoService;

    @GetMapping
    public List<Veiculo> listVeiculos(){
        return veiculoService.listVeiculos();
    }

    @GetMapping
    public Veiculo findById(Long id){
        return veiculoService.findById(id);
    }

    @GetMapping
    public List<Veiculo> findByMarca(String marca){
        return veiculoService.findByMarca(marca);
    }

    @GetMapping
    public List<Veiculo> findByNome(String nome){
        return veiculoService.findByNome(nome);
    }

    @PostMapping
    public Veiculo cadastroVeiculo(Veiculo veiculo){
        return veiculoService.cadastroVeiculo(veiculo);
    }

    @PutMapping
    public Veiculo updateVeiculo(Long id, Veiculo veiculo){
        return veiculoService.updateVeiculo(id, veiculo);
    }

    @DeleteMapping
    public void deleteVeiculo(Long id){
        veiculoService.deleteVeiculo(id);
    }
}
