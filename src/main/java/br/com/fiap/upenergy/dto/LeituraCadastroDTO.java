package br.com.fiap.upenergy.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeituraCadastroDTO {

    Long leituraId;
    Long sensorId;
    double consumoKw;

}
