package com.fiap.parquimetro.api.resource.controleEstacionamento.pagamento;

import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.dto.DadosPagamento;
import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.entity.Pagamento;
import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.service.PagamentoService;
import com.fiap.parquimetro.dominio.util.TipoExtencaoArquivo;
import com.fiap.parquimetro.dominio.util.UriUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/controle-estacionamento/pagamento")
@Tag(name = "Pagamento do estacionamento", description = "Controle do pagamento do estacionamento")
public class PagamentoResource {

    private final PagamentoService pagamentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastra um novo pagamento.", method = "POST")
    public ResponseEntity<DadosPagamento> cadastrar(@Valid @RequestBody DadosPagamento dadosPagamento) {
        Pagamento pagamento = pagamentoService.salvar(dadosPagamento);
        dadosPagamento = new DadosPagamento(pagamento);
        return ResponseEntity.created(UriUtil.createUriWithId(pagamento.getId())).body(dadosPagamento);
    }

    @GetMapping("/recibo/{id}")
    @Operation(summary = "Emite o recibo do estacinamento.", method = "GET")
    public ResponseEntity<byte[]> emitirRecibo(@PathVariable Long id) throws Exception {
        byte[] relatorio = pagamentoService.emitirRecibo(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, TipoExtencaoArquivo.PDF.getMime(TipoExtencaoArquivo.PDF.getExtencao()))
                .body(relatorio);
    }
}
