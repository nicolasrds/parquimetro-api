package com.fiap.parquimetro.dominio.gerencimantoCadastro.condutor.service;

import com.fiap.parquimetro.dominio.gerencimantoCadastro.condutor.entity.Condutor;
import com.fiap.parquimetro.dominio.gerencimantoCadastro.condutor.repository.CondutorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Slf4j
public class CondutorService {

    private final CondutorRepository condutorRepository;

    @Transactional
    public void atualizarCondutor(Condutor condutor) {
            condutorRepository.save(condutor);
    }

}
