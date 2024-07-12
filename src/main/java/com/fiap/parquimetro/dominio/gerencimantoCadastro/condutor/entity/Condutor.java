package com.fiap.parquimetro.dominio.gerencimantoCadastro.condutor.entity;

import com.fiap.parquimetro.dominio.gerencimantoCadastro.condutor.dto.DadosCondutor;
import com.fiap.parquimetro.dominio.util.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "condutor", schema = "controle_estabelecimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Condutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "con_id")
    private Long id;

    @NotEmpty
    @Column(name = "con_nome", nullable = false)
    @Length(min = 5)
    private String nome;

    @CPF
    @NotEmpty
    @Column(name = "con_cpf", nullable = false, unique = true, updatable = false, length = 14)
    private String cpf;

    @Email
    @NotEmpty
    @Column(name = "con_email", nullable = false, unique = true)
    private String email;

    @NotEmpty
    @Column(name = "con_senha", nullable = false)
    private String senha;

    @Column(name = "con_telefone")
    private String telefone;

    @Embedded
    private Endereco endereco;

    public Condutor(DadosCondutor dadosCondutor) {
        this.nome = dadosCondutor.nome();
        this.cpf = dadosCondutor.cpf();
        this.email = dadosCondutor.email();
        this.senha = dadosCondutor.senha();
        this.telefone = dadosCondutor.telefone();
        this.endereco = dadosCondutor.endereco();
    }
}
