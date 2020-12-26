package com.jp.baking.service.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.jp.baking.service.interf.UniqueDataValidator;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueToCreateImpl.class)
@Documented
public @interface UniqueToCreate {
	
	String message() default "message unique to create";
	
	String property();
	
	Class<? extends UniqueDataValidator> manager();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
