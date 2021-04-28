package br.com.zupacademy.mateus.Propostas.shared.validation;

import org.springframework.http.HttpStatus;

import br.com.zupacademy.mateus.Propostas.shared.validation.response.ErrorMessageItem;

/**
 * Exception lançada durante a execução da aplicação para que o Handler de exceptions possa tratar e criar uma resposta de erro de acordo com os dados populados nela.
 * 
 * @author Mateus Soares
 */
@SuppressWarnings("serial")
public class ApiErrorException extends RuntimeException {

	private HttpStatus httpStatus;
	private ErrorMessageItem errorMessageItem;

	/**
	 *  Popula um objeto que estende {@link RuntimeException} quando for lançado deve ser capturado pelo handler de exceptions,
	 * para que a API possa retornar uam resposta formatada corretamente.
	 *  
	 * @param httpStatus		status HTTP que deve estar na resposta formulada;
	 * @param errorMessageItem  item que representa a mensagem de erro.
	 */
	public ApiErrorException(HttpStatus httpStatus, ErrorMessageItem errorMessageItem) {
		this.httpStatus = httpStatus;
		this.errorMessageItem = errorMessageItem;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public ErrorMessageItem getErrorMessageItem() {
		return errorMessageItem;
	}
}