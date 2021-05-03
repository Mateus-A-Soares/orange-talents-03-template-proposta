package br.com.zupacademy.mateus.Propostas.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import br.com.zupacademy.mateus.Propostas.cartao.renegociacao.Renegociacao;
import br.com.zupacademy.mateus.Propostas.cartao.vencimento.EmbebbedVencimento;
import br.com.zupacademy.mateus.Propostas.proposta.Proposta;

/**
 * 
 * Classe modelo que representa os dados da entidade cartão.
 * 
 * @author Mateus Soares
 */
@Entity
public class Cartao {

	@Id
	@NotBlank
	private String id;
	
	@OneToOne(mappedBy = "cartao")
	@NotNull
	private Proposta proposta;
	
	@Column(nullable = false)
	@NotNull @PastOrPresent
	private LocalDateTime dataEmissao;
	
	@Column(nullable = false)
	@NotBlank
	private String titular;
	
	@Column(nullable = false)
	@NotNull @PositiveOrZero
	private BigDecimal limite;
	
	@OneToOne @JoinColumn(name = "renegociacao_id", referencedColumnName = "id")
	private Renegociacao renegociacao;

    @Embedded
    @NotNull
	private EmbebbedVencimento vencimento;

	@Deprecated
	public Cartao() {
	}

	/**
	 * Construtor que instância e popula um objeto {@link Cartao} com os dados representativos da entidade cartão.
	 * 
	 * @param id			id do cartão gerado pelo serviço de accounts, deve ser único;
	 * @param proposta		proposta pertencente ao cartão, obrigatória;
	 * @param dataEmissao	data em que o cartão foi emitido, obrigatória e não pode estar no futuro;
	 * @param titular		nome do titular do cartão, obrigatório;
	 * @param limite		limite do cartão, não pode ser um número negativo;
	 * @param vencimento	objeto que representa os dados de vencimento, embute os campos dia e data de criação.
	 */
	public Cartao(@NotBlank String id, @NotNull Proposta proposta, @NotNull @PastOrPresent LocalDateTime dataEmissao,
			@NotBlank String titular, @NotNull @PositiveOrZero BigDecimal limite,
			@NotNull EmbebbedVencimento vencimento) {
		this.id = id;
		this.proposta = proposta;
		this.dataEmissao = dataEmissao;
		this.titular = titular;
		this.limite = limite;
		this.vencimento = vencimento;
	}

	public String getId() {
		return id;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public LocalDateTime getDataEmissao() {
		return dataEmissao;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public Renegociacao getRenegociacao() {
		return renegociacao;
	}

	public EmbebbedVencimento getVencimento() {
		return vencimento;
	}
	
	/**
	 * Adiciona uma renegociação para o registro.
	 * 
	 * @param renegociacao objeto representando o registro de renegociação a ser linkado.
	 */
	public void setRenegociacao(Renegociacao renegociacao) {
		this.renegociacao = renegociacao;
	}
}