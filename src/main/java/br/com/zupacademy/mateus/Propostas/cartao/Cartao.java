package br.com.zupacademy.mateus.Propostas.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import br.com.zupacademy.mateus.Propostas.cartao.renegociacao.Renegociacao;
import br.com.zupacademy.mateus.Propostas.cartao.vencimento.EmbebbedVencimento;
import br.com.zupacademy.mateus.Propostas.proposta.Proposta;

@Entity
public class Cartao {

	@Id
	@NotBlank
	private String id;
	
	@OneToOne(mappedBy = "cartao")
	@NotNull
	private Proposta proposta;
	
	@Column(nullable = false)
	@NotNull @PastOrPresent
	private LocalDateTime dataEmissao;
	
	@Column(nullable = false)
	@NotBlank
	private String titular;
	
	@Column(nullable = false)
	@NotNull @PositiveOrZero
	private BigDecimal limite;
	
	@OneToOne @JoinColumn(name = "renegociacao_id", referencedColumnName = "id")
	private Renegociacao renegociacao;

    @Embedded
    @NotNull
	private EmbebbedVencimento vencimento;

	@Deprecated
	public Cartao() {
	}

	public Cartao(@NotBlank String id, @NotNull Proposta proposta, @NotNull @PastOrPresent LocalDateTime dataEmissao,
			@NotBlank String titular, @NotNull @PositiveOrZero BigDecimal limite,
			@NotNull EmbebbedVencimento vencimento) {
		this.id = id;
		this.proposta = proposta;
		this.dataEmissao = dataEmissao;
		this.titular = titular;
		this.limite = limite;
		this.vencimento = vencimento;
	}

	public String getId() {
		return id;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public LocalDateTime getDataEmissao() {
		return dataEmissao;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public Renegociacao getRenegociacao() {
		return renegociacao;
	}

	public EmbebbedVencimento getVencimento() {
		return vencimento;
	}
	
	public void setRenegociacao(Renegociacao renegociacao) {
		this.renegociacao = renegociacao;
	}
}