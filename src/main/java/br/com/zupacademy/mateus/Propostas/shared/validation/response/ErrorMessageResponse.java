package br.com.zupacademy.mateus.Propostas.shared.validation.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

/**
 *  Classe que representa qualquer mensagem de erro que a aplicação for serializar como resposta.
 * 
 * @author Mateus Soares
 */
public class ErrorMessageResponse {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	
	private LocalDateTime timestamp;
	private Integer status;
	private String path;
	private List<ErrorMessageItem> errors;
	
	/**
	 * Construtor que instância e popula um objeto {@link ErrorMessageResponse} com os dados representativos do erro.
	 *
	 * @param status	status HTTP que deve ser retornado;
	 * @param path		rota onde ocorreu a requisição;
	 * @param errors	lista de {@link ErrorMessageItem} que representa as mensagens de erro.
	 */
	public ErrorMessageResponse(HttpStatus status, String path, List<ErrorMessageItem> errors) {
		timestamp = LocalDateTime.now();
		this.status = status.value();
		this.path = path;
		this.errors = errors;
	}
	
	/**
	 * Construtor que instância e popula um objeto {@link ErrorMessageResponse} com os dados representativos do erro.
	 *
	 * @param status	status HTTP que deve ser retornado;
	 * @param path		rota onde ocorreu a requisição;
	 * @param error 	objeto {@link ErrorMessageItem} que representa o erro que ocorreu.
	 */
	public ErrorMessageResponse(HttpStatus status, String path, ErrorMessageItem error) {
		timestamp = LocalDateTime.now();
		this.status = status.value();
		this.path = path;
		this.errors = Arrays.asList(error);
	}

	public static DateTimeFormatter getFormatter() {
		return formatter;
	}

	public String getTimestamp() {
		return formatter.format(timestamp);
	}

	public Integer getStatus() {
		return status;
	}

	public String getPath() {
		return path;
	}

	public List<ErrorMessageItem> getErrors() {
		return errors;
	}
}
