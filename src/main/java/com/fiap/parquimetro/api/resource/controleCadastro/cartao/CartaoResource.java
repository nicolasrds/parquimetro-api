package com.fiap.parquimetro.api.resource.controleCadastro.cartao;

import com.fiap.parquimetro.dominio.controleCadastro.cartao.dto.DadosCartao;
import com.fiap.parquimetro.dominio.controleCadastro.cartao.entity.Cartao;
import com.fiap.parquimetro.dominio.controleCadastro.cartao.service.CartaoService;
import com.fiap.parquimetro.dominio.controleCadastro.condutor.dto.DadosCondutor;
import com.fiap.parquimetro.dominio.util.UriUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/controle-cadastro/cartao")
public class CartaoResource {

    private final CartaoService cartaoService;

    @PostMapping("/{condutorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DadosCartao> cadastrar(@PathVariable Long condutorId,
                                                 @Valid @RequestBody DadosCartao dadosCartao                                                 ) {
        Cartao cartao = new Cartao(dadosCartao);
        cartaoService.salvar(condutorId, cartao);
        return ResponseEntity.created(UriUtil.createUriWithId(cartao.getId())).body(new DadosCartao(cartao));
    }

    @GetMapping("cartoes-do-condutor/{condutorId}")
    public ResponseEntity<List<DadosCartao>> listarCartoesDoCondutor(@PathVariable Long condutorId) {
        List<DadosCartao> cartoes = cartaoService.listarCartaoPorCondutorId(condutorId)
                .stream().map(DadosCartao::new).toList();
        return ResponseEntity.ok(cartoes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DadosCondutor> Delete(@PathVariable Long id) {
        cartaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
