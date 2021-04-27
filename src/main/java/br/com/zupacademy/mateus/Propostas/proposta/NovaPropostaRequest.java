package br.com.zupacademy.mateus.Propostas.proposta;

import java.math.BigDecimal;

/**
 * 
 * Classe modelo que representa os dados de uma proposta enviados em uma request de cadastro.
 * 
 * @author Mateus Soares
 */
public class NovaPropostaRequest {

	private String documento;
	private String email;
	private String nome;
	private String endereco;
	private BigDecimal salario;

	/**
	 * Construtor que instância e popula um objeto {@link NovaPropostaRequest} com os dados representativos de uma proposta a ser cadastrada.
	 * 
	 * @param documento cpf ou cnpj da entidade cadastrante, não pode estar vazia e deve estar formatada corretamente;
	 * @param email email do cadastrante, não pode estar vazia e deve estar formatada corretamente;
	 * @param nome nome da proposta, não pode estar vazia e deve estar formatada corretamente;
	 * @param endereco endereço do cadastrante, não pode estar vazio;
	 * @param salario salario do cadastrante, tem que ser um valor positivo.
	 */
	public NovaPropostaRequest(String documento, String email, String nome, String endereco, BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "NovaPropostaRequest [documento=" + documento + ", email=" + email + ", nome=" + nome + ", endereco="
				+ endereco + ", salario=" + salario + "]";
	}
}
