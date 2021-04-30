package br.com.zupacademy.mateus.Propostas.cartao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Classe que cuida da task responsável por procurar os cartões relativos as
 * propostas criadas que ainda não tiveram seus cartões associados.
 * 
 * @author Mateus Soares
 */
@Component
public class PersistsCartaoForPropostaTask {

	private static final Logger log = LoggerFactory.getLogger(PersistsCartaoForPropostaTask.class);

	@Async
	@Scheduled(fixedDelayString =  "${account.service.task.delay}")
	public void execute() {
		log.debug("Executando de task de consulta e persitência dos cartões ainda não atrelados a propostas criadas");
	}
}
