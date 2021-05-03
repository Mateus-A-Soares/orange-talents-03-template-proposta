package br.com.zupacademy.mateus.Propostas.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zupacademy.mateus.Propostas.cartao.renegociacao.RenegociacaoClientResponse;
import br.com.zupacademy.mateus.Propostas.cartao.vencimento.VencimentoClientResponse;
import br.com.zupacademy.mateus.Propostas.proposta.Proposta;

/**
 *  Classe representativa do corpo da resposta na rota de solicitação
 * de um cartão relativo a uma proposta, na API de accounts.
 * 
 * @author Mateus Soares
 */
public class CartaoClientResponse {

	@NotBlank
	private String id;
	@NotNull @Positive
	private Long idProposta;
	@JsonProperty("emitidoEm")
	@NotNull @PastOrPresent
	private LocalDateTime dataEmissao;
	@NotBlank
	private String titular; 
	@NotNull @PositiveOrZero
	private BigDecimal limite;
	@Valid
	private RenegociacaoClientResponse renegociacao;
	@Valid @NotNull
	private VencimentoClientResponse vencimento;
	
	/**
	 *  Construtor que instância e popula um objeto {@link CartaoResponse}
	 * com os dados representativos da resposta na rota de solicitação de
	 * um cartão, na API de accounts.
	 * 
	 * @param id			id do cartão, deve ser único;
	 * @param idProposta	id da proposta qual o cartão pertence, deve existir e não ter cartão atrelado;
	 * @param dataEmissao	data de emissão do cartão, deve ser uma data válida e no passado;
	 * @param titular		nome do titular do cartão, não pode estar vazio;
	 * @param limite		limite do cartão, deve ser um valor não negativo;
	 * @param renegociacao	renegociacao, representação de uma renegociação caso exista; 
	 * @param vencimento	vencimento do cartão, representa a data de vencimento do cartão, não pode estar vazio. 
	 */
	public CartaoClientResponse(@NotBlank String id, @NotNull @Positive Long idProposta,
			@NotNull @PastOrPresent LocalDateTime dataEmissao, @NotBlank String titular,
			@NotNull @PositiveOrZero BigDecimal limite, @Valid VencimentoClientResponse vencimento) {
		this.id = id;
		this.idProposta = idProposta;
		this.dataEmissao = dataEmissao;
		this.titular = titular;
		this.limite = limite;
		this.vencimento = vencimento;
	}
	
	public String getId() {
		return id;
	}

	public Long getIdProposta() {
		return idProposta;
	}

	public Cartao toModel(Proposta proposta) {
		Cartao cartao = new Cartao(id, proposta, dataEmissao, titular, limite, vencimento.toModel()); 
		if(renegociacao != null)
			cartao.setRenegociacao(renegociacao.toModel());
		return cartao;
	}
}