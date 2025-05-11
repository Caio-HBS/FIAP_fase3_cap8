package br.com.fiap.upenergy.service;

import br.com.fiap.upenergy.dto.LeituraCadastroDTO;
import br.com.fiap.upenergy.dto.LeituraExibicaoDTO;
import br.com.fiap.upenergy.exception.RecursoNaoEncontradoException;
import br.com.fiap.upenergy.model.Leitura;
import br.com.fiap.upenergy.model.Sensor;
import br.com.fiap.upenergy.repository.LeituraRepository;
import br.com.fiap.upenergy.repository.SensorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LeituraService {

    LeituraRepository leituraRepository;
    SensorRepository sensorRepository;

    public LeituraService(LeituraRepository leituraRepository, SensorRepository sensorRepository) {
        this.leituraRepository = leituraRepository;
        this.sensorRepository = sensorRepository;
    }

    public Leitura cadastrarLeitura(LeituraCadastroDTO leituraCadastroDTO) {

        Optional<Sensor> procurarSensor = sensorRepository.findById(leituraCadastroDTO.getSensorId());

        if (!procurarSensor.isPresent()) {
            throw new RecursoNaoEncontradoException("Sensor não encontrado.");
        }

        Leitura novaLeitura = new Leitura();
        BeanUtils.copyProperties(leituraCadastroDTO, novaLeitura);
        novaLeitura.setSensor(procurarSensor.get());
        novaLeitura.setDataHora(LocalDateTime.now());

        return leituraRepository.save(novaLeitura);

    }

    public List<LeituraExibicaoDTO> listarLeituras(Long sensorId) {
        return leituraRepository.findBySensorId(sensorId).stream().map(LeituraExibicaoDTO::new).toList();
    }

}
