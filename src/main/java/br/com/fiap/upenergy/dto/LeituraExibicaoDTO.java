package br.com.fiap.upenergy.dto;

import br.com.fiap.upenergy.model.Leitura;

public record LeituraExibicaoDTO(
        Long leituraId,
        Long sensorId,
        double consumoKw
) {

    public LeituraExibicaoDTO(Leitura leitura) {
        this(
                leitura.getLeituraId(),
                leitura.getSensor().getSensorId(),
                leitura.getConsumoKw()
        );
    }

}
