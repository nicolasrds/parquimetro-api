package com.fiap.parquimetro.dominio.gerencimentoCadastro.veiculo.service;

import com.fiap.parquimetro.dominio.gerencimentoCadastro.veiculo.dto.DadosVeiculo;
import com.fiap.parquimetro.dominio.gerencimentoCadastro.veiculo.entity.Veiculo;
import com.fiap.parquimetro.dominio.gerencimentoCadastro.veiculo.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public Page<DadosVeiculo> listarVeiculos(Pageable pageable) {
        var veiculos = veiculoRepository.findAll(pageable);

        return veiculos.map(DadosVeiculo::new);
    }

    public DadosVeiculo listarVeiculo(Long id) {
        var veiculo = veiculoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Veiculo não encontrado"));

        return new DadosVeiculo(veiculo);
    }

    @Transactional
    public DadosVeiculo salvarVeiculo(DadosVeiculo dadosVeiculo) {
        Veiculo veiculoSalvo = new Veiculo(dadosVeiculo);
        veiculoSalvo = veiculoRepository.save(veiculoSalvo);

        return new DadosVeiculo(veiculoSalvo);
    }

    @Transactional
    public DadosVeiculo atualizarVeiculo(Long id, DadosVeiculo dadosVeiculo) {
       try {
           Veiculo veiculo = veiculoRepository.getReferenceById(id);

           veiculo.setId(dadosVeiculo.id());
           veiculo.setPlaca(dadosVeiculo.placa());
           veiculo.setFabricante(dadosVeiculo.fabricante());
           veiculo.setModelo(dadosVeiculo.modelo());
           veiculo.setCor(dadosVeiculo.cor());
           veiculo.setCondutor(dadosVeiculo.Condutor());

           veiculoRepository.save(veiculo);

           return new DadosVeiculo(veiculo);
       } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Veiculo não encontrado!");
       }

    }

    @Transactional
    public void delete(Long id) {
        veiculoRepository.deleteById(id);
    }
}
