package com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.dto;


import com.fiap.parquimetro.dominio.controleEstacionamento.registro.dto.DadosEstacionamentoRegistro;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.dto.DadosVeiculoRegistro;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.entity.Registro;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.enumeration.TipoRegistro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosRegistroPagamento(
        @NotNull
        Long id,
        DadosVeiculoRegistro veiculoRegistro,
        DadosEstacionamentoRegistro estacionamentoRegistro,
        TipoRegistro tipoRegistro,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        Integer duracao
) {

    public DadosRegistroPagamento(Registro registro) {
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
