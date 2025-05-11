package br.com.fiap.upenergy.dto;

import br.com.fiap.upenergy.model.Sensor;

public record SensorExibicaoDTO(
        Long sensorId,
        String codigoSerial,
        Long equipamentoId,
        String tipo,
        Boolean ativo
) {

    public SensorExibicaoDTO(Sensor sensor) {
        this(
                sensor.getSensorId(),
                sensor.getCodigoSerial(),
                sensor.getEquipamentoId().getEquipamentoId(),
                sensor.getTipo(),
                sensor.getAtivo()
        );
    }

}
