package com.fiap.parquimetro.dominio.gerencimentoCadastro.cartao.service;

import com.fiap.parquimetro.dominio.gerencimentoCadastro.cartao.entity.Cartao;
import com.fiap.parquimetro.dominio.gerencimentoCadastro.cartao.repository.CartaoRepository;
import com.fiap.parquimetro.dominio.gerencimentoCadastro.condutor.service.CondutorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class CartaoService {

    private final CartaoRepository cartaoRepository;
    private final CondutorService condutorService;

    @Transactional
    public void gravarCartao(Long condutorId, Cartao cartao) {
        cartao.setCondutor(condutorService.carregar(condutorId));
        cartaoRepository.save(cartao);
    }

    public List<Cartao> listarCartaoPorCondutorId(Long condutorId) {
        return cartaoRepository.findCartaoByCondutorId(condutorId);
    }

}
