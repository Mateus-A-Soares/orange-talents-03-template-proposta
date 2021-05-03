package br.com.zupacademy.mateus.Propostas.cartao.vencimento;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;

@Embeddable
public class EmbebbedVencimento {
	
	@Column(nullable = false)
	@Min(1) @Max(31)
	private Integer dia;
	
	@Column(nullable = false)
	@PastOrPresent
	private LocalDateTime dataDeCriacao;

	public EmbebbedVencimento() {
	}
	
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
