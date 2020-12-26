package com.jp.baking.service.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({METHOD})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueNumberAttentionToCreateImpl.class)
@Documented
public @interface UniqueNumberAttentionToCreate {

	String message() default "número de atención existente";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
