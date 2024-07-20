package com.fiap.parquimetro.dominio.gerencimentoCadastro.estacionamento.repository;

import com.fiap.parquimetro.dominio.gerencimentoCadastro.estacionamento.entity.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {
}
