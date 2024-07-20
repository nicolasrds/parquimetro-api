package com.fiap.parquimetro.api.resource.estacionamento;

import com.fiap.parquimetro.dominio.gerencimentoCadastro.estacionamento.dto.DadosEstacionamento;
import com.fiap.parquimetro.dominio.gerencimentoCadastro.estacionamento.entity.Estacionamento;
import com.fiap.parquimetro.dominio.gerencimentoCadastro.estacionamento.service.EstacionamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/estacionamento")
public class EstacionamentoResource {

    private final EstacionamentoService estacionamentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DadosEstacionamento> cadastrar(@Valid @RequestBody DadosEstacionamento dadosEstacionamento,
                                                         UriComponentsBuilder uriComponentsBuilder) {
        Estacionamento estacionamento = new Estacionamento(dadosEstacionamento);
        estacionamentoService.atualizarEstacionamento(estacionamento);
        var uri = uriComponentsBuilder.path("/api/v1/estacionamento/{id}").buildAndExpand(estacionamento.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosEstacionamento(estacionamento));

    }
}
