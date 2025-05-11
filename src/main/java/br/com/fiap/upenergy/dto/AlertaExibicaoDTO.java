package br.com.fiap.upenergy.dto;

import br.com.fiap.upenergy.model.Alerta;
import br.com.fiap.upenergy.model.enums.NivelAlerta;

import java.time.LocalDateTime;

public record AlertaExibicaoDTO(
        Long alertaId,
        Long equipamentoId,
        NivelAlerta nivel,
        LocalDateTime dataHora
) {

    public AlertaExibicaoDTO(Alerta alerta) {
        this(
                alerta.getAlertaId(),
                alerta.getEquipamento().getEquipamentoId(),
                alerta.getNivel(),
                alerta.getDataHora()
        );
    }

}
