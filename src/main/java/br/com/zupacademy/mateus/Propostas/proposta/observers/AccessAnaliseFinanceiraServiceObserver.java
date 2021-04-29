package br.com.zupacademy.mateus.Propostas.proposta.observers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import br.com.zupacademy.mateus.Propostas.dadosfinanceiros.DadosFinanceirosClient;
import br.com.zupacademy.mateus.Propostas.dadosfinanceiros.DadosFinanceirosRequest;
import br.com.zupacademy.mateus.Propostas.dadosfinanceiros.DadosFinanceirosSolicitacaoResponse;
import br.com.zupacademy.mateus.Propostas.dadosfinanceiros.ResultadoSolicitacao;
import br.com.zupacademy.mateus.Propostas.proposta.EstadoProposta;
import br.com.zupacademy.mateus.Propostas.proposta.Proposta;
import feign.FeignException;

/**
 * Observer que acessa o sistema de análise financeira para veriricar a
 * existência de restrições para o solicitante.
 * 
 * @author Mateus Soares
 */
@Order(value = 1)
@Component
public class AccessAnaliseFinanceiraServiceObserver implements NewPropostaObserver {

	private DadosFinanceirosClient client;

	public AccessAnaliseFinanceiraServiceObserver(@Autowired DadosFinanceirosClient client) {
		this.client = client;
	}

	@Transactional
	@Override
	public void update(Proposta proposta) {
		try {
			DadosFinanceirosRequest requestBody = new DadosFinanceirosRequest(proposta);
			DadosFinanceirosSolicitacaoResponse responseBody = client.consulta(requestBody);
			Assert.isTrue(responseBody.getResultadoSolicitacao().equals(ResultadoSolicitacao.SEM_RESTRICAO),
					"O resultado de uma request sucedida deveria ser SEM_RESTRICAO");
			proposta.setEstado(EstadoProposta.ELEGIVEL);
		} catch (FeignException.UnprocessableEntity e) {
			proposta.setEstado(EstadoProposta.NAO_ELEGIVEL);
		}
	}
}
