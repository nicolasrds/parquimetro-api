package com.fiap.parquimetro.dominio.gerencimantoCadastro.cartao.entity;

import com.fiap.parquimetro.dominio.gerencimantoCadastro.cartao.dto.DadosCartao;
import com.fiap.parquimetro.dominio.gerencimantoCadastro.condutor.entity.Condutor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "cartao", schema = "controle_estabelecimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartao_id")
    private Long id;

    @Column(name = "cartao_numero", nullable = false, length = 16)
    private String numero;

    @Column(name = "cartao_nome_titular", nullable = false)
    private String titular;

    @Column(name = "cartao_data_validade", nullable = false)
    private LocalDate validade;

    @Column(name = "cartao_cvv", nullable = false, length = 4)
    private String cvv;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "condutor_id", nullable = false)
    private Condutor condutor;

    public Cartao(DadosCartao dadosCartao) {
        this.numero = dadosCartao.numero();
        this.titular = dadosCartao.titular();
        this.validade = dadosCartao.validade();
        this.cvv = dadosCartao.cvv();
    }

}
