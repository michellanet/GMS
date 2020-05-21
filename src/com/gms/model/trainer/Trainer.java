package com.gms.model.trainer;

public class Trainer {

    private int trainer_id;
    private int user_id;
    private String fname;
    private String lname;
    private String experience;
    private String email;
    private String phone;
    private String trainer_type;
    public Trainer(){

    }

    public Trainer(int trainer_id, String fname, String lname, String experience, String email, String phone, String trainer_type) {
        this.trainer_id = trainer_id;
        this.user_id = user_id;
        this.fname = fname;
        this.lname = lname;
        this.experience = experience;
        this.email = email;
        this.phone = phone;
        this.trainer_type = trainer_type;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTrainer_type() {
        return trainer_type;
    }

    public void setTrainer_type(String trainer_type) {
        this.trainer_type = trainer_type;
    }

    @Override
    public String toString() {
        return fname + " " + lname;
    }


}