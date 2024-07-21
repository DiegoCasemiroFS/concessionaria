package br.com.DiegoCasemiroFS.api.controller;

import br.com.DiegoCasemiroFS.api.entity.Usuario;
import br.com.DiegoCasemiroFS.api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> findAll(){
        return usuarioService.findAll();
    }

    @GetMapping
    public Usuario findById(Long id){
        return usuarioService.findById(id);
    }

    @PostMapping
    public Usuario createUsuario(Usuario usuario){
        return usuarioService.createUsuario(usuario);
    }

    @PutMapping
    public Usuario updateUsuario(Long id, Usuario usuario){
        return usuarioService.updateUsuario(id, usuario);
    }

    @DeleteMapping
    public void deleteUsuario(Long id){
        usuarioService.deleteUsuario(id);
    }
}
