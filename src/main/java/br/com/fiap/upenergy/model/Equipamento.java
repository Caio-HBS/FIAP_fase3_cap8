package br.com.fiap.upenergy.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "T_EQUIPAMENTO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Equipamento {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "SEQ_EQUIPAMENTOS"
    )
    @SequenceGenerator(
        name = "SEQ_EQUIPAMENTOS",
        sequenceName = "SEQ_EQUIPAMENTOS",
        allocationSize = 1
    )
    @Column(name = "equipamento_id")
    private Long equipamentoId;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable=false)
    private Usuario usuario;
    private String nome;
    private String localizacao;
    private double consumoMedioKw;

}