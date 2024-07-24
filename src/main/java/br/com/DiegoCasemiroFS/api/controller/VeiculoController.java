package br.com.DiegoCasemiroFS.api.controller;

import br.com.DiegoCasemiroFS.api.entity.Veiculo;
import br.com.DiegoCasemiroFS.api.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @PostMapping("/cadastra")
    public Veiculo cadastra(@RequestBody Veiculo veiculo){
        return veiculoService.cadastraVeiculo(veiculo);
    }

    @PutMapping(("/{id}"))
    public Veiculo atualiza(@PathVariable Long id, @RequestBody Veiculo veiculo){
        return veiculoService.atualizaVeiculo(id, veiculo);
    }

    @DeleteMapping(("/{id}"))
    public void deleta(@PathVariable Long id){
        veiculoService.deletaVeiculo(id);
    }
}
