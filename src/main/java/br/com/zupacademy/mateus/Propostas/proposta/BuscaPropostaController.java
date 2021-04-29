package br.com.zupacademy.mateus.Propostas.proposta;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Controller com os end-point relacionados a leitura dos registros da entidade Proposta.
 * 
 * @author Mateus Soares
 */
@RestController
@RequestMapping("/propostas")
public class BuscaPropostaController {

	private PropostaRepository repository;

	public BuscaPropostaController(@Autowired PropostaRepository repository) {
		this.repository = repository;
	}

	/**
	 * End-point de URL /propostas/{id} que procura por uma proposta e, caso
	 * encontre, retorna ela no corpo da resposta.
	 * 
	 * @param id id da proposta a ser procurada;
	 * @return ResponseEntity representando o status HTTP 200, 404 ou 500.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<PropostaDetailsResponse> detalha(@PathVariable Long id) {
		Optional<Proposta> propostaOptional = repository.findById(id);
		ResponseEntity<PropostaDetailsResponse> response = propostaOptional.map(proposta -> {
			return ResponseEntity.ok(new PropostaDetailsResponse(proposta));
		}).orElseGet(() -> {
			return ResponseEntity.notFound().build();
		});
		return response;
	}
}
