package br.com.zupacademy.mateus.Propostas.proposta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Controller com os end-points relacionados ao CRUD da entidade Proposta.
 * 
 * @author Mateus Soares
 */
@RestController
@RequestMapping("/propostas")
public class PropostaController {
	
	/**
	 * End-point de URL /propostas que realiza a validação e o cadastro do registro de uma proposta.
	 * 
	 * @param request proposta a ser cadastrada.
	 * @return ResponseEntity representando o status HTTP 201, 400 ou 500.
	 */
	@PostMapping
	public ResponseEntity<Void> cadastra(@RequestBody NovaPropostaRequest request){
		System.out.println(request.toString());
		return ResponseEntity.noContent().build();
	} 
}
