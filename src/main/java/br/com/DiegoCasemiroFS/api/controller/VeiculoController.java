package br.com.DiegoCasemiroFS.api.controller;

import br.com.DiegoCasemiroFS.api.entity.Veiculo;
import br.com.DiegoCasemiroFS.api.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @GetMapping("/listagem")
    public List<Veiculo> listVeiculos(){
        return veiculoService.listVeiculos();
    }

    @GetMapping("/id")
    public Veiculo findById(@PathVariable Long id){
        return veiculoService.findById(id);
    }

    @GetMapping("/marca")
    public List<Veiculo> findByMarca(@RequestBody String marca){
        return veiculoService.findByMarca(marca);
    }

    @GetMapping("/nome")
    public List<Veiculo> findByNome(@RequestBody String nome){
        return veiculoService.findByNome(nome);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Veiculo cadastra(@RequestBody Veiculo veiculo){
        return veiculoService.cadastraVeiculo(veiculo);
    }

    @PutMapping
    public Veiculo atualiza(@PathVariable Long id, @RequestBody Veiculo veiculo){
        return veiculoService.atualizaVeiculo(id, veiculo);
    }

    @DeleteMapping
    public void deleta(@PathVariable Long id){
        veiculoService.deletaVeiculo(id);
    }
}
