package com.gms.model.member;

public class Preference {
    private String shift;
    private BodyType bodyType;

    public Preference() {

    }

    public Preference(String shift, BodyType ideal_body) {
        this.shift = shift;
        this.bodyType = ideal_body;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public BodyType getIdeal_body() {
        return bodyType;
    }

    public void setIdeal_body(BodyType ideal_body) {
        this.bodyType = ideal_body;
    }
}
