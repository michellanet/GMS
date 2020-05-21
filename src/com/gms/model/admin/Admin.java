package com.gms.model.admin;

import java.sql.Date;

public class Admin {
    
    private int admin_id;
    private int user_id;
    private String fname;
    private String lname;
    private String contact;
    private String email;
    private String addline1;
    private String addline2;
    private String econtactno;
    private String econtactname;
    private Date accountcreated;

    public Admin(){}

    public Admin(int admin_id, int user_id, String fname, String lname, String contact, String email, String addline1, String addline2, String econtactno, String econtactname, Date accountcreated) {
        this.admin_id = admin_id;
        this.user_id = user_id;
        this.fname = fname;
        this.lname = lname;
        this.contact = contact;
        this.email = email;
        this.addline1 = addline1;
        this.addline2 = addline2;
        this.econtactno = econtactno;
        this.econtactname = econtactname;
        this.accountcreated = accountcreated;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddline1() {
        return addline1;
    }

    public void setAddline1(String addline1) {
        this.addline1 = addline1;
    }

    public String getAddline2() {
        return addline2;
    }

    public void setAddline2(String addline2) {
        this.addline2 = addline2;
    }

    public String getEcontactno() {
        return econtactno;
    }

    public void setEcontactno(String econtactno) {
        this.econtactno = econtactno;
    }

    public String getEcontactname() {
        return econtactname;
    }

    public void setEcontactname(String econtactname) {
        this.econtactname = econtactname;
    }

    public Date getAccountcreated() {
        return accountcreated;
    }

    public void setAccountcreated(Date accountcreated) {
        this.accountcreated = accountcreated;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", user_id=" + user_id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
