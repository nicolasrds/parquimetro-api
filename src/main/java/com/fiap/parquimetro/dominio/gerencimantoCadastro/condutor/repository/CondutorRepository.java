package com.fiap.parquimetro.dominio.gerencimantoCadastro.condutor.repository;

import com.fiap.parquimetro.dominio.gerencimantoCadastro.condutor.entity.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CondutorRepository extends JpaRepository<Condutor, Long> {
}
