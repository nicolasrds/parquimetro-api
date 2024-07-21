package com.fiap.parquimetro.dominio.controleEstacionamento.util;

import com.fiap.parquimetro.dominio.controleEstacionamento.registro.entity.Registro;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class TipoCalculoFixo implements CalculadoraValorDevido {
    @Override
    public BigDecimal calcularValorDevido(Registro registro) {

        BigDecimal valor = registro.getEstacionamento().getTarifa()
                .multiply(BigDecimal.valueOf(registro.getDuracao()));

        log.info("Tarifa -> {}", registro.getEstacionamento().getTarifa());
        log.info("Duração -> {}", registro.getDuracao());
        log.info("Calculando valor de fixo de {}", valor.toString());

        return valor;
    }
}
