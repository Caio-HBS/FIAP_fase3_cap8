package br.com.fiap.upenergy.controller;

import br.com.fiap.upenergy.dto.EquipamentoCadastroDTO;
import br.com.fiap.upenergy.dto.EquipamentoExibicaoDTO;
import br.com.fiap.upenergy.model.Equipamento;
import br.com.fiap.upenergy.model.RespostaGeneric;
import br.com.fiap.upenergy.service.EquipamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @PostMapping(path="/equipamentos")
    public ResponseEntity<RespostaGeneric> cadastrarEquipamento(@Valid @RequestBody EquipamentoCadastroDTO equipamentoCadastroDTO) {

        Equipamento equipamentoNovo = equipamentoService.cadastrarEquipamento(equipamentoCadastroDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(equipamentoNovo.getEquipamentoId())
                .toUri();

        RespostaGeneric response = new RespostaGeneric("Equipamento cadastrado com sucesso!");
        return ResponseEntity.created(uri).body(response);

    }

    @GetMapping(path="/equipamentos/{usuarioId}")
    public ResponseEntity<List<EquipamentoExibicaoDTO>> listarEquipamentos(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(equipamentoService.listarEquipamentos(usuarioId));
    }

}
