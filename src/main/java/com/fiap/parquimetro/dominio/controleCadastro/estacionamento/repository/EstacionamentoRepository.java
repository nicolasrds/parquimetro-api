package com.fiap.parquimetro.dominio.controleCadastro.estacionamento.repository;

import com.fiap.parquimetro.dominio.controleCadastro.estacionamento.entity.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {
}
