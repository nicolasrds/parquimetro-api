package com.fiap.parquimetro.dominio.controleCadastro.condutor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.parquimetro.dominio.controleCadastro.condutor.entity.Condutor;
import com.fiap.parquimetro.dominio.util.Endereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCondutor(

        Long id,

        @NotEmpty
        String nome,

        @CPF
        @NotEmpty
        String cpf,

        @Email
        @NotEmpty
        String email,

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotEmpty
        String senha,

        String telefone,

        Endereco endereco

        ) {

        public DadosCondutor(Condutor condutor) {
                this(condutor.getId(), condutor.getNome(), condutor.getCpf(), condutor.getEmail(),
                        condutor.getSenha(), condutor.getTelefone(), condutor.getEndereco());
        }
}
