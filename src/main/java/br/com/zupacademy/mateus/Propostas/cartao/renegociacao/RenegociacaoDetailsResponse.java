package br.com.zupacademy.mateus.Propostas.cartao.renegociacao;

import java.math.BigDecimal;

/**
 * Classe que representa os dados das renegociações a serem serializados e enviados pela aplicação
 * 
 * @author Mateus Soares
 */
public class RenegociacaoDetailsResponse {

	private String id;
	private Integer quantidade;
	private BigDecimal valor;

	/**
	 * Construtor que instância e popula um objeto {@link RenegociacaoDetailsResponse} com os dados representativos de uma renegociação a ser enviado pela aplicação.
	 * 
	 * @param renegociacao renegociacao populada com os dados que serão serializados.
	 */
	public RenegociacaoDetailsResponse(Renegociacao renegociacao) {
		id = renegociacao.getId();
		quantidade = renegociacao.getQuantidade();
		valor = renegociacao.getValor();
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
}
