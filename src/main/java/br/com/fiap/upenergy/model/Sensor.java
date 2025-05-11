package br.com.fiap.upenergy.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_SENSOR")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Sensor {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_SENSORES"
    )
    @SequenceGenerator(
            name = "SEQ_SENSORES",
            sequenceName = "SEQ_SENSORES",
            allocationSize = 1
    )
    @Column(name = "sensor_id")
    private Long sensorId;
    @Column(unique = true, nullable = false)
    private String codigoSerial;
    @ManyToOne
    @JoinColumn(name = "equipamento_id", nullable = false)
    private Equipamento equipamentoId;
    private String tipo;
    private Boolean ativo;

}
