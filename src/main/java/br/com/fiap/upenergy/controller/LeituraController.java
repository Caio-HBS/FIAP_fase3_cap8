package br.com.fiap.upenergy.controller;

import br.com.fiap.upenergy.dto.LeituraCadastroDTO;
import br.com.fiap.upenergy.dto.LeituraExibicaoDTO;
import br.com.fiap.upenergy.model.Leitura;
import br.com.fiap.upenergy.model.RespostaGeneric;
import br.com.fiap.upenergy.service.LeituraService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1")
public class LeituraController {

    private final LeituraService leituraService;

    public LeituraController(LeituraService leituraService) {
        this.leituraService = leituraService;
    }

    @PostMapping("/leituras")
    public ResponseEntity<RespostaGeneric> cadastrarLeitura(@Valid @RequestBody LeituraCadastroDTO leituraCadastroDTO) {

        Leitura novaLeitura = leituraService.cadastrarLeitura(leituraCadastroDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novaLeitura.getLeituraId())
                .toUri();

        RespostaGeneric response = new RespostaGeneric("Leitura cadastrada com sucesso!");
        return ResponseEntity.created(uri).body(response);

    }

    @GetMapping(path="/leituras/{sensorId}")
    public ResponseEntity<List<LeituraExibicaoDTO>> listarLeituras(@PathVariable Long sensorId) {
        return ResponseEntity.ok(leituraService.listarLeituras(sensorId));
    }

}
