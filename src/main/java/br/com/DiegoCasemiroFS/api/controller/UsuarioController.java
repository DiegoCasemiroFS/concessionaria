package br.com.DiegoCasemiroFS.api.controller;

import br.com.DiegoCasemiroFS.api.entity.Usuario;
import br.com.DiegoCasemiroFS.api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/{findAll}")
    public List<Usuario> findAll(){
        return usuarioService.findAll();
    }

    @GetMapping
    public Usuario findById(@PathVariable Long id){
        return usuarioService.findById(id);
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){
        return usuarioService.createUsuario(usuario);
    }

    @PutMapping
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        return usuarioService.updateUsuario(id, usuario);
    }

    @DeleteMapping
    public void deleteUsuario(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
    }
}
