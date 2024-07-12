package com.fiap.parquimetro.dominio.gerencimantoCadastro.condutor.dto;

import com.fiap.parquimetro.dominio.gerencimantoCadastro.condutor.entity.Condutor;
import com.fiap.parquimetro.dominio.util.Endereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCondutor(

        @NotEmpty
        String nome,

        @CPF
        @NotEmpty
        String cpf,

        @Email
        @NotEmpty
        String email,

        @NotEmpty
        String senha,

        String telefone,

        Endereco endereco

        ) {

        public DadosCondutor(Condutor condutor) {
                this(condutor.getNome(), condutor.getCpf(), condutor.getEmail(),
                        condutor.getSenha(), condutor.getTelefone(), condutor.getEndereco());
        }
}
