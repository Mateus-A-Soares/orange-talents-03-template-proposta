package br.com.zupacademy.mateus.Propostas.cartao;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zupacademy.mateus.Propostas.cartao.renegociacao.RenegociacaoClientResponse;
import br.com.zupacademy.mateus.Propostas.cartao.vencimento.VencimentoClientResponse;

/**
 *  Classe representativa do corpo da resposta na rota de solicitação
 * de um cartão relativo a uma proposta, na API de accounts.
 * 
 * @author Mateus Soares
 */
public class CartaoClientResponse {

	private String id;
	private Long idProposta;
	@JsonProperty("emitidoEm")
	private LocalDateTime dataEmissao;
	private String titular; 
	private Long limite;
	private RenegociacaoClientResponse renegociacao;
	private VencimentoClientResponse vencimento;
	
	/**
	 *  Construtor que instância e popula um objeto {@link CartaoResponse}
	 * com os dados representativos da resposta na rota de solicitação de
	 * um cartão, na API de accounts.
	 * 
	 * @param id			id do cartão, deve ser único;
	 * @param idProposta	id da proposta qual o cartão pertence, deve existir e não ter cartão atrelado;
	 * @param dataEmissao	data de emissão do cartão, deve ser uma data válida e no passado;
	 * @param titular		titular do cartão, deve ser um documento válido;
	 * @param limite		limite do cartão, deve ser um valor não negativo;
	 * @param renegociacao	renegociacao, representação de uma renegociação caso exista; 
	 * @param vencimento	vencimento do cartão, representa a data de vencimento do cartão. 
	 */
	public CartaoClientResponse(String id, Long idProposta, LocalDateTime dataEmissao, String titular, Long limite,
			RenegociacaoClientResponse renegociacao, VencimentoClientResponse vencimento) {
		this.id = id;
		this.idProposta = idProposta;
		this.dataEmissao = dataEmissao;
		this.titular = titular;
		this.limite = limite;
		this.renegociacao = renegociacao;
		this.vencimento = vencimento;
	}
	
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "CartaoResponse [id=" + id + ", idProposta=" + idProposta + ", dataEmissao=" + dataEmissao + ", titular="
				+ titular + ", limite=" + limite + ", renegociacao=" + renegociacao + ", vencimento=" + vencimento
				+ "]";
	}
}
