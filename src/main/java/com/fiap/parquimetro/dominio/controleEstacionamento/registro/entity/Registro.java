package com.fiap.parquimetro.dominio.controleEstacionamento.registro.entity;

import com.fiap.parquimetro.dominio.controleCadastro.estacionamento.entity.Estacionamento;
import com.fiap.parquimetro.dominio.controleCadastro.veiculo.entity.Veiculo;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.dto.DadosRegistro;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.enumeration.TipoRegistro;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Entity
@Table(name = "registro",
        uniqueConstraints = {@UniqueConstraint(name = "uk_registro_veiculo",
                columnNames = {"vei_id", "reg_inicio"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Slf4j
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reg_id")
    private Long id;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "vei_id", referencedColumnName = "vei_id", nullable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_veiculo"))
    private Veiculo veiculo;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "est_id", referencedColumnName = "est_id", nullable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_estacionamento"))
    private Estacionamento estacionamento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "reg_tipo", nullable = false, updatable = false)
    private TipoRegistro tipo;

    @NotNull
    @Column(name = "reg_inicio", nullable = false, updatable = false)
    private LocalDateTime inicio;

    @Column(name = "reg_fim")
    private LocalDateTime fim;

    @Column(name = "reg_duracao")
    private Integer duracao;

    public Registro(DadosRegistro dadosRegistro, Veiculo veiculo, Estacionamento estacionamento) {
        setInicio(LocalDateTime.now());
        setFim(dadosRegistro.dataFim());
        setTipo(dadosRegistro.tipoRegistro());
        setDuracao(preencherDuracao(dadosRegistro));
        setVeiculo(veiculo);
        setEstacionamento(estacionamento);
    }


    public boolean isDeveNotificar() {
        LocalDateTime previsaoEncerramento = getInicio().plusHours(getDuracao());
        return previsaoEncerramento.isBefore(LocalDateTime.now());
    }

    private Integer preencherDuracao(DadosRegistro dadosRegistro) {
        if (getTipo().equals(TipoRegistro.FIXO)) {
            return dadosRegistro.duracao();
        }
        return 1;
    }
}
