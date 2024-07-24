package com.fiap.parquimetro.dominio.util;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DataUtil {

    public static long retornarHoraCompletaPorFracao(@NotNull LocalDateTime inicio, @NotNull LocalDateTime fim) {
        /**
         * Qualquer fração de uma hora será arredondada para a próxima hora completa,
         * pois estamos somando 59 minutos à diferença antes de dividir por 60.
         */
        return (diferencaDeMinutos(inicio, fim) + 59) / 60;
    }
    public static long diferencaDeHoras(@NotNull LocalDateTime inicio, @NotNull LocalDateTime fim) {
        return (diferencaDeMinutos(inicio, fim) ) / 60;
    }

    public static long diferencaDeMinutos(@NotNull LocalDateTime inicio, @NotNull LocalDateTime fim) {
        // Calcula a diferença em minutos
        return  ChronoUnit.MINUTES.between(inicio, fim);
    }


}