package br.com.zupacademy.mateus.Propostas.dadosfinanceiros;

import br.com.zupacademy.mateus.Propostas.proposta.Proposta;

/**
 * Classe representando o corpo da requisição a ser enviada para a API de análise financeira.
 * 
 * @author Mateus Soares
 */
public class DadosFinanceirosRequest {

	private String documento;
	private String nome;
	private String idProposta;

	/**
	 *  Construtor que instância um objeto {@link DadosFinanceirosRequest} populando
	 * com os dados da proposta passada.
	 * 
	 * @param proposta proposta encapsulando os dados a serem utilizados no corpo da requisição.s
	 */
	public DadosFinanceirosRequest(Proposta proposta) {
		documento = proposta.getDocumento();
		nome = proposta.getNome();
		idProposta = proposta.getId().toString();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}
}
