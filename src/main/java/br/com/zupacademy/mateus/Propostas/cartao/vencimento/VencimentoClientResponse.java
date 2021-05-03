package br.com.zupacademy.mateus.Propostas.cartao.vencimento;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

/**
 *  Classe representativa da data de vencimento de um cartão, enviada no corpo da requisição na
 * rota de solicitação de um cartão, na API de accounts.
 * 
 * @author Mateus Soares
 */
public class VencimentoClientResponse {
	
	@NotNull @Min(1) @Max(31)
	private Integer dia;
	@NotNull @PastOrPresent
	private LocalDateTime dataDeCriacao;
	
	/**
	 *  Construtor que instância e popula um objeto {@link VencimentoClientResponse}
	 * com os dados representativos da data de vencimento de um cartão, na rota de solicitação de
	 * um cartão, na API de accounts.
	 * 
	 * @param dia			dia de vencimento do cartão, deve ser um dia válido;
	 * @param dataDeCriacao	data de criação do vencimento, deve ser uma data válida no passado.
	 */
	public VencimentoClientResponse(@NotNull @Min(1) @Max(31) Integer dia,
			@NotNull @PastOrPresent LocalDateTime dataDeCriacao) {
		this.dia = dia;
		this.dataDeCriacao = dataDeCriacao;
	}

	public EmbebbedVencimento toModel() {
		return new EmbebbedVencimento(dia, dataDeCriacao);
	}
}
