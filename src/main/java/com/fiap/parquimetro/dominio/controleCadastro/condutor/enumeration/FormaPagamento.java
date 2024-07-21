package com.fiap.parquimetro.dominio.controleCadastro.condutor.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FormaPagamento {

    CREDITO("Crédito"),
    DEBITO("Débito"),
    PIX("PIX");

    private String nome;
}
