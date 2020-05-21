package com.gms.model;

public class Shift {
    private int shift_id;
    private String shift_name;
    private String Shift_time;
    private String shift_extra;

    public int getShift_id() {
        return shift_id;
    }

    public void setShift_id(int shift_id) {
        this.shift_id = shift_id;
    }

    public String getShift_name() {
        return shift_name;
    }

    public void setShift_name(String shift_name) {
        this.shift_name = shift_name;
    }

    public String getShift_time() {
        return Shift_time;
    }

    public void setShift_time(String shift_time) {
        Shift_time = shift_time;
    }

    public String getShift_extra() {
        return shift_extra;
    }

    public void setShift_extra(String shift_extra) {
        this.shift_extra = shift_extra;
    }
}
