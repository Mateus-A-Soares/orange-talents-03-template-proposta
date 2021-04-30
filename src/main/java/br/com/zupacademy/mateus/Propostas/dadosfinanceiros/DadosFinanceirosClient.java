package br.com.zupacademy.mateus.Propostas.dadosfinanceiros;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Interface que declara o acesso ao serviço de análise financeira implementado pelo Spring Cloud.
 * 
 * @author Mateus Soares
 */
@FeignClient(url = "${analisefinanceira.service.url}", name = "${analisefinanceira.service.name}")
public interface DadosFinanceirosClient {
	
	/**
	 *  Executa uma chamada na rota /api/solicitacao do serviço de análise financeira,
	 * retornando a resposta como um String.
	 * 
	 * @param request corpo da requisição com os dados necessários para a chamada.
	 * @return string representando a resposta do serviço.
	 */
	@PostMapping("/api/solicitacao")
	public DadosFinanceirosSolicitacaoResponse consulta(@RequestBody DadosFinanceirosRequest request);
	
	@GetMapping
	public ResponseEntity<Void> ping(); 
}
