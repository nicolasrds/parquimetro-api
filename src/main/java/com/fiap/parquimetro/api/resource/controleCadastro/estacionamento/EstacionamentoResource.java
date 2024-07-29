package com.fiap.parquimetro.api.resource.controleCadastro.estacionamento;

import com.fiap.parquimetro.dominio.controleCadastro.estacionamento.dto.DadosEstacionamento;
import com.fiap.parquimetro.dominio.controleCadastro.estacionamento.entity.Estacionamento;
import com.fiap.parquimetro.dominio.controleCadastro.estacionamento.service.EstacionamentoService;
import com.fiap.parquimetro.dominio.util.UriUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/controle-cadastro/estacionamento")
@Tag(name = "Área de estacionamento", description = "Controle da área de estacionamento")
public class EstacionamentoResource {

    private final EstacionamentoService estacionamentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastra uma nova área para estacionamento.", method = "POST")
    public ResponseEntity<DadosEstacionamento> cadastrar(@Valid @RequestBody DadosEstacionamento dadosEstacionamento,
                                                         UriComponentsBuilder uriComponentsBuilder) {
        Estacionamento estacionamento = new Estacionamento(dadosEstacionamento);
        estacionamentoService.atualizarEstacionamento(estacionamento);
        return ResponseEntity.created(UriUtil.createUriWithId(estacionamento.getId()))
                .body(new DadosEstacionamento(estacionamento));

    }

    @GetMapping
    @Operation(summary = "Busca as áreas de estacionamento cadastradas.", method = "GET")
    public Page<DadosEstacionamento> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return estacionamentoService.consultar(paginacao).map(DadosEstacionamento::new);
    }


}
