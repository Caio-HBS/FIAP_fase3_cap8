package br.com.fiap.upenergy.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "T_LEITURA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Leitura {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_LEITURAS"
    )
    @SequenceGenerator(
            name = "SEQ_LEITURAS",
            sequenceName = "SEQ_LEITURAS",
            allocationSize = 1
    )
    @Column(name = "leitura_id")
    private Long leituraId;
    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor;
    private LocalDateTime dataHora;
    private double consumoKw;

}
