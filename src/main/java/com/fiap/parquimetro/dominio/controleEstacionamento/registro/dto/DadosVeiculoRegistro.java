package com.fiap.parquimetro.dominio.controleEstacionamento.registro.dto;

import com.fiap.parquimetro.dominio.controleCadastro.veiculo.dto.DadosCondutorVeiculo;
import com.fiap.parquimetro.dominio.controleCadastro.veiculo.entity.Veiculo;
import jakarta.validation.constraints.NotNull;

public record DadosVeiculoRegistro(

        @NotNull
        Long id,

        String placa,

        String fabricante,

        String modelo,

        String cor,

        DadosCondutorVeiculo dadosCondutorVeiculo

) {
    public DadosVeiculoRegistro(Veiculo veiculo) {
        this(veiculo.getId(), veiculo.getPlaca(),
                veiculo.getFabricante(),
                veiculo.getModelo(), veiculo.getCor(),
                new DadosCondutorVeiculo(veiculo.getCondutor()));
    }
}
