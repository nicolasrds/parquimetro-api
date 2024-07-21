package com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.entity;

import com.fiap.parquimetro.dominio.controleCadastro.cartao.entity.Cartao;
import com.fiap.parquimetro.dominio.controleCadastro.estacionamento.entity.Estacionamento;
import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.dto.DadosPagamento;
import com.fiap.parquimetro.dominio.controleEstacionamento.pagamento.enumeration.TipoPagamento;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.entity.Registro;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento", schema = "controle_estacionamento",
        indexes = {
                @Index(name = "uk_reg_id", columnList = "reg_id", unique = true),
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pag_id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "reg_id", referencedColumnName = "reg_id", unique = true, nullable = false, updatable = false)
    private Registro registro;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    private Cartao cartao;

    @NotNull
    @Column(name = "pag_data", nullable = false, updatable = false)
    private LocalDateTime data;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "pag_tipo", nullable = false, updatable = false)
    private TipoPagamento tipo;

    @NotNull
    @Column(name = "pag_valor", nullable = false, updatable = false)
    private BigDecimal valor;

    public Pagamento(DadosPagamento dadosPagamento, Registro registro, Cartao cartao) {
        setTipo(dadosPagamento.tipo());
        setRegistro(registro);
        setCartao(cartao);
        setData(LocalDateTime.now());
        setValor(dadosPagamento.valor());
    }
}
