package br.com.zupacademy.mateus.Propostas.cartao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.mateus.Propostas.proposta.Proposta;
import br.com.zupacademy.mateus.Propostas.proposta.PropostaRepository;

/**
 * Classe que cuida da task responsável por procurar os cartões relativos as
 * propostas criadas que ainda não tiveram seus cartões associados.
 * 
 * @author Mateus Soares
 */
@Component
class PersistsCartaoForPropostaTask {

	private static final Logger log = LoggerFactory.getLogger(PersistsCartaoForPropostaTask.class);
	
	private PropostaRepository propostaRepository;
	
	private CartaoClient client;

	PersistsCartaoForPropostaTask(@Autowired PropostaRepository propostaRepository,
			@Autowired CartaoClient client) {
		this.propostaRepository = propostaRepository;
		this.client = client;
	}

	/**
	 *  Task responsável por procurar os cartões relativos as propostas criadas
	 * que ainda não tiveram seus cartões associados.
	 */
	@Async
	@Scheduled(fixedDelayString =  "${account.service.task.delay}")
	private void execute() {
		Iterable<Proposta> propostas = propostaRepository.findAll();
		propostas.forEach(proposta -> {
			log.debug("Consultando cartão para proposta {}", proposta.getId());
			String clientResponse = client.cartaoParaProposta(proposta.getId().toString());
			log.debug("Sucesso, cartão encontrado para proposta {} : {}", proposta.getId(), clientResponse);
		});
	}
}
