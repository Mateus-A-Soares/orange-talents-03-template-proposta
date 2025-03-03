package br.com.zupacademy.mateus.Propostas.proposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.mateus.Propostas.shared.validation.constraint.CpfCnpjFormat;

/**
 * 
 * Classe modelo que representa os dados de uma proposta enviados em uma request de cadastro.
 * 
 * @author Mateus Soares
 */
public class NovaPropostaRequest {

	@NotBlank @CpfCnpjFormat
	private String documento;
	@NotBlank @Email
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String endereco;
	@NotNull @Positive
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
	public NovaPropostaRequest(String documento, @Email String email, @NotBlank String nome, @NotBlank String endereco,
			@NotNull @Positive BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Proposta toModel() {
		return new Proposta(new PropostaDocumento(documento), email, nome, endereco, salario);
	}
}
