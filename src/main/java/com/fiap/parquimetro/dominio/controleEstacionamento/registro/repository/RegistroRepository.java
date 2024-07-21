package com.fiap.parquimetro.dominio.controleEstacionamento.registro.repository;

import com.fiap.parquimetro.dominio.controleEstacionamento.registro.entity.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistroRepository extends JpaRepository<Registro, Long> {

    @Query("select r from Registro r where  r.fim is null ")
    List<Registro> findByDataFimIsNull();
}
