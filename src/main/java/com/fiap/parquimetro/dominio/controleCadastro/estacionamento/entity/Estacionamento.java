package com.fiap.parquimetro.dominio.controleCadastro.estacionamento.entity;

import com.fiap.parquimetro.dominio.controleCadastro.estacionamento.dto.DadosEstacionamento;
import com.fiap.parquimetro.dominio.util.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Entity
@Table(name = "estacionamento",
        indexes = {@Index(name = "uk_est_nome", columnList = "est_nome", unique = true)}
)
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
    @Column(name = "est_nome", nullable = false, unique = true)
    @Length(min = 5)
    private String nome;

    @NotNull
    @Column(name = "est_tarifa", nullable = false)
    private BigDecimal tarifa;

    @NotEmpty
    @Column(name = "est_chave_pix", nullable = false)
    private String chavePIX;

    @Embedded
    private Endereco endereco;

    public Estacionamento(DadosEstacionamento dadosEstacionamento){
        this.nome = dadosEstacionamento.nome();
        this.tarifa = dadosEstacionamento.tarifa();
        this.chavePIX = dadosEstacionamento.chavePIX();
        this.endereco = dadosEstacionamento.endereco();
    }
}
