package br.com.zupacademy.mateus.Propostas.analisefinanceira;

/**
 * Classe representativa do corpo da resposta na rota de solicitação na API de análise financeira
 *
 * @author Mateus Soares
 */
public class SolicitacaoDadosFinanceirosResponse {

	private String idProposta;
	private String documento;
	private String nome;
	private ResultadoSolicitacao resultadoSolicitacao;
	
	public SolicitacaoDadosFinanceirosResponse(String idProposta, String documento, String nome,
			ResultadoSolicitacao resultadoSolicitacao) {
		this.idProposta = idProposta;
		this.documento = documento;
		this.nome = nome;
		this.resultadoSolicitacao = resultadoSolicitacao;
	}
	public String getIdProposta() {
		return idProposta;
	}
	public String getDocumento() {
		return documento;
	}
	public String getNome() {
		return nome;
	}
	public ResultadoSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}
	@Override
	public String toString() {
		return "DadosFinanceirosSolicitacaoResponse [idProposta=" + idProposta + ", documento=" + documento + ", nome="
				+ nome + ", resultadoSolicitacao=" + resultadoSolicitacao + "]";
	}
}
