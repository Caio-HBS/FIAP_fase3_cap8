package br.com.fiap.upenergy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorCadastroDTO {

    private Long sensorId;
    @NotBlank(message="É obrigatório informar o código serial do sensor.")
    private String codigoSerial;
    private Long equipamentoId;
    @NotBlank(message="É obrigatório informar o tipo de sensor.")
    private String tipo;
    private Boolean ativo;

}
