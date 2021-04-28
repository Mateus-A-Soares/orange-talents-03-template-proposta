package br.com.zupacademy.mateus.Propostas.proposta;

import java.net.URI;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * 
 * Controller com os end-points relacionados ao CRUD da entidade Proposta.
 * 
 * @author Mateus Soares
 */
@RestController
@RequestMapping("/propostas")
public class PropostaController {

	private PropostaRepository repository;

	public PropostaController(@Autowired PropostaRepository repository) {
		this.repository = repository;
	}

	/**
	 * End-point de URL /propostas que realiza a validação e o cadastro do registro
	 * de uma proposta.
	 * 
	 * @param request proposta a ser cadastrada.
	 * @return ResponseEntity representando o status HTTP 201, 400 ou 500.
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<Void> cadastra(@RequestBody @Valid NovaPropostaRequest request) {
		Proposta proposta = request.toModel();
		repository.save(proposta);
		URI novaPropostaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(novaPropostaUri).build();
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
