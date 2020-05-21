package com.gms.model;

public class Package {
    private int package_id;
    private String package_name;
    private double monthly_pric;

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public double getMonthly_pric() {
        return monthly_pric;
    }

    public void setMonthly_pric(double monthly_pric) {
        this.monthly_pric = monthly_pric;
    }
}
