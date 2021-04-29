package br.com.zupacademy.mateus.Propostas.proposta.observers;

import br.com.zupacademy.mateus.Propostas.proposta.Proposta;

/**
 *  Interface que deve ser implementada pelos Observers que executarão
 * após o cadastro de uma nova proposta.
 * 
 * @author Mateus Soares
 */
public interface NewPropostaObserver {
	
	/**
	 * Método chamado invocado após uma {@link Proposta} ter sido cadastrada.
	 * 
	 * @param proposta proposta cadastrada.
	 */
	void update(Proposta proposta);
}
