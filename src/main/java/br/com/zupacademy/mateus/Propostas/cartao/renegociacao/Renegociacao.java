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

/**
 * 
 * Classe modelo que representa os dados da entidade renegociação.
 * 
 * @author Mateus Soares
 */
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

	/**
	 * Construtor que instância e popula um objeto {@link Renegociacao} com os dados representativos da entidade renegociação.
	 * 
	 * @param id			id da renegociação gerado pelo serviço de accounts, deve ser único;
	 * @param quantidade	
	 * @param valor			valor da renegociação, deve se um valor não negativo;
	 * @param dataDeCriacao	data do cadastro da renegociação, deve ser uma data válida e não pode ser futura.
	 */
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
