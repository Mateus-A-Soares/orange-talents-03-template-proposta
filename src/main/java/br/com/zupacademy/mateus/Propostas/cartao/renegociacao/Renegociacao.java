package br.com.zupacademy.mateus.Propostas.cartao.renegociacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import br.com.zupacademy.mateus.Propostas.cartao.Cartao;

@Entity
public class Renegociacao {

	@Id
	@NotBlank
	private String id;
	
	@Column(nullable = false)
	@PositiveOrZero
	private Integer quantidade;
	
	@Column(nullable = false)
	@PositiveOrZero
	private BigDecimal valor;
	
	@PastOrPresent
	@Column(nullable = false)
	private LocalDateTime dataDeCriacao;
	
	@OneToOne(mappedBy = "renegociacao")
	private Cartao cartao;
	
	@Deprecated
	public Renegociacao() {
	}

	public Renegociacao(String id, Integer quantidade, BigDecimal valor, LocalDateTime dataDeCriacao) {
		this.id = id;
		this.quantidade = quantidade;
		this.valor = valor;
		this.dataDeCriacao = dataDeCriacao;
	}

	public String getId() {
		return id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}
	
	public Cartao getCartao() {
		return cartao;
	}
}
