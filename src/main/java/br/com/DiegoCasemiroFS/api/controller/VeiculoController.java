package br.com.DiegoCasemiroFS.api.controller;

import br.com.DiegoCasemiroFS.api.entity.Veiculo;
import br.com.DiegoCasemiroFS.api.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @GetMapping("/{findAll}")
    public List<Veiculo> listVeiculos(){
        return veiculoService.listVeiculos();
    }

    @GetMapping("/{id}")
    public Veiculo findById(@PathVariable Long id){
        return veiculoService.findById(id);
    }

    @GetMapping("/{marca}")
    public List<Veiculo> findByMarca(@RequestBody String marca){
        return veiculoService.findByMarca(marca);
    }

    @GetMapping("/{nome}")
    public List<Veiculo> findByNome(@RequestBody String nome){
        return veiculoService.findByNome(nome);
    }

    @PostMapping
    public Veiculo cadastroVeiculo(@RequestBody Veiculo veiculo){
        return veiculoService.cadastroVeiculo(veiculo);
    }

    @PutMapping
    public Veiculo updateVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo){
        return veiculoService.updateVeiculo(id, veiculo);
    }

    @DeleteMapping
    public void deleteVeiculo(@PathVariable Long id){
        veiculoService.deleteVeiculo(id);
    }
}
