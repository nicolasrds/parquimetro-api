package com.fiap.parquimetro.dominio.gerencimantoCadastro.veiculo.repository;

import com.fiap.parquimetro.dominio.gerencimantoCadastro.veiculo.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
