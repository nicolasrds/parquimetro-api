package com.fiap.parquimetro.dominio.controleCadastro.veiculo.service;

import com.fiap.parquimetro.dominio.controleCadastro.condutor.service.CondutorService;
import com.fiap.parquimetro.dominio.controleCadastro.veiculo.dto.DadosVeiculo;
import com.fiap.parquimetro.dominio.controleCadastro.veiculo.entity.Veiculo;
import com.fiap.parquimetro.dominio.controleCadastro.veiculo.repository.VeiculoRepository;
import com.fiap.parquimetro.infra.util.MessageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
@CacheConfig(cacheNames = {"veiculoCache"})
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final CondutorService condutorService;
    private final MessageService messageService;

    @Cacheable(key = "#pageable", unless = "#result == null")
    public Page<DadosVeiculo> listarVeiculos(Pageable pageable) {
        var veiculos = veiculoRepository.findAll(pageable);
        return veiculos.map(DadosVeiculo::new);
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public Optional<Veiculo> buscarVeiculo(Long id) {
        return Optional.ofNullable(veiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageService.getMessage("vaiculoNaoEncontrado"))));
    }

    @Transactional
    @CacheEvict(cacheNames = "veiculoCache",allEntries = true)
    public Veiculo salvarVeiculo(DadosVeiculo dadosVeiculo) {
        Veiculo veiculoSalvo = new Veiculo(dadosVeiculo);
        veiculoSalvo.setCondutor(condutorService.carregar(dadosVeiculo.dadosCondutorVeiculo().id()));
        veiculoSalvo = veiculoRepository.save(veiculoSalvo);
        return veiculoSalvo;
    }

    @Transactional
    public DadosVeiculo atualizarVeiculo(Long id, DadosVeiculo dadosVeiculo) {
       try {
           Veiculo veiculo = veiculoRepository.getReferenceById(id);
           veiculo.setPlaca(dadosVeiculo.placa());
           veiculo.setFabricante(dadosVeiculo.fabricante());
           veiculo.setModelo(dadosVeiculo.modelo());
           veiculo.setCor(dadosVeiculo.cor());
           veiculoRepository.save(veiculo);

           return new DadosVeiculo(veiculo);
       } catch (EntityNotFoundException e){
            throw new EntityNotFoundException(messageService.getMessage("veiculoNaoEncontrado"));
       }

    }

    @Transactional
    public void deletar(Long id) {
        veiculoRepository.deleteById(id);
    }
}
