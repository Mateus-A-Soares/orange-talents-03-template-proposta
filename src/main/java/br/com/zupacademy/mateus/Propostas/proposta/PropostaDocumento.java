package br.com.zupacademy.mateus.Propostas.proposta;

import org.springframework.util.Assert;

/**
 * Classe representativa do parâmetro documento para entidade Proposta.
 *  
 * @author Mateus Soares
 */
public class PropostaDocumento {
	
	private String documento;

	/**
	 * Define um documento utilizado para uma proposta, deve estar formatado corretamente como CPF ou CNPJ.
	 * Deve corresponde a um dos seguintes formatos: CPF : XXX.XXX.XXX-XX ou XXXXXXXXXXX, CNPJ : XX.XXX.XXX/XXXX-XX ou XXXXXXXXXXXXXX,
	 * onde X equivale a um número inteiro de 0 a 9.
	 * 
	 * 
	 * @param documento String representando um cpf ou cnpj devidamente formatado.
	 */
	public PropostaDocumento(String documento) {
		Assert.isTrue(documento.matches("([0-9]{2}[.]?[0-9]{3}[.]?[0-9]{3}[/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{14})") ||
					documento.matches("([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}-[0-9]{2})|([0-9]{11})"),
					"Tentando cadastrar documento fora do padrão!");
		this.documento = documento.replaceAll("[./-]", "");
	}
	
	public String getDocumento() {
		return documento;
	}
}
