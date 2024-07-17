package com.fiap.parquimetro.dominio.gerencimantoCadastro.estacionamento.entity;

import com.fiap.parquimetro.dominio.gerencimantoCadastro.estacionamento.dto.DadosEstacionamento;
import com.fiap.parquimetro.dominio.util.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Estacionamento", schema = "controle_estacionamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Estacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "est_id")
    private Long idEstacionamento;

    @NotEmpty
    @Column(name = "est_nome", nullable = false)
    @Length(min = 5)
    private String nomeEstacionamento;

    @NotEmpty
    @Column(name = "est_tarifa", nullable = false)
    private float tarifa;

    @NotEmpty
    @Column(name = "est_tipoTarifa", nullable = false)
    private char tipoTarifa;

    @NotEmpty
    @Column(name = "est_chavePIX", nullable = false)
    private String chavePIX;

    @Embedded
    private Endereco endereco;

    public Estacionamento(DadosEstacionamento dadosEstacionamento){
        this.nomeEstacionamento= dadosEstacionamento.nomeEstacionamento();
        this.tarifa = dadosEstacionamento.tarifa();
        this.tipoTarifa = dadosEstacionamento.tipoTarifa();
        this.chavePIX = dadosEstacionamento.chavePIX();
        this.endereco = dadosEstacionamento.endereco();
    }
}
