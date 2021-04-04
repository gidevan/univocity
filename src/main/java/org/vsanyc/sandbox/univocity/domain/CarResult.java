package org.vsanyc.sandbox.univocity.domain;

import java.util.ArrayList;
import java.util.List;

public class CarResult {

    private List<Car> cars;
    private List<CarImproved> carImproveds;
    private List<String> messages = new ArrayList<>();

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<CarImproved> getCarImproveds() {
        return carImproveds;
    }

    public void setCarImproveds(List<CarImproved> carImproveds) {
        this.carImproveds = carImproveds;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
