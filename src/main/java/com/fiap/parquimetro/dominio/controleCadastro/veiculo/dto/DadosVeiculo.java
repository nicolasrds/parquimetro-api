package com.fiap.parquimetro.dominio.controleCadastro.veiculo.dto;

import com.fiap.parquimetro.dominio.controleCadastro.veiculo.entity.Veiculo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record DadosVeiculo(

        Long id,

        @NotBlank
        String placa,

        @NotBlank
        String fabricante,

        @NotBlank
        String modelo,

        @NotBlank
        String cor,

        @Valid
        DadosCondutorVeiculo dadosCondutorVeiculo

) {
    public DadosVeiculo(Veiculo veiculo) {
        this(veiculo.getId(), veiculo.getPlaca(),
                veiculo.getFabricante(),
                veiculo.getModelo(), veiculo.getCor(),
                new DadosCondutorVeiculo(veiculo.getCondutor()));
    }
}
