package com.fiap.parquimetro.dominio.controleCadastro.estacionamento.service;

import com.fiap.parquimetro.dominio.controleCadastro.estacionamento.entity.Estacionamento;
import com.fiap.parquimetro.dominio.controleCadastro.estacionamento.repository.EstacionamentoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
@CacheConfig(cacheNames = {"estacionamentoCache"})
public class EstacionamentoService {

    private final EstacionamentoRepository estacionamentoRepository;

    @CacheEvict(cacheNames="estacionamentoCache", allEntries=true)
    @Transactional
    public void atualizarEstacionamento(Estacionamento estacionamento) {
        estacionamentoRepository.save(estacionamento);
    }

    @Cacheable(key = "#id", condition = "#id > 0")
    public Optional<Estacionamento> buscarEstacionamentoPorId(Long id) {
        return estacionamentoRepository.findById(id);
    }

    @Cacheable( unless = "#result == null ")
    public Page<Estacionamento> consultar(Pageable pageable){
        return estacionamentoRepository.findAll(pageable);
    }

    @Transactional
    public void deletar(Long id){
        estacionamentoRepository.deleteById(id);
    }
}
