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

    public List<Veiculo> findByMarca(String marca){
        return veiculoRepository.findByMarcaContainingIgnoreCase(marca);
    }

    public List<Veiculo> findByNome(String nome){
        return veiculoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Veiculo cadastroVeiculo(Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }

    public Veiculo updateVeiculo(Long id, Veiculo veiculo){
        return veiculoRepository.findById(id)
                .map(f -> {
                    veiculoRepository.findById(id);
                    veiculoRepository.save(veiculo);
                    return veiculo;
                }).orElseThrow(() -> new VeiculoException());
    }

    public void deleteVeiculo(Long id){
        veiculoRepository.findById(id)
                .map(f -> {
                    veiculoRepository.delete(f);
                    return Void.TYPE;
                }).orElseThrow(() -> new VeiculoException());
    }
}
