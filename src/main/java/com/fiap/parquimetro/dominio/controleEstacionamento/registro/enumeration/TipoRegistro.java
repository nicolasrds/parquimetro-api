package com.fiap.parquimetro.dominio.controleEstacionamento.registro.enumeration;

import com.fiap.parquimetro.dominio.controleEstacionamento.registro.entity.Registro;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.bussines.valorDevido.CalculadoraValorDevido;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.bussines.valorDevido.TipoCalculoFixo;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.bussines.valorDevido.TipoCalculoVariavel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public enum TipoRegistro {
    FIXO(new TipoCalculoFixo()),
    VARIAVEL(new TipoCalculoVariavel());

    private final CalculadoraValorDevido calculadoraValorDevido;

    public BigDecimal calcularValorDevido(Registro registro){
        return this.calculadoraValorDevido.calcularValorDevido(registro);
    }

}
