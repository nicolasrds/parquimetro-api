package com.fiap.parquimetro.dominio.controleCadastro.veiculo.dto;

import com.fiap.parquimetro.dominio.controleCadastro.condutor.entity.Condutor;
import com.fiap.parquimetro.dominio.util.Endereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCondutorVeiculo(

        @NotNull
        Long id,

        String nome,

        @CPF
        String cpf,

        @Email
        String email,

        String telefone,

        Endereco endereco

) {

    public DadosCondutorVeiculo(Condutor condutor) {
        this(condutor.getId(),
                condutor.getNome(),
                condutor.getCpf(),
                condutor.getEmail(),
                condutor.getTelefone(),
                condutor.getEndereco());
    }

}
