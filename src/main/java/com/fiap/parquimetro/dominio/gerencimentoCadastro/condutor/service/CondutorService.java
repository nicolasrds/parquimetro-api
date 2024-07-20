package com.fiap.parquimetro.dominio.gerencimentoCadastro.condutor.service;

import com.fiap.parquimetro.dominio.gerencimentoCadastro.condutor.entity.Condutor;
import com.fiap.parquimetro.dominio.gerencimentoCadastro.condutor.repository.CondutorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
@CacheConfig(cacheNames = {"CondutorCache"})
@Slf4j
public class CondutorService {

    private final CondutorRepository condutorRepository;

    @Transactional
    public void atualizarCondutor(Condutor condutor) {
        condutorRepository.save(condutor);
    }

    @Cacheable(key = "#condutorId")
    public Condutor carregar(Long condutorId) {
        return condutorRepository.findById(condutorId)
                .orElseThrow(() -> new EntityNotFoundException("Condutor não encontrado"));
    }

    @Cacheable(key = "#cpf")
    public Optional<Condutor> carregar(String cpf) {
        return Optional.ofNullable(condutorRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Condutor não encontrado")));
    }

}
