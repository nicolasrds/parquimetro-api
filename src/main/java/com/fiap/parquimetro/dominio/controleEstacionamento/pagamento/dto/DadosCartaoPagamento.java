package com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.dto;

import com.fiap.parquimetro.dominio.controleCadastro.cartao.entity.Cartao;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCartaoPagamento(

        @NotNull
        Long id,

        String numero,

        String titular,

        LocalDate validade,

        String cvv

) {

    public DadosCartaoPagamento(Cartao cartao) {

        this( cartao !=null ? cartao.getId() : null,
                cartao !=null ?  cartao.getNumero() : null,
                cartao !=null ? cartao.getTitular() : null,
                cartao !=null ? cartao.getValidade() : null,
                cartao !=null ? cartao.getCvv() : null) ;
    }

}
