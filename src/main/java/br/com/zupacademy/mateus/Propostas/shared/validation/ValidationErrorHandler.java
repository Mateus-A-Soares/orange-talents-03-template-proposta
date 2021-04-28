package br.com.zupacademy.mateus.Propostas.shared.validation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import br.com.zupacademy.mateus.Propostas.shared.validation.response.ErrorMessageResponse;
import br.com.zupacademy.mateus.Propostas.shared.validation.response.ErrorMessageItem;

/**
 * Handler que captura exceptions lançadas durante a execução dos end-points e
 * modifica a resposta que  aplicação devolverá para o usuário final.
 * 
 * @author Mateus Soares
 */
@RestControllerAdvice
public class ValidationErrorHandler {

	@Autowired
	private MessageSource messageSource;
	
	/** 
	 *  Invocado ocorre quando uma {@link ApiErrorException} é lançada durante a execução do end point relativo a URL da request,
	 * formula um {@link ResponseEntity} com o status passado na exception e popula o corpo com um {@link ErrorMessageResponse}.
	 * 
	 * @param exception	exception lançada pela aplicação que deve representar um erro ocorrido durante a execução de um end point;
	 * @param request	representação da request da qual vai ser tirada o caminho que o usuário acessou;
	 * @return {@link ResponseEntity} representando a resposta da API para a requisição.
	 */
	@ExceptionHandler(ApiErrorException.class)
	public ResponseEntity<ErrorMessageResponse> handleApiError(ApiErrorException exception, WebRequest request) {
		String path = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
		ErrorMessageResponse body = new ErrorMessageResponse(exception.getHttpStatus(), path, exception.getErrorMessageItem());
		return ResponseEntity.status(exception.getHttpStatus()).body(body);
	}

	/**
	 *  Invocado quando qualquer exception do tipo {@link BindException} for lançada,
	 * popula um {@link ErrorMessageResponse} para ser serializado como resposta para o usuário final.
	 * 
	 * @param bindingResult	BindingResult gerado pela plataforma populado com os erros de validação;
	 * @param request		representação da request da qual vai ser tirada o caminho que o usuário acessou;
	 * @return {@link ErrorMessageResponse} populado com os erros do BindingResult e URI da request para ser serializado como resposta.
	 */
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public ErrorMessageResponse handle(BindingResult bindingResult, WebRequest request) {
		String path = ((ServletWebRequest) request).getRequest().getRequestURI();
		List<FieldError> bindingResultFieldErrors = bindingResult.getFieldErrors();
		List<ErrorMessageItem> errors = bindingResultFieldErrors.stream().map(error -> {
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			return new ErrorMessageItem(error.getField(), message);
		}).collect(Collectors.toList());
		return new ErrorMessageResponse(HttpStatus.BAD_REQUEST, path, errors);
	}
	
	/**
	 *  Invocado quando qualquer exception do tipo {@link MethodArgumentNotValidException} for lançada,
	 * popula um {@link ErrorMessageResponse} para ser serializado como resposta para o usuário final.
	 * 
	 * @param exception	exception lançaca, encapsulando o BindingResult populado com os erros de validação;
	 * @param request	representação da request da qual vai ser tirada o caminho que o usuário acessou;
	 * @return {@link ErrorMessageResponse} populado com os erros do BindingResult e URI da request para ser serializado como resposta.
	 */
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorMessageResponse handle(MethodArgumentNotValidException exception, WebRequest request) {
		BindingResult bindingResult = exception.getBindingResult();
		return handle(bindingResult, request);
	}
	
	/**
	 *  Invocado quando a implementação ativa do {@link HttpMessageConverter} não consegue ler o corpo da requisição enviada para algum end point da aplicação,
	 * popula um {@link ErrorMessageResponse} para ser serializado como resposta para o usuário final.
	 * 
	 * @param request	representação da request da qual vai ser tirada o caminho que o usuário acessou;
	 * @return {@link ErrorMessageResponse} populado com o erro 400 e URI da request para ser serializado como resposta.
	 */
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ErrorMessageResponse handleUnprocessableEntity(WebRequest request) {
		String errorMessage = messageSource.getMessage("BadRequest", null, "400 Bad Request", null);
		String path = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
		ErrorMessageItem error = new ErrorMessageItem("request body", errorMessage);
		return new ErrorMessageResponse(HttpStatus.BAD_REQUEST, path, error);
	}
	
	/**
	 *  Invocado quando a aplicação não encontra um end point relativo a URL da request,
	 * popula um {@link ErrorMessageResponse} para ser serializado como resposta para o usuário final.
	 * 
	 * @param request	representação da request da qual vai ser tirada o caminho que o usuário acessou;
	 * @return {@link ErrorMessageResponse} populado com o erro 404 e URI da request para ser serializado como resposta.
	 */
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ErrorMessageResponse handleNotFound(WebRequest request) {
		String errorMessage = messageSource.getMessage("NotFound", null, "404 Not Found", null);
		String path = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
		ErrorMessageItem error = new ErrorMessageItem("path", errorMessage);
		return new ErrorMessageResponse(HttpStatus.NOT_FOUND, path, error);
	}
	
	/**
	 *  Invocado quando o end point relativo a URL da request não suporta o método definido na request,
	 * popula um {@link ErrorMessageResponse} para ser serializado como resposta para o usuário final.
	 * 
	 * @param request	representação da request da qual vai ser tirada o caminho que o usuário acessou;
	 * @return {@link ErrorMessageResponse} populado com o erro 405 e URI da request para ser serializado como resposta.
	 */
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ErrorMessageResponse handleMethodNotAllowed(WebRequest request) {
		String errorMessage = messageSource.getMessage("MethodNotAllowed", null, "405 Method Not Allowed", null);
		String path = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
		ErrorMessageItem error = new ErrorMessageItem("method", errorMessage);
		return new ErrorMessageResponse(HttpStatus.METHOD_NOT_ALLOWED, path, error);
	}
	

	/**
	 *  Invocado quando qualquer exception não tratada / esperada for lançada,
	 * popula um {@link ErrorMessageResponse} para ser serializado como resposta para o usuário final.
	 * 
	 * @param request	representação da request da qual vai ser tirada o caminho que o usuário acessou;
	 * @return {@link ErrorMessageResponse} populado com o erro 500 e URI da request para ser serializado como resposta.
	 */
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErrorMessageResponse handleInternalServerError(WebRequest request) {
		String errorMessage = messageSource.getMessage("InternalServerError", null, "500 Internal Server Error", null);
		String path = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
		ErrorMessageItem error = new ErrorMessageItem("server", errorMessage);
		return new ErrorMessageResponse(HttpStatus.INTERNAL_SERVER_ERROR, path, error);
	}
}
