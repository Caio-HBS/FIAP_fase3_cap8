package br.com.fiap.upenergy.controller;

import br.com.fiap.upenergy.dto.UsuarioCadastroDTO;
import br.com.fiap.upenergy.model.RespostaGeneric;
import br.com.fiap.upenergy.model.Usuario;
import br.com.fiap.upenergy.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path="/api/v1")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(path="/usuarios")
    public ResponseEntity<RespostaGeneric> criarUsuario(
            @Valid @RequestBody UsuarioCadastroDTO usuario
    ) {

        Usuario usuarioNovo = usuarioService.criarUsuario(usuario);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuarioNovo.getUsuarioId())
                .toUri();

        RespostaGeneric response = new RespostaGeneric("Usuário criado com sucesso!");
        return ResponseEntity.created(uri).body(response);

    }

    @DeleteMapping(path="usuarios/{usuarioId}")
    public ResponseEntity<RespostaGeneric> excluirUsuario(
            @PathVariable Long usuarioId
    ) {

        usuarioService.excluirUsuario(usuarioId);

        RespostaGeneric response = new RespostaGeneric("Usuário excluído com sucesso.");
        return ResponseEntity.ok(response);

    }

}
