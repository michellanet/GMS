package com.gms.model.member;

public class BodyType {

    private int id;
    private String bodyType;

   public BodyType(){

    }

    public BodyType(int id, String bodyType) {
        this.id = id;
        this.bodyType = bodyType;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return bodyType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
