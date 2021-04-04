package org.vsanyc.sandbox.univocity.domain;

import com.univocity.parsers.annotations.Parsed;

public class CarImproved extends Car {

    @Parsed
    private int data;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
