package org.vsanyc.sandbox.univocity.validation;

import org.vsanyc.sandbox.univocity.domain.CarImproved;

import java.util.ArrayList;
import java.util.List;

public class CarImprovedValidator implements BeanValidator<CarImproved> {

    @Override
    public BeanValidationResult validate(CarImproved bean) {
        List<String> messages = new ArrayList<>();

        if (bean.getYear() == null) {
            messages.add("Year is empty");
        }
        if (bean.getMake() == null) {
            messages.add("Make is empty");
        }
        if (bean.getModel() == null) {
            messages.add("Model is empty");
        }

        return new BeanValidationResult(messages.isEmpty(), messages);
    }
}
