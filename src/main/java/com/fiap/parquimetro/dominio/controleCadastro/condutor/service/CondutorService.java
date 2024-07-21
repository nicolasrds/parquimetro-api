package com.fiap.parquimetro.dominio.controleCadastro.condutor.service;

import com.fiap.parquimetro.dominio.controleCadastro.condutor.entity.Condutor;
import com.fiap.parquimetro.dominio.controleCadastro.condutor.repository.CondutorRepository;
import com.fiap.parquimetro.infra.util.MessageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
@CacheConfig(cacheNames = {"condutorCache"})
@Slf4j
public class CondutorService {

    private final CondutorRepository condutorRepository;
    private final MessageService messageService;

    @Transactional
    @CacheEvict(allEntries = true,cacheNames = "condutorCache")
    public void atualizarCondutor(Condutor condutor) {
        condutorRepository.save(condutor);
    }

    @Cacheable(key = "#condutorId")
    public Condutor carregar(Long condutorId) {
        log.info("++++++ Carregando condutor  {}.",condutorId);
        return condutorRepository.findById(condutorId)
                .orElseThrow(() -> new EntityNotFoundException(
                        messageService.getMessage("condutorNaoEncontrado")));
    }

    @Cacheable(key = "#cpf")
    public Optional<Condutor> carregar(String cpf) {
        return Optional.ofNullable(condutorRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException(
                        messageService.getMessage("condutorNaoEncontrado"))));
    }

}
