package br.com.zupacademy.mateus.Propostas.shared.validation.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

/**
 * Anotação que mescla as anotações CPF e CNPJ para validação de uma variável que possa se encaixar em um dos dois formatos.
 * 
 * @author Mateus Soares
 */
@ConstraintComposition(CompositionType.OR)
@CNPJ(message = "Documento não está formatado como CNPJ")
@CPF(message = "Documento não está formatado como CPF")
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface CpfCnpjFormat {
	@Deprecated
	String message() default "Documento mal formatado";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
