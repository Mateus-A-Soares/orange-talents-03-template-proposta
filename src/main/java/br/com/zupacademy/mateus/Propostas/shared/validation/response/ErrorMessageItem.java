package br.com.zupacademy.mateus.Propostas.shared.validation.response;

/**
 *  Classe modelo que representa uma mensagem de erro para um campo.
 *
 * @author Mateus Soares
 */
public class ErrorMessageItem {

	private String field;
	private String message;

	/**
	 * Construtor que instância e popula um objeto {@link ErrorMessageItem} com os dados representativos do erro.
	 *
	 * @param field		nome que descreve aonde o erro ocorreu;
	 * @param message	mensagem descritiva do erro em sí.
	 */
	public ErrorMessageItem(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
}
