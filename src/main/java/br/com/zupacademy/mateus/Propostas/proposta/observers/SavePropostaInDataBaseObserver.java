package br.com.zupacademy.mateus.Propostas.proposta.observers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.zupacademy.mateus.Propostas.proposta.Proposta;
import br.com.zupacademy.mateus.Propostas.proposta.PropostaRepository;

/**
 * Observer que efetivamente cadastra uma proposta no banco de dados.
 * Deve ser necessariamente o primeiro Observer invocado.
 * 
 * @author Mateus Soares
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
public class SavePropostaInDataBaseObserver implements NewPropostaObserver {

	private PropostaRepository repository;
	
	public SavePropostaInDataBaseObserver(@Autowired PropostaRepository repository) {
		this.repository = repository;
	}

	@Transactional
	@Override
	public void update(Proposta proposta) {
		repository.save(proposta);
	}
}
