package com.fiap.parquimetro.dominio.controleEstacionamento.util;

import com.fiap.parquimetro.dominio.controleEstacionamento.registro.entity.Registro;

import java.math.BigDecimal;

public interface CalculadoraValorDevido {

    public BigDecimal calcularValorDevido(Registro registro);

}
