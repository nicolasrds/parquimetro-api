package com.fiap.parquimetro.dominio.controleEstacionamento.registro.dto;


import com.fiap.parquimetro.dominio.controleEstacionamento.registro.entity.Registro;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.enumeration.TipoRegistro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosRegistro(

        Long id,
        @Valid
        DadosVeiculoRegistro veiculoRegistro,
        @Valid
        DadosEstacionamentoRegistro estacionamentoRegistro,
        @NotNull
        TipoRegistro tipoRegistro,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        Integer duracao
) {

    public DadosRegistro(Registro registro) {
        this(
                registro.getId(),
                new DadosVeiculoRegistro(registro.getVeiculo()),
                new DadosEstacionamentoRegistro(registro.getEstacionamento()),
                registro.getTipo(),
                registro.getInicio(),
                registro.getFim(),
                registro.getDuracao());
    }
}
