package com.gms.model.member;

import java.sql.Date;

public class Member {
    private int id;
    private int user_id;
    private String fname;
    private String lname;
    private String addline1;
    private String addline2;
    private String email;

    private String econtactname;
    private String contact;

    private String econtactno;
    private Date accountcreated;
    private int level;
    private Date nextpaydate;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Member(){

    }

    public Member(int id, int user_id, String fname, String lname, String addline1, String addline2, String email, String econtactname, String contact, String econtactno, Date accountcreated) {
        this.id = id;
        this.user_id = user_id;
        this.fname = fname;
        this.lname = lname;
        this.addline1 = addline1;
        this.addline2 = addline2;
        this.email = email;
        this.econtactname = econtactname;
        this.contact = contact;
        this.econtactno = econtactno;
        this.accountcreated = accountcreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEcontactname() {
        return econtactname;
    }

    public void setEcontactname(String econtactname) {
        this.econtactname = econtactname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEcontactno() {
        return econtactno;
    }

    public void setEcontactno(String econtactno) {
        this.econtactno = econtactno;
    }

    public Date getAccountcreated() {
        return accountcreated;
    }

    public void setAccountcreated(Date accountcreated) {
        this.accountcreated = accountcreated;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", addline1='" + addline1 + '\'' +
                ", addline2='" + addline2 + '\'' +
                ", email='" + email + '\'' +
                ", econtactname='" + econtactname + '\'' +
                ", contact='" + contact + '\'' +
                ", econtactno='" + econtactno + '\'' +
                ", accountcreated=" + accountcreated +
                ", level=" + level +
                ", nextpaydate=" + nextpaydate +
                '}';
    }

    public Date getNextpaydate() {
        return nextpaydate;
    }

    public void setNextpaydate(Date nextpaydate) {
        this.nextpaydate = nextpaydate;
    }
}
