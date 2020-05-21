package com.gms.model.member;

import java.sql.Date;

public class PaymentHistory {
    private Date pay_date;
    private double amount;

    public Date getPay_date() {
        return pay_date;
    }

    public void setPay_date(Date pay_date) {
        this.pay_date = pay_date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
