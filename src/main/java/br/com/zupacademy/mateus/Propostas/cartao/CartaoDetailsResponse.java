package br.com.zupacademy.mateus.Propostas.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.zupacademy.mateus.Propostas.cartao.renegociacao.Renegociacao;
import br.com.zupacademy.mateus.Propostas.cartao.renegociacao.RenegociacaoDetailsResponse;

/**
 * Classe que representa os dados dos cartões a serem serializados e enviados pela aplicação
 * 
 * @author Mateus Soares
 */
public class CartaoDetailsResponse {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	private String id;
	private String titular;
	private BigDecimal limite;
	private LocalDateTime dataEmissao;
	private Integer diaVencimento;
	private LocalDateTime dataCriacaoVencimento;
	private RenegociacaoDetailsResponse renegociacao;

	/**
	 * Construtor que instância e popula um objeto {@link CartaoDetailsResponse} com os dados representativos de um cartão a ser enviado pela aplicação.
	 * 
	 * @param cartao cartao populado com os dados que serão serializados.
	 */
	public CartaoDetailsResponse(Cartao cartao) {
		id = cartao.getId();
		dataEmissao = cartao.getDataEmissao();
		limite = cartao.getLimite();
		titular = cartao.getTitular();
		diaVencimento = cartao.getVencimento().getDia();
		dataCriacaoVencimento = cartao.getVencimento().getDataDeCriacao();
		Renegociacao renegociacaoCartao = cartao.getRenegociacao();
		if(renegociacaoCartao != null)
			renegociacao = new RenegociacaoDetailsResponse(renegociacaoCartao);
	}

	public String getId() {
		return id;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public String getDataEmissao() {
		return formatter.format(dataEmissao);
	}

	public Integer getDiaVencimento() {
		return diaVencimento;
	}

	public String getDataCriacaoVencimento() {
		return formatter.format(dataCriacaoVencimento);
	}

	public RenegociacaoDetailsResponse getRenegociacao() {
		return renegociacao;
	}
}
