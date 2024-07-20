package com.fiap.parquimetro.dominio.gerencimentoCadastro.condutor.repository;

import com.fiap.parquimetro.dominio.gerencimentoCadastro.condutor.entity.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CondutorRepository extends JpaRepository<Condutor, Long> {
   Optional<Condutor> findByCpf(String cpf);
}
