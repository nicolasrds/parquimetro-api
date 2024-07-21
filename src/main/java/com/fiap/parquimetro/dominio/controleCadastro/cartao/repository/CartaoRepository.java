package com.fiap.parquimetro.dominio.controleCadastro.cartao.repository;

import com.fiap.parquimetro.dominio.controleCadastro.cartao.entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    List<Cartao> findCartaoByCondutorId(Long condutorId);

}
