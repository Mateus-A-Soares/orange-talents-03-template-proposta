package br.com.zupacademy.mateus.Propostas.proposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.mateus.Propostas.shared.validation.constraint.CpfCnpjFormat;

/**
 * 
 * Classe modelo que representa os dados da entidade proposta.
 * 
 * @author Mateus Soares
 */
@Entity
public class Proposta {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank @CpfCnpjFormat
	@Column(nullable = false, unique = true)
	private String documento;
	
	@Email
	@Column(nullable = false)
	private String email;
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@NotBlank
	@Column(nullable = false)
	private String endereco;
	
	@NotNull @Positive
	@Column(nullable = false)
	private BigDecimal salario;

    @Enumerated(EnumType.STRING)
	private EstadoProposta estado;
	
	@Deprecated
	public Proposta() {
	}

	/**
	 * Construtor que instância e popula um objeto {@link Proposta} com os dados representativos da entidade proposta.
	 * 
	 * @param documento cpf ou cnpj da entidade cadastrante, não pode estar vazia e deve estar formatada corretamente;
	 * @param email email do cadastrante, não pode estar vazia e deve estar formatada corretamente;
	 * @param nome nome da proposta, não pode estar vazia e deve estar formatada corretamente;
	 * @param endereco endereço do cadastrante, não pode estar vazio;
	 * @param salario salario do cadastrante, tem que ser um valor positivo.
	 */
	public Proposta(PropostaDocumento documento, @Email String email, @NotBlank String nome, @NotBlank String endereco,
			@NotNull @Positive BigDecimal salario) {
		this.documento = documento.getDocumento();
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public Long getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}
	
	public EstadoProposta getEstado() {
		return estado;
	}
	
	public void setEstado(@NotNull EstadoProposta estado) {
		this.estado = estado;
	}
}
