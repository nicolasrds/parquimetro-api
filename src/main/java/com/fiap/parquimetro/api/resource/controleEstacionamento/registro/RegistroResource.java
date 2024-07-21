package com.fiap.parquimetro.api.resource.controleEstacionamento.registro;

import com.fiap.parquimetro.dominio.controleEstacionamento.registro.dto.DadosRegistro;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.entity.Registro;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.service.RegistroService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/controle-estacionamento/registro")
public class RegistroResource {

    private final RegistroService registroService;


    @GetMapping
    public ResponseEntity<Page<DadosRegistro>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<DadosRegistro> dadosRegistros = registroService.consultar(pageable);
        return ResponseEntity.ok(dadosRegistros);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DadosRegistro> cadastrar(@Valid @RequestBody DadosRegistro dadosRegistro) {
        Registro registro = registroService.salvar(dadosRegistro);
        dadosRegistro = new DadosRegistro(registro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand((registro.getId())).toUri();
        return ResponseEntity.created(uri).body(dadosRegistro);
    }

    @PutMapping("/{id}/encerrar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void encerrar(@PathVariable Long id) {
        this.registroService.encerrar(id);
    }
}
