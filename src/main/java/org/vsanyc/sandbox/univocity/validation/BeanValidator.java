package org.vsanyc.sandbox.univocity.validation;

public interface BeanValidator<T> {

    BeanValidationResult validate(T bean);
}
