package br.com.zupacademy.mateus.Propostas.cartao.renegociacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *  Classe representativa de uma renegociação, enviada no corpo da requisição na
 * rota de solicitação de um cartão, na API de accounts.
 * 
 * @author Mateus Soares
 */
public class RenegociacaoClientResponse {
	
	private String id;
	private Integer quantidade;
	private BigDecimal valor;
	private LocalDateTime dataDeCriacao;
	
	/**
	 *  Construtor que instância e popula um objeto {@link RenegociacaoResponses}
	 * com os dados representativos de uma renegociação, na rota de solicitação de
	 * um cartão, na API de accounts.
	 * 
	 * @param id			id da renegociação, deve ser único;
	 * @param quantidade		
	 * @param valor			valor da renegociação, deve se um valor positivo;
	 * @param dataDeCriacao	data do cadastro da renegocicação, deve ser uma data válida e no passado.
	 */
	public RenegociacaoClientResponse(String id, Integer quantidade, BigDecimal valor, LocalDateTime dataDeCriacao) {
		this.id = id;
		this.quantidade = quantidade;
		this.valor = valor;
		this.dataDeCriacao = dataDeCriacao;
	}

	@Override
	public String toString() {
		return "RenegociacaoResponse [id=" + id + ", quantidade=" + quantidade + ", valor=" + valor + ", dataDeCriacao="
				+ dataDeCriacao + "]";
	}
}
