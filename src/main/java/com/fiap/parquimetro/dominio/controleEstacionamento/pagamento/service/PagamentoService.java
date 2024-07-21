package com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.service;

import com.fiap.parquimetro.dominio.controleCadastro.cartao.entity.Cartao;
import com.fiap.parquimetro.dominio.controleCadastro.cartao.service.CartaoService;
import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.dto.DadosPagamento;
import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.entity.Pagamento;
import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.enumeration.TipoPagamento;
import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.repository.PagamentoRepository;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.entity.Registro;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.service.RegistroService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final RegistroService registroService;
    private final CartaoService cartaoService;

    @Transactional
    public Pagamento salvar(DadosPagamento dadosPagamento) {
        Cartao cartao = null;
        if (dadosPagamento.tipo().equals(TipoPagamento.CREDITO) || dadosPagamento.tipo().equals(TipoPagamento.DEBITO)) {
            cartao = cartaoService.carregar(dadosPagamento.dadosCartao().id());
        }
        Registro registro = registroService.carregar(dadosPagamento.dadosRegistro().id());
        Pagamento pagamento = new Pagamento(dadosPagamento,registro,cartao);
        return pagamentoRepository.save(pagamento);
    }
}
