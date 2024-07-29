package com.fiap.parquimetro.api.resource.controleCadastro.veiculo;

import com.fiap.parquimetro.dominio.controleCadastro.veiculo.dto.DadosVeiculo;
import com.fiap.parquimetro.dominio.controleCadastro.veiculo.entity.Veiculo;
import com.fiap.parquimetro.dominio.controleCadastro.veiculo.service.VeiculoService;
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

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/controle-cadastro/veiculo")
@Tag(name = "Veiculo", description = "Controle de veículos do condutor")
public class VeiculoResource {

    private VeiculoService veiculoService;

    @GetMapping
    @Operation(summary = "Busca todos os veiculos cadastrados.", method = "GET")
    public ResponseEntity<Page<DadosVeiculo>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<DadosVeiculo> dadosVeiculos = veiculoService.listarVeiculos(pageable);
        return ResponseEntity.ok(dadosVeiculos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um veiculo cadastrado pelo id.", method = "GET")
    public ResponseEntity<DadosVeiculo> findById(@PathVariable Long id) {
        return veiculoService.buscarVeiculo(id)
                .map(DadosVeiculo::new)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
   }

    @PostMapping
    @Operation(summary = "Cadastra um veiculo novo para o condutor.", method = "POST")
    public ResponseEntity<DadosVeiculo> save(@Valid @RequestBody DadosVeiculo dadosVeiculo){
        Veiculo veiculo = veiculoService.salvarVeiculo(dadosVeiculo);
        dadosVeiculo = new DadosVeiculo(veiculo);
        return ResponseEntity.created(UriUtil.createUriWithId(veiculo.getId())).body(dadosVeiculo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Altera um veiculo cadastrado.", method = "PUT")
    public ResponseEntity<DadosVeiculo> update(@PathVariable Long id, @Valid @RequestBody DadosVeiculo dadosVeiculo) {
        var veiculo = veiculoService.atualizarVeiculo(id, dadosVeiculo);
        return ResponseEntity.ok(veiculo);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um veiculo cadastrado.", method = "DELETE")
    public ResponseEntity<DadosVeiculo> Delete(@PathVariable Long id) {
        veiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
