package br.com.fiap.upenergy.model;

import br.com.fiap.upenergy.model.enums.NivelAlerta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_ALERTA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Alerta {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ALERTAS"
    )
    @SequenceGenerator(
            name = "SEQ_ALERTAS",
            sequenceName = "SEQ_ALERTAS",
            allocationSize = 1
    )
    @Column(name = "alerta_id")
    private Long alertaId;
    @Enumerated(EnumType.STRING)
    private NivelAlerta nivel;
    @ManyToOne
    @JoinColumn(name = "equipamento_id", nullable = false)
    private Equipamento equipamento;
    private LocalDateTime dataHora;

}
