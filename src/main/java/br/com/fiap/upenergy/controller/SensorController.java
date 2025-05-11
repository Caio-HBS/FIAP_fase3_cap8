package br.com.fiap.upenergy.controller;

import br.com.fiap.upenergy.dto.SensorCadastroDTO;
import br.com.fiap.upenergy.dto.SensorExibicaoDTO;
import br.com.fiap.upenergy.model.RespostaGeneric;
import br.com.fiap.upenergy.model.Sensor;
import br.com.fiap.upenergy.service.SensorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping(path="/sensores")
    public ResponseEntity<RespostaGeneric> cadastrarSensor(@Valid @RequestBody SensorCadastroDTO sensorCadastroDTO) {

        Sensor sensorNovo = sensorService.cadastrarSensor(sensorCadastroDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(sensorNovo.getSensorId())
                .toUri();

        RespostaGeneric response = new RespostaGeneric("Sensor cadastrado com sucesso!");
        return ResponseEntity.created(uri).body(response);

    }

    @GetMapping(path="sensores/{equipamentoId}")
    public ResponseEntity<List<SensorExibicaoDTO>> listarSensores(@PathVariable Long equipamentoId) {
        return ResponseEntity.ok(sensorService.listarSensores(equipamentoId));
    }

}
