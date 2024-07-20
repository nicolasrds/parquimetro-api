package com.fiap.parquimetro.dominio.gerencimentoCadastro.estacionamento.dto;

import com.fiap.parquimetro.dominio.gerencimentoCadastro.estacionamento.entity.Estacionamento;
import com.fiap.parquimetro.dominio.util.Endereco;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public record DadosEstacionamento(

        @NotEmpty
        String nomeEstacionamento,

        @NotEmpty
        BigDecimal tarifa,

        char tipoTarifa,

        String chavePIX,

        Endereco endereco
) {

    public DadosEstacionamento(Estacionamento estacionamento) {
        this(estacionamento.getNome(), estacionamento.getTarifa(),
                estacionamento.getTipoTarifa(), estacionamento.getChavePIX(), estacionamento.getEndereco());
    }

}
