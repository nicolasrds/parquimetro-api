package com.fiap.parquimetro.api.resource.condutor;

import com.fiap.parquimetro.dominio.gerencimantoCadastro.condutor.dto.DadosCondutor;
import com.fiap.parquimetro.dominio.gerencimantoCadastro.condutor.entity.Condutor;
import com.fiap.parquimetro.dominio.gerencimantoCadastro.condutor.service.CondutorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/condutor")
public class CondutorResource {

    private final CondutorService condutorService;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DadosCondutor> cadastrar(@Valid @RequestBody DadosCondutor dadosCondutor,
                                                   UriComponentsBuilder uriComponentsBuilder) {
        Condutor condutor = new Condutor(dadosCondutor);
        condutorService.atualizarCondutor(condutor);
        var uri = uriComponentsBuilder.path("/api/v1/condutor/{id}").buildAndExpand(condutor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosCondutor(condutor));

    }
}
