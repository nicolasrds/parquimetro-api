package com.fiap.parquimetro.dominio.controleCadastro.cartao.service;

import com.fiap.parquimetro.dominio.controleCadastro.cartao.entity.Cartao;
import com.fiap.parquimetro.dominio.controleCadastro.cartao.repository.CartaoRepository;
import com.fiap.parquimetro.dominio.controleCadastro.condutor.entity.Condutor;
import com.fiap.parquimetro.dominio.controleCadastro.condutor.service.CondutorService;
import com.fiap.parquimetro.infra.util.MessageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
@CacheConfig(cacheNames = {"cartaoCache"})
public class CartaoService {

    private final CartaoRepository cartaoRepository;
    private final CondutorService condutorService;
    private final MessageService messageService;

    @Transactional
    @CacheEvict(allEntries = true, cacheNames = "cartaoCache")
    public void salvar(Long condutorId, Cartao cartao) {
        cartao.setCondutor(condutorService.carregar(condutorId));
        cartaoRepository.save(cartao);
    }
    @Cacheable(key = "#condutorId")
    public List<Cartao> listarCartaoPorCondutorId(Long condutorId) {
        return cartaoRepository.findCartaoByCondutorId(condutorId);
    }

    @Cacheable(key = "#id")
    public Cartao carregar(Long id) {
        return cartaoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageService.getMessage("cartaoNaoEncontrado")));
    }

}
