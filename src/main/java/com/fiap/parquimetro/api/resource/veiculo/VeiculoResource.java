package com.fiap.parquimetro.api.resource.veiculo;

import com.fiap.parquimetro.dominio.gerencimantoCadastro.veiculo.dto.DadosVeiculo;
import com.fiap.parquimetro.dominio.gerencimantoCadastro.veiculo.service.VeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/veiculo")
public class VeiculoResource {

    private VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<Page<DadosVeiculo>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<DadosVeiculo> dadosVeiculos = veiculoService.listarVeiculos(pageable);

        return ResponseEntity.ok(dadosVeiculos);
    }

    @GetMapping("{/id}")
    public ResponseEntity<DadosVeiculo> findById(@PathVariable Long id) {
        var veiculo = veiculoService.listarVeiculo(id);

        return ResponseEntity.ok(veiculo);
    }

    @PostMapping
    public ResponseEntity<DadosVeiculo> save(@Valid @RequestBody DadosVeiculo dadosVeiculo){
        var veiculo = veiculoService.salvarVeiculo(dadosVeiculo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((veiculo.id())).toUri();

        return ResponseEntity.created(uri).body(veiculo);
    }

    @PutMapping("{/id}")
    public ResponseEntity<DadosVeiculo> update(@PathVariable Long id, @Valid @RequestBody DadosVeiculo dadosVeiculo) {
        var veiculo = veiculoService.atualizarVeiculo(id, dadosVeiculo);

        return ResponseEntity.ok(veiculo);
    }


    @DeleteMapping("{/id}")
    public ResponseEntity<DadosVeiculo> Delete(@PathVariable Long id) {
        veiculoService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
