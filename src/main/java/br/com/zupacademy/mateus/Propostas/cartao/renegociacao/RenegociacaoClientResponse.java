package br.com.zupacademy.mateus.Propostas.cartao.renegociacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

/**
 *  Classe representativa de uma renegociação, enviada no corpo da requisição na
 * rota de solicitação de um cartão, na API de accounts.
 * 
 * @author Mateus Soares
 */
public class RenegociacaoClientResponse {
	
	@NotBlank
	private String id;
	@NotNull @PositiveOrZero
	private Integer quantidade;
	@NotNull @PositiveOrZero
	private BigDecimal valor;
	@NotNull @PastOrPresent
	private LocalDateTime dataDeCriacao;
	
	/**
	 *  Construtor que instância e popula um objeto {@link RenegociacaoResponses}
	 * com os dados representativos de uma renegociação, na rota de solicitação de
	 * um cartão, na API de accounts.
	 * 
	 * @param id			id da renegociação, deve ser único;
	 * @param quantidade		
	 * @param valor			valor da renegociação, deve se um valor não negativo;
	 * @param dataDeCriacao	data do cadastro da renegocicação, deve ser uma data válida e no passado.
	 */
	public RenegociacaoClientResponse(@NotBlank String id, @NotNull @PositiveOrZero Integer quantidade,
			@NotNull @PositiveOrZero BigDecimal valor, @NotNull @PastOrPresent LocalDateTime dataDeCriacao) {
		this.id = id;
		this.quantidade = quantidade;
		this.valor = valor;
		this.dataDeCriacao = dataDeCriacao;
	}

	public Renegociacao toModel() {
		return new Renegociacao(id, quantidade, valor, dataDeCriacao);
	}
}
