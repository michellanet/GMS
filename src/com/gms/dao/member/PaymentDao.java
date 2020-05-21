package com.gms.dao.member;

import com.gms.dao.Database;
import com.gms.model.member.Member;
import com.gms.model.member.Payment;
import com.gms.model.member.PaymentHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Calendar;

public class PaymentDao {
    Connection connection;
    public PaymentDao(){
        connection = Database.getDatabase().getConn();
    }

    public boolean addInvoice(Member member, String pack, double price){
        String  sql = "INSERT INTO INVOICE(MEMBER_ID,PACKAGE,PRICE) VALUES(?,?,?)";


        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,member.getId());
            statement.setString(2,pack);
            statement.setDouble(3, price);
            if(statement.executeUpdate()>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public ObservableList<Payment> getAlllastMonthBalanceList(Member member) {
        ObservableList<Payment> lastmonthBalance = FXCollections.observableArrayList();

        String sql = "SELECT * FROM INVOICE WHERE MEMBER_ID = ? AND INVOICE_DATE<=? AND INVOICE_DATE>?";


        Calendar c = Calendar.getInstance();
        Date currentTime = new java.sql.Date(c.getTime().getTime());
        c.setTime(member.getNextpaydate());
        c.add(Calendar.DATE,-30);
        Date previousDate = new java.sql.Date(c.getTime().getTime());

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,member.getId());
            statement.setDate(2,member.getNextpaydate());
            statement.setDate(3, previousDate);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Payment payment = new Payment();
                payment.setAmount(rs.getDouble("PRICE"));
                payment.setInvoice_date(rs.getDate("INVOICE_DATE"));
                payment.setPayment_package(rs.getString("PACKAGE"));
                lastmonthBalance.add(payment);
                System.out.println("i am here");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastmonthBalance;


    }

    public ObservableList<PaymentHistory> getLastMonthPayment(Member member) {

        ObservableList<PaymentHistory> lastMonthHistory = FXCollections.observableArrayList();

        String sql = "SELECT * FROM PAYMENT_HISTORY WHERE MEMBER_ID = ? AND PAY_DATE<? AND PAY_DATE>?";


        Calendar c = Calendar.getInstance();
        c.setTime(member.getNextpaydate());
        c.add(Calendar.DATE,-30);
        Date previousDate = new java.sql.Date(c.getTime().getTime());

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,member.getId());
            statement.setDate(2,member.getNextpaydate());
            statement.setDate(3, previousDate);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                PaymentHistory paymentHistory = new PaymentHistory();
                paymentHistory.setAmount(rs.getDouble("AMOUNT"));
                paymentHistory.setPay_date(rs.getDate("PAY_DATE"));
                lastMonthHistory.add(paymentHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastMonthHistory;
    }


    public ObservableList<PaymentHistory> getAllpayHistory(Member member) {
        ObservableList<PaymentHistory> allHistory = FXCollections.observableArrayList();

        String sql = "SELECT * FROM PAYMENT_HISTORY WHERE MEMBER_ID = ?";


        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,member.getId());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                PaymentHistory paymentHistory = new PaymentHistory();
                paymentHistory.setAmount(rs.getDouble("AMOUNT"));
                paymentHistory.setPay_date(rs.getDate("PAY_DATE"));
                allHistory.add(paymentHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allHistory;


    }

    public boolean addPaymentHistory(Member member, double amount){
        String  sql = "INSERT INTO PAYMENT_HISTORY(MEMBER_ID,PAY_DATE,AMOUNT) VALUES(?,?,?)";
        Calendar c = Calendar.getInstance();
        Date currentTime = new java.sql.Date(c.getTime().getTime());

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,member.getId());
            statement.setDate(2,currentTime);
            statement.setDouble(3, amount);
            if(statement.executeUpdate()>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
