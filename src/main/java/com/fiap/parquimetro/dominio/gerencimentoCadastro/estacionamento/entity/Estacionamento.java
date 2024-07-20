package com.fiap.parquimetro.dominio.gerencimentoCadastro.estacionamento.entity;

import com.fiap.parquimetro.dominio.gerencimentoCadastro.estacionamento.dto.DadosEstacionamento;
import com.fiap.parquimetro.dominio.util.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Entity
@Table(name = "estacionamento", schema = "controle_estacionamento")
@Cacheable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Estacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "est_id")
    private Long id;

    @NotEmpty
    @Column(name = "est_nome", nullable = false)
    @Length(min = 5)
    private String nome;

    @NotEmpty
    @Column(name = "est_tarifa", nullable = false)
    private BigDecimal tarifa;

    @NotEmpty
    @Column(name = "est_tipoTarifa", nullable = false)
    private char tipoTarifa;

    @NotEmpty
    @Column(name = "est_chavePIX", nullable = false)
    private String chavePIX;

    @Embedded
    private Endereco endereco;

    public Estacionamento(DadosEstacionamento dadosEstacionamento){
        this.nome = dadosEstacionamento.nomeEstacionamento();
        this.tarifa = dadosEstacionamento.tarifa();
        this.tipoTarifa = dadosEstacionamento.tipoTarifa();
        this.chavePIX = dadosEstacionamento.chavePIX();
        this.endereco = dadosEstacionamento.endereco();
    }
}
