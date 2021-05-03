package br.com.zupacademy.mateus.Propostas.cartao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.zupacademy.mateus.Propostas.proposta.Proposta;
import br.com.zupacademy.mateus.Propostas.proposta.PropostaRepository;

/**
 * Classe que cuida da task responsável por procurar os cartões relativos as
 * propostas criadas que ainda não tiveram seus cartões associados.
 * 
 * @author Mateus Soares
 */
@Component
public class PersistsCartaoForPropostaTask {

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
	@Scheduled(fixedDelayString =  "${account.service.task.delay}")
	@Transactional
	public void execute() {
		Iterable<Proposta> propostas = propostaRepository.findByCartaoIsNull();
		propostas.forEach(proposta -> {
			try {
				log.debug("Consultando cartão para proposta {}", proposta.getId());
				CartaoClientResponse cartao = client.cartaoParaProposta(proposta.getId().toString());
				Assert.isTrue(cartao.getIdProposta().equals(proposta.getId()), "Cartão retornado não pertence a proposta!");
				proposta.setCartao(cartao.toModel(proposta));
				propostaRepository.save(proposta);
				log.debug("Sucesso, cartão {} cadastrado para proposta {}", cartao.getId(), proposta.getId());
			} catch (Exception exception) {
				exception.printStackTrace();
				log.debug("Falha no processo de geração de cartão para a proposta {}, mensagem = {}", proposta.getId(),
						 exception.getMessage());
			}
		});
	}
}