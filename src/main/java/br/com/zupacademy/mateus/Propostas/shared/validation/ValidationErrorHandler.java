package br.com.zupacademy.mateus.Propostas.shared.validation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

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
	 *  Invocado quando qualquer exception não tratada / esperada for lançada,
	 * popula um {@link ErrorMessageResponse} para ser serializado como resposta para o usuário final.
	 * 
	 * @param request	representação da request da qual vai ser tirada o caminho que o usuário acessou;
	 * @return {@link ErrorMessageResponse} populado com o erro 500 e URI da request para ser serializado como resposta.
	 */
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErrorMessageResponse handle(WebRequest request) {
		String errorMessage = messageSource.getMessage("InternalServerError", null, "500", null);
		String path = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
		ErrorMessageItem error = new ErrorMessageItem("server", errorMessage);
		return new ErrorMessageResponse(HttpStatus.INTERNAL_SERVER_ERROR, path, error);
	}
}
