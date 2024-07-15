package com.fiap.parquimetro.dominio.gerencimantoCadastro.cartao.repository;

import com.fiap.parquimetro.dominio.gerencimantoCadastro.cartao.entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    List<Cartao> findCartaoByCondutorId(Long condutorId);

}
