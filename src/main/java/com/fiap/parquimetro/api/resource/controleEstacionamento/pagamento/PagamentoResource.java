package com.fiap.parquimetro.api.resource.controleEstacionamento.pagamento;

import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.dto.DadosPagamento;
import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.entity.Pagamento;
import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.service.PagamentoService;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.dto.DadosRegistro;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.entity.Registro;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/controle-estacionamento/pagamento")
public class PagamentoResource {

    private final PagamentoService pagamentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DadosPagamento> cadastrar(@Valid @RequestBody DadosPagamento dadosPagamento) {
        Pagamento pagamento = pagamentoService.salvar(dadosPagamento);
        dadosPagamento = new DadosPagamento(pagamento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand((pagamento.getId())).toUri();
        return ResponseEntity.created(uri).body(dadosPagamento);
    }
}
