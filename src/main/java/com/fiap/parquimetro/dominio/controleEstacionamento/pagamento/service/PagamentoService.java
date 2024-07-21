package com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.service;

import com.fiap.parquimetro.dominio.controleCadastro.cartao.entity.Cartao;
import com.fiap.parquimetro.dominio.controleCadastro.cartao.service.CartaoService;
import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.dto.DadosPagamento;
import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.entity.Pagamento;
import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.enumeration.TipoPagamento;
import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.repository.PagamentoRepository;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.entity.Registro;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.enumeration.TipoRegistro;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.service.RegistroService;
import com.fiap.parquimetro.dominio.util.PastaRelatorio;
import com.fiap.parquimetro.dominio.util.RelatorioUtil;
import com.fiap.parquimetro.infra.exception.RegraDeNegocioException;
import com.fiap.parquimetro.infra.util.MessageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final RegistroService registroService;
    private final CartaoService cartaoService;
    private final MessageService messageService;
    private final RelatorioUtil relatorioUtil;

    @Transactional
    public Pagamento salvar(DadosPagamento dadosPagamento) {

        Registro registro = registroService.carregar(dadosPagamento.dadosRegistro().id());

        if (dadosPagamento.tipo().equals(TipoPagamento.PIX)
                && !registro.getTipo().equals(TipoRegistro.FIXO)){
            throw new RegraDeNegocioException(messageService.getMessage("pixSomenteFixo"));
        }

        Cartao cartao = null;
        if (dadosPagamento.tipo().equals(TipoPagamento.CREDITO) || dadosPagamento.tipo().equals(TipoPagamento.DEBITO)) {
            cartao = cartaoService.carregar(dadosPagamento.dadosCartao().id());
        }
        Pagamento pagamento = new Pagamento(dadosPagamento,registro,cartao);
        return pagamentoRepository.save(pagamento);
    }

    public byte[] emitirRecibo(Long idPagamento) throws Exception {

        Pagamento pagamento = pagamentoRepository.findById(idPagamento)
                .orElseThrow(() -> new EntityNotFoundException(messageService.getMessage("pagamentoNaoEncontrado")));

        List<Pagamento> lista = new ArrayList<>();
        lista.add(pagamento);
        Map<String, Object> parametros = new HashMap<>();
            return relatorioUtil.gerarRelatorioEmByte(PastaRelatorio.REL_RECIBO,
                    PastaRelatorio.SUB_REPORT_CABECALHO_PAISAGEM,
                    "teste", lista, parametros);



    }
}
