package br.com.fiap.upenergy.controller;


import br.com.fiap.upenergy.dto.AlertaExibicaoDTO;
import br.com.fiap.upenergy.service.AlertaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1")
public class AlertaController {

    AlertaService alertaService;

    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    @GetMapping(path="/alertas")
    public List<AlertaExibicaoDTO> listarAlertas() {
        return alertaService.listarAlertas();
    }

}
