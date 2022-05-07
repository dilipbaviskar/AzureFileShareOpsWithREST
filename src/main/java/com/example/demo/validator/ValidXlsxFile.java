package com.example.demo.validator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ZipFileValidator.class})
public @interface ValidXlsxFile {
    String message() default "Invalid xlsx file";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}