package br.com.zupacademy.mateus.Propostas.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import br.com.zupacademy.mateus.Propostas.dadosfinanceiros.DadosFinanceirosClient;
import feign.FeignException;

/**
 * HealthIndicator que verifica a conexão da aplicação com o serviço de análise financeira.
 * 
 * @author Mateus Soares
 */
@Component
public class AnaliseFinanceiraServiceHealthIndicator implements HealthIndicator {

	private DadosFinanceirosClient client;

	public AnaliseFinanceiraServiceHealthIndicator(@Autowired DadosFinanceirosClient client) {
		this.client = client;
	}

	@Override
	public Health health() {
		try {
			client.ping();
		} catch (FeignException.NotFound e) {
			return Health.up().build();
		} catch (Exception e) {
			return Health.down().withDetail("connection", e.getMessage()).build();
		}
		return Health.up().build();
	}
}
