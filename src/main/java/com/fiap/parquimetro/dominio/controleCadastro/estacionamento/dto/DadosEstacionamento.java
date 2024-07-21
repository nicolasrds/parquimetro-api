package com.fiap.parquimetro.dominio.controleCadastro.estacionamento.dto;

import com.fiap.parquimetro.dominio.controleCadastro.estacionamento.entity.Estacionamento;
import com.fiap.parquimetro.dominio.util.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosEstacionamento(

        Long id,

        @NotEmpty
        String nome,

        @NotNull
        BigDecimal tarifa,

        @NotEmpty
        String chavePIX,

        @Valid
        Endereco endereco
) {

    public DadosEstacionamento(Estacionamento estacionamento) {
        this(estacionamento.getId(), estacionamento.getNome(), estacionamento.getTarifa(),
                 estacionamento.getChavePIX(), estacionamento.getEndereco());
    }

}
