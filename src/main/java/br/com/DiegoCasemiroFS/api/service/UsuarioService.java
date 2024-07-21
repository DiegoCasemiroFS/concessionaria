package br.com.DiegoCasemiroFS.api.service;

import br.com.DiegoCasemiroFS.api.entity.Usuario;
import br.com.DiegoCasemiroFS.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Codigo do Usuario não encontrado."));
    }

    public Usuario createUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuario){
        return usuarioRepository.findById(id)
                .map(f -> {
                    usuario.setId(f.getId());
                    usuarioRepository.save(usuario);
                    return usuario;
                }).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    public void deleteUsuario(Long id){
        usuarioRepository.findById(id)
                .map(f -> {
                    usuarioRepository.delete(f);
                    return Void.TYPE;
                }).orElseThrow(() -> new RuntimeException("Codigo do Usuario não encotnrado"));
    }
}
