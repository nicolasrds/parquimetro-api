package com.fiap.parquimetro.dominio.controleEstacionamento.registro.dto;

import com.fiap.parquimetro.dominio.controleCadastro.estacionamento.entity.Estacionamento;
import com.fiap.parquimetro.dominio.util.Endereco;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosEstacionamentoRegistro(

        @NotNull
        Long id,

        String nome,

        BigDecimal tarifa,

        String chavePIX,

        Endereco endereco
) {

    public DadosEstacionamentoRegistro(Estacionamento estacionamento) {
        this(estacionamento.getId(), estacionamento.getNome(), estacionamento.getTarifa(),
                 estacionamento.getChavePIX(), estacionamento.getEndereco());
    }

}
