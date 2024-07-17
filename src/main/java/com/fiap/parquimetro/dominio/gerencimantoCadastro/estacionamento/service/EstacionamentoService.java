package com.fiap.parquimetro.dominio.gerencimantoCadastro.estacionamento.service;

import com.fiap.parquimetro.dominio.gerencimantoCadastro.estacionamento.entity.Estacionamento;
import com.fiap.parquimetro.dominio.gerencimantoCadastro.estacionamento.repository.EstacionamentoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Slf4j
public class EstacionamentoService {

    private final EstacionamentoRepository estacionamentoRepository;

    @Transactional
    public void atualizarEstacionamento(Estacionamento estacionamento) { estacionamentoRepository.save(estacionamento); }
}
