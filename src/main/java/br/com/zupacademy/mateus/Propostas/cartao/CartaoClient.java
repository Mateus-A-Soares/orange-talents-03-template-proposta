package br.com.zupacademy.mateus.Propostas.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Interface que declara o acesso ao serviço de accounts implementado pelo Spring Cloud.
 * 
 * @author Mateus Soares
 */
@FeignClient(url = "${account.service.url}", name = "${account.service.name}")
public interface CartaoClient {
	
	/**
	 *  Executa uma chamada GET na rota /api/cartoes do serviço de accounts,
	 * retornando a resposta como um String.
	 * 
	 * @param idProposta id da proposta para qual procura-se um cartão.
	 * @return string representando o cartão encontrado.
	 */
	@GetMapping("/api/cartoes")
	public String cartaoParaProposta(@RequestParam(name = "idProposta") String idProposta);
}
