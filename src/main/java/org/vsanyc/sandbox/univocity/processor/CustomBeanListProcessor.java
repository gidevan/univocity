package org.vsanyc.sandbox.univocity.processor;

import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.processor.BeanListProcessor;
import org.vsanyc.sandbox.univocity.validation.BeanValidationResult;
import org.vsanyc.sandbox.univocity.validation.BeanValidator;

import java.util.ArrayList;
import java.util.List;

public class CustomBeanListProcessor<T> extends BeanListProcessor<T> {

    private List<String> messages = new ArrayList<>();
    private BeanValidator<T> validator;
    public CustomBeanListProcessor(Class<T> beanType, BeanValidator<T> beanValidator) {
        super(beanType);
        validator = beanValidator;
    }

    public CustomBeanListProcessor(Class<T> beanType, BeanValidator<T> beanValidator, int expectedBeanCount) {
        super(beanType, expectedBeanCount);
        validator = beanValidator;
    }

    @Override
    public void processStarted(ParsingContext context) {
        System.out.println("Process started...");
        super.processStarted(context);
    }

    @Override
    public void processEnded(ParsingContext context) {
        System.out.println("processEnded");
        super.processEnded(context);
    }

    @Override
    public void beanProcessed(T bean, ParsingContext context) {
        System.out.println("beanProcessed");
        BeanValidationResult validationResult = validator.validate(bean);

        if(validationResult.isValid()) {
            super.beanProcessed(bean, context);
        } else {
            String message = "Wrong row: " + context.currentLine() + ".";
            String validationMessages = String.join(";", validationResult.getMessages());

            messages.add(message + validationMessages);
        }

    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
