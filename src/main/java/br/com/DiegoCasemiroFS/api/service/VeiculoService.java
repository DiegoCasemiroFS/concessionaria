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
                .orElseThrow(VeiculoException::new);
    }

    public Veiculo cadastraVeiculo(Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizaVeiculo(Long id, Veiculo veiculo) {
        return veiculoRepository.findById(id)
                .map(f -> {
                    f.setNome(veiculo.getNome());
                    f.setMarca(veiculo.getMarca());
                    f.setModelo(veiculo.getModelo());
                    f.setAno(veiculo.getAno());
                    f.setPreco(veiculo.getPreco());
                    f.setTipoVeiculo(veiculo.getTipoVeiculo());

                    return veiculoRepository.save(f);
                })
                .orElseThrow(() -> new VeiculoException());
    }

    public void deletaVeiculo(Long id){
        veiculoRepository.findById(id)
                .map(f -> {
                    veiculoRepository.delete(f);
                    return Void.TYPE;
                }).orElseThrow(() -> new VeiculoException());
    }
}
