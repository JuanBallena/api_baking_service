package com.jp.baking.service.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = TrueOrFalseImpl.class)
@Documented
public @interface TrueOrFalse {
	
	String message() default "debe ser true o false";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
