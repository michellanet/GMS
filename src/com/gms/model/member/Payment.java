package com.gms.model.member;

import java.sql.Date;

public class Payment {

    String payment_package;
    double amount;
    Date invoice_date;

    public String getPayment_package() {
        return payment_package;
    }

    public void setPayment_package(String payment_package) {
        this.payment_package = payment_package;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(Date invoice_date) {
        this.invoice_date = invoice_date;
    }
}
