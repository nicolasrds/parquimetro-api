package com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.repository;

import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
