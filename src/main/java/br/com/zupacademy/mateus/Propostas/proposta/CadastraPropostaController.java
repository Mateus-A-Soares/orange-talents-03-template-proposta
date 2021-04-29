package br.com.zupacademy.mateus.Propostas.proposta;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zupacademy.mateus.Propostas.proposta.observers.NewPropostaObserver;
import br.com.zupacademy.mateus.Propostas.shared.validation.ApiErrorException;
import br.com.zupacademy.mateus.Propostas.shared.validation.response.ErrorMessageItem;

/**
 * 
 * Controller com os end-points relacionados a persistência de novos registros da entidade Proposta.
 * 
 * @author Mateus Soares
 */
@RestController
@RequestMapping("/propostas")
public class CadastraPropostaController {

	private List<NewPropostaObserver> newPropostaObservers;
	private PropostaRepository repository;

	public CadastraPropostaController(@Autowired PropostaRepository repository,
			@Autowired List<NewPropostaObserver> newPropostaObservers) {
		this.repository = repository;
		this.newPropostaObservers = newPropostaObservers;
	}

	/**
	 * End-point de URL /propostas que realiza a validação e o cadastro do registro
	 * de uma proposta.
	 * 
	 * @param request proposta a ser cadastrada.
	 * @return ResponseEntity representando o status HTTP 201, 400 ou 500.
	 */
	@PostMapping
	public ResponseEntity<Void> cadastra(@RequestBody @Valid NovaPropostaRequest request) {
		Proposta proposta = request.toModel();
		Optional<Proposta> propostaMesmoDocumento = repository.findByDocumento(proposta.getDocumento());
		if (propostaMesmoDocumento.isPresent())
			throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY,
					new ErrorMessageItem("documento", "documento já cadastrado"));
		newPropostaObservers.forEach(observer -> observer.update(proposta));
		URI novaPropostaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(novaPropostaUri).build();
	}
}
