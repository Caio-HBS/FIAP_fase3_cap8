package br.com.fiap.upenergy.service;

import br.com.fiap.upenergy.dto.SensorCadastroDTO;
import br.com.fiap.upenergy.dto.SensorExibicaoDTO;
import br.com.fiap.upenergy.exception.RecursoNaoEncontradoException;
import br.com.fiap.upenergy.model.Equipamento;
import br.com.fiap.upenergy.model.Sensor;
import br.com.fiap.upenergy.repository.EquipamentoRepository;
import br.com.fiap.upenergy.repository.SensorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    SensorRepository sensorRepository;
    EquipamentoRepository equipamentoRepository;

    public SensorService(SensorRepository sensorRepository, EquipamentoRepository equipamentoRepository) {
        this.sensorRepository = sensorRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public Sensor cadastrarSensor(SensorCadastroDTO sensorCadastroDTO) {

        Optional<Equipamento> procurarEquipamento = equipamentoRepository.findById(sensorCadastroDTO.getEquipamentoId());

        if (!procurarEquipamento.isPresent()) {
            throw new RecursoNaoEncontradoException("Equipamento não encontrado.");
        }

        Sensor novoSensor = new Sensor();
        BeanUtils.copyProperties(sensorCadastroDTO, novoSensor);
        novoSensor.setEquipamentoId(procurarEquipamento.get());

        if (sensorCadastroDTO.getAtivo() == null) {
            novoSensor.setAtivo(true);
        }

        return sensorRepository.save(novoSensor);

    }

    public List<SensorExibicaoDTO> listarSensores(Long equipamentoId) {
        return sensorRepository.findByEquipamentoId(equipamentoId).stream().map(SensorExibicaoDTO::new).toList();
    }

}
