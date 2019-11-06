package com.hx.external.conf;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = PhoneValidate.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface PhoneNum {

    String message() default "手机号不符合规范!!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };

}
