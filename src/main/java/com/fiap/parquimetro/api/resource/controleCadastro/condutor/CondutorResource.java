package com.fiap.parquimetro.api.resource.controleCadastro.condutor;

import com.fiap.parquimetro.dominio.controleCadastro.condutor.dto.DadosCondutor;
import com.fiap.parquimetro.dominio.controleCadastro.condutor.entity.Condutor;
import com.fiap.parquimetro.dominio.controleCadastro.condutor.service.CondutorService;
import com.fiap.parquimetro.dominio.controleCadastro.veiculo.dto.DadosVeiculo;
import com.fiap.parquimetro.dominio.util.UriUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/controle-cadastro/condutor")
@Tag(name = "Condutor", description = "Condutor/Usuário")
public class CondutorResource {

    private final CondutorService condutorService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar o condutor no sistema.", method = "POST")
    public ResponseEntity<DadosCondutor> cadastrar(@Valid @RequestBody DadosCondutor dadosCondutor) {
        Condutor condutor = new Condutor(dadosCondutor);
        condutorService.atualizarCondutor(condutor);
        return ResponseEntity.created(UriUtil.createUriWithId(condutor.getId())).body(new DadosCondutor(condutor));
    }

    @GetMapping("/{cpf}")
    @Operation(summary = "Buscar o condutor pelo número do cpf.", method = "POST")
    public ResponseEntity<DadosCondutor> buscarPorCpf(@PathVariable String cpf) {
        return  condutorService.carregar(cpf)
                .map(DadosCondutor::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DadosCondutor> Delete(@PathVariable Long id) {
        condutorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
