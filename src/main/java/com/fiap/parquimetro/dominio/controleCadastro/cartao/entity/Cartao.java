package com.fiap.parquimetro.dominio.controleCadastro.cartao.entity;

import com.fiap.parquimetro.dominio.controleCadastro.cartao.dto.DadosCartao;
import com.fiap.parquimetro.dominio.controleCadastro.condutor.entity.Condutor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "cartao", schema = "controle_cadastro")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;

    @NotEmpty
    @Column(name = "car_numero", nullable = false, length = 16)
    private String numero;

    @NotEmpty
    @Column(name = "car_nome_titular", nullable = false)
    private String titular;

    @NotNull
    @Column(name = "car_data_validade", nullable = false)
    private LocalDate validade;

    @NotEmpty
    @Column(name = "car_cvv", nullable = false, length = 4)
    private String cvv;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "condutor_id", nullable = false, foreignKey = @ForeignKey(name = "fk_condutor"))
    private Condutor condutor;

    public Cartao(DadosCartao dadosCartao) {
        this.numero = dadosCartao.numero();
        this.titular = dadosCartao.titular();
        this.validade = dadosCartao.validade();
        this.cvv = dadosCartao.cvv();
    }

}
