package com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.entity.Pagamento;
import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.enumeration.TipoPagamento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosPagamento(
        Long id,
        @Valid
        DadosRegistroPagamento dadosRegistro,
        DadosCartaoPagamento dadosCartao,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        LocalDateTime data,
        @NotNull
        TipoPagamento tipo,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        BigDecimal valor
) {
    public DadosPagamento(Pagamento pagamento) {
        this(
                pagamento.getId(),
                new DadosRegistroPagamento(pagamento.getRegistro()),
                new DadosCartaoPagamento(pagamento.getCartao()),
                pagamento.getData(),
                pagamento.getTipo(),
                pagamento.getValor());
    }
}