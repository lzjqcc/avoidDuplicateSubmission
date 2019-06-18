package com.example.avoidDuplicateSubmission.annotation;

import com.example.avoidDuplicateSubmission.enums.TokenTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Token {
    TokenTypeEnum type() default TokenTypeEnum.PreventingRefreshCommit;
}
