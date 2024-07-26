package com.fiap.parquimetro.dominio.controleEstacionamento.registro.bussines.valorDevido;

import com.fiap.parquimetro.dominio.controleEstacionamento.registro.entity.Registro;
import com.fiap.parquimetro.dominio.util.DataUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class TipoCalculoVariavel implements CalculadoraValorDevido{
    @Override
        public BigDecimal calcularValorDevido(Registro registro) {
        long quantidadeDeHoras = DataUtil.retornarHoraCompletaPorFracao(registro.getInicio(), registro.getFim());
        BigDecimal valor = registro.getEstacionamento().getTarifa().multiply(new BigDecimal(quantidadeDeHoras));
        log.info("Tarifa -> {}", registro.getEstacionamento().getTarifa());
        log.info("Duração -> {}", quantidadeDeHoras);
        log.info("Calculando valor variavel de {}", valor.toString());

        return valor;
    }
}
