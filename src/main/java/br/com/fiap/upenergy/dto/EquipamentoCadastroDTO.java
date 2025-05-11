package br.com.fiap.upenergy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipamentoCadastroDTO {

    Long equipamentoId;
    Long usuarioId;
    @NotBlank(message="É obrigatório informar um nome para o equipamento.")
    String nome;
    @NotBlank(message="É obrigatório informar a localização do equipamento.")
    String localizacao;
    double consumoMedioKw;

}
