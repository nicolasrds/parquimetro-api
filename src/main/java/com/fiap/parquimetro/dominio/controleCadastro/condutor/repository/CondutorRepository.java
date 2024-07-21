package com.fiap.parquimetro.dominio.controleCadastro.condutor.repository;

import com.fiap.parquimetro.dominio.controleCadastro.condutor.entity.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CondutorRepository extends JpaRepository<Condutor, Long> {
   Optional<Condutor> findByCpf(String cpf);
}
