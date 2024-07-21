package com.fiap.parquimetro.dominio.controleCadastro.cartao.dto;

import com.fiap.parquimetro.dominio.controleCadastro.cartao.entity.Cartao;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record DadosCartao(

        @NotEmpty(message = "O número do cartão é obrigatório")
        String numero,

        @NotEmpty(message = "O nome do título do cartão é obrigatório")
        @Length(min = 5, message = "O nome do títular deve ter pelo menos 5 letras")
        String titular,

        @NotNull(message = "A data de validade do cartão é obrigatória")
        @Future(message = "A data de validade do cartão deve ser uma data futura")
        LocalDate validade,

        @NotEmpty(message = "O CVV do cartão é obrigatório")
        String cvv

) {

    public DadosCartao(Cartao cartao) {
        this(cartao.getNumero(), cartao.getTitular(),
                cartao.getValidade(), cartao.getCvv());
    }

}
