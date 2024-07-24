package br.com.DiegoCasemiroFS.api.service;

import br.com.DiegoCasemiroFS.api.entity.Veiculo;
import br.com.DiegoCasemiroFS.api.exception.VeiculoException;
import br.com.DiegoCasemiroFS.api.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public List<Veiculo> listVeiculos(){
        return veiculoRepository.findAllByOrderByPrecoAsc();
    }

    public Veiculo findById(Long id){
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoException());
    }

    public Veiculo cadastraVeiculo(Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizaVeiculo(Long id, Veiculo veiculo){
        return veiculoRepository.findById(id)
                .map(f -> {
                    veiculoRepository.findById(id);
                    veiculoRepository.save(veiculo);
                    return veiculo;
                }).orElseThrow(() -> new VeiculoException());
    }

    public void deletaVeiculo(Long id){
        veiculoRepository.findById(id)
                .map(f -> {
                    veiculoRepository.delete(f);
                    return Void.TYPE;
                }).orElseThrow(() -> new VeiculoException());
    }
}
