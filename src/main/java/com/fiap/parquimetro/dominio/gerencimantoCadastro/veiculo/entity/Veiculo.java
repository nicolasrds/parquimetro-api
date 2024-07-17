package com.fiap.parquimetro.dominio.gerencimantoCadastro.veiculo.entity;

import com.fiap.parquimetro.dominio.gerencimantoCadastro.condutor.entity.Condutor;
import com.fiap.parquimetro.dominio.gerencimantoCadastro.veiculo.dto.DadosVeiculo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "veiculo", schema = "controle_estabelecimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vei_id")
    private Long id;

    @NotBlank
    @Column(name = "vei_placa", nullable = false, unique = true, length = 7)
    @Size(max = 7)
    private String placa;

    @NotBlank
    @Column(name = "vei_fabricante", nullable = false)
    @Size(min = 3, max = 255)
    private String fabricante;

    @NotBlank
    @Column(name = "vei_modelo", nullable = false)
    @Size(min = 3, max = 255)
    private String modelo;

    @NotBlank
    @Column(name = "vei_cor", nullable = false)
    @Size(min = 3, max = 255)
    private String cor;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "vei_condutor_id")
    private Condutor condutor;

    public Veiculo(DadosVeiculo dadosVeiculo){
        this.id = dadosVeiculo.id();
        this.placa = dadosVeiculo.placa();
        this.fabricante = dadosVeiculo.fabricante();
        this.modelo = dadosVeiculo.modelo();
        this.cor = dadosVeiculo.cor();
        this.condutor = dadosVeiculo.Condutor();
    }
}