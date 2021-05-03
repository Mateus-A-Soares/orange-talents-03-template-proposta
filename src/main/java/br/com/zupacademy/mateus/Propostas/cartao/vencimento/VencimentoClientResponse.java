package br.com.zupacademy.mateus.Propostas.cartao.vencimento;

import java.time.LocalDateTime;

/**
 *  Classe representativa da data de vencimento de um cartão, enviada no corpo da requisição na
 * rota de solicitação de um cartão, na API de accounts.
 * 
 * @author Mateus Soares
 */
public class VencimentoClientResponse {
	
	private String id;
	private Integer dia;
	private LocalDateTime dataDeCriacao;
	
	/**
	 *  Construtor que instância e popula um objeto {@link VencimentoClientResponse}
	 * com os dados representativos da data de vencimento de um cartão, na rota de solicitação de
	 * um cartão, na API de accounts.
	 * 
	 * @param id			id da renegociação, deve ser único;
	 * @param dia			dia de vencimento do cartão, deve ser um dia válido;
	 * @param dataDeCriacao	data de criação do vencimento, deve ser uma data válida no passado.
	 */
	public VencimentoClientResponse(String id, Integer dia, LocalDateTime dataDeCriacao) {
		this.id = id;
		this.dia = dia;
		this.dataDeCriacao = dataDeCriacao;
	}

	@Override
	public String toString() {
		return "VencimentoResponse [id=" + id + ", dia=" + dia + ", dataDeCriacao=" + dataDeCriacao + "]";
	}
}
