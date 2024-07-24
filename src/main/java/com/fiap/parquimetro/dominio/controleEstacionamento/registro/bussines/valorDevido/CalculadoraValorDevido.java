package com.fiap.parquimetro.dominio.controleEstacionamento.registro.bussines.valorDevido;

import com.fiap.parquimetro.dominio.controleEstacionamento.registro.entity.Registro;

import java.math.BigDecimal;

public interface CalculadoraValorDevido {

    BigDecimal calcularValorDevido(Registro registro);

}
