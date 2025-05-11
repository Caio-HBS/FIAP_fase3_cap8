package br.com.fiap.upenergy.dto;

import br.com.fiap.upenergy.model.Equipamento;

public record EquipamentoExibicaoDTO(
        Long equipamentoId,
        Long usuarioId,
        String nome,
        String localizacao,
        double consumoMedioKw
) {

    public EquipamentoExibicaoDTO(Equipamento equipamento) {
        this(
                equipamento.getEquipamentoId(),
                equipamento.getUsuario().getUsuarioId(),
                equipamento.getNome(),
                equipamento.getLocalizacao(),
                equipamento.getConsumoMedioKw()
        );
    }

}
