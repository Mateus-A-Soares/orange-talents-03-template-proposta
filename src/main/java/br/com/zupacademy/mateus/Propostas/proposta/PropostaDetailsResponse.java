package br.com.zupacademy.mateus.Propostas.proposta;

import java.math.BigDecimal;

import br.com.zupacademy.mateus.Propostas.cartao.Cartao;
import br.com.zupacademy.mateus.Propostas.cartao.CartaoDetailsResponse;

/**
 * Classe que representa os dados das propostas a serem serializados e enviados pela aplicação
 * 
 * @author Mateus Soares
 */
public class PropostaDetailsResponse {

	private String documento;
	private String email;
	private String endereco;
	private String nome;
	private BigDecimal salario;
	private EstadoProposta estado;
	private CartaoDetailsResponse cartao;

	/**
	 * Construtor que instância e popula um objeto {@link PropostaDetailsResponse} com os dados representativos de uma proposta a ser enviado pela aplicação.
	 * 
	 * @param proposta proposta populada com os dados que serão serializados.
	 */
	public PropostaDetailsResponse(Proposta proposta) {
		documento = proposta.getDocumento();
		email = proposta.getEmail();
		endereco = proposta.getEndereco();
		nome = proposta.getNome();
		salario = proposta.getSalario();
		estado = proposta.getEstado();
		Cartao propostaCartao = proposta.getCartao();
		if(propostaCartao != null)
			cartao = new CartaoDetailsResponse(propostaCartao);
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}
	
	public EstadoProposta getEstado() {
		return estado;
	}
	
	public CartaoDetailsResponse getCartao() {
		return cartao;
	}
}
