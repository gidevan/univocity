package org.vsanyc.sandbox.univocity.validation;

import java.util.ArrayList;
import java.util.List;

public class BeanValidationResult {

    private boolean isValid;
    private List<String> messages = new ArrayList<>();

    public BeanValidationResult(boolean isValid, List<String> messages) {
        this.isValid = isValid;
        this.messages = messages;
    }

    public boolean isValid() {
        return isValid;
    }

    public List<String> getMessages() {
        return messages;
    }

}
