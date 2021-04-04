package org.vsanyc.sandbox.univocity.domain;

import com.univocity.parsers.annotations.Parsed;

public class Car {
    @Parsed
    private String year;
    @Parsed
    private String make;
    @Parsed
    private String model;
    @Parsed
    private String description;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
