package br.com.fiap.upenergy.service;

import br.com.fiap.upenergy.dto.AlertaExibicaoDTO;
import br.com.fiap.upenergy.repository.AlertaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaService {

    AlertaRepository alertaRepository;

    public AlertaService(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    public List<AlertaExibicaoDTO> listarAlertas() {
        return alertaRepository.findAll().stream().map(AlertaExibicaoDTO::new).toList();
    }

}
