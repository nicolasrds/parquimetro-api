package com.fiap.parquimetro.dominio.gerencimentoCadastro.veiculo.dto;

import com.fiap.parquimetro.dominio.gerencimentoCadastro.condutor.entity.Condutor;
import com.fiap.parquimetro.dominio.gerencimentoCadastro.veiculo.entity.Veiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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

        @NotEmpty
        @NotNull
        Condutor Condutor

) {
    public DadosVeiculo(Veiculo veiculo) {
        this(veiculo.getId(), veiculo.getPlaca(), veiculo.getFabricante(), veiculo.getModelo(), veiculo.getCor(), veiculo.getCondutor());
    }
}
