package br.com.zupacademy.mateus.Propostas.cartao.vencimento;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;

/**
 * 
 * Classe modelo que representa os campos embutidos relativos ao vencimento da entidade cartão.
 * 
 * @author Mateus Soares
 */
@Embeddable
public class EmbebbedVencimento {
	
	@Column(nullable = false)
	@Min(1) @Max(31)
	private Integer dia;
	
	@Column(nullable = false)
	@PastOrPresent
	private LocalDateTime dataDeCriacao;

	@Deprecated
	public EmbebbedVencimento() {
	}
	
	/**
	 * Construtor que instância e popula um objeto {@link EmbebbedVencimento} com os campos embutidos relativos ao vencimento da entidade cartão.
	 * 
	 * @param dia			dia do vencimento do cartão, deve ser um número entre 1 e 31;
	 * @param dataDeCriacao	data de criação do vencimento, deve ser uma data válida e não pode estar no futuro. 
	 */
	public EmbebbedVencimento(@Min(1) @Max(31) Integer dia, @PastOrPresent LocalDateTime dataDeCriacao) {
		this.dia = dia;
		this.dataDeCriacao = dataDeCriacao;
	}

	public Integer getDia() {
		return dia;
	}

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}
}
