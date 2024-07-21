package com.fiap.parquimetro.dominio.util;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DataUtil {

    public static long diferencaDeHoras(@NotNull LocalDateTime inicio, @NotNull LocalDateTime fim) {
        // Calcula a diferença em minutos
        long diferencaMinutos = ChronoUnit.MINUTES.between(inicio, fim);

        /**
         * Qualquer fração de uma hora será arredondada para a próxima hora completa,
         * pois estamos somando 59 minutos à diferença antes de dividir por 60.
         */
        return (diferencaMinutos + 59) / 60;
    }

}