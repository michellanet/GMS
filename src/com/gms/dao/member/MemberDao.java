package com.gms.dao.member;

import com.gms.dao.Database;
import com.gms.model.User;
import com.gms.model.member.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Calendar;

public class MemberDao {
    Connection connection;
    TrainingDao trainingDao;
    PaymentDao paymentDao;

    public MemberDao(){
        connection = Database.getDatabase().getConn();
        trainingDao = new TrainingDao();
        paymentDao = new PaymentDao();
    }


    //Getting member by user id
    public Member getMemberByUserId(int id){

        String sql = "SELECT * FROM member WHERE USER_ID = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            ResultSet rs = preparedStatement.executeQuery();
            Member member = new Member();
            if(rs.next()){
                setMember(member,rs);
                member.setLevel(rs.getInt("member_level"));

                return member;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //getting member by username
    public Member getMemberByUserName(String username){

        String sql = "SELECT * FROM member JOIN users using (user_id) WHERE username = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);

            ResultSet rs = preparedStatement.executeQuery();
            Member member = new Member();
            if(rs.next()){
                setMember(member,rs);

                return member;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //getting all list of members;
    public ObservableList<Member> getMembers(){
        ObservableList<Member> members = FXCollections.observableArrayList();
        String sql = "SELECT * FROM member";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                Member member = new Member();
                setMember(member,rs);
                System.out.println(member);
                members.add(member);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return members;
    }

    //updating the member profile
    public boolean updateMember(Member member){
        String sql = "UPDATE member SET MOBILE =?, EMAIL = ?, ADDRESS1=?, ADDRESS2 = ?,EMERGENCY_CONTACT=?,EMERGENCY_CONTACT_NO=? WHERE USER_ID =?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,member.getContact());
            preparedStatement.setString(2,member.getEmail());
            preparedStatement.setString(3,member.getAddline1());
            preparedStatement.setString(4,member.getAddline2());
            preparedStatement.setString(5,member.getEcontactname());
            preparedStatement.setString(6,member.getEcontactno());
            preparedStatement.setInt(7,member.getUser_id());


            if(preparedStatement.executeUpdate() > 0){
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    //Adding a new member
    public boolean addNewMember(Member member){
        String sql = "INSERT INTO member(USER_ID,FNAME,LNAME,ACCOUNT_CREATED) VALUES(?,?,?,TO_DATE(TO_CHAR(sysdate,'yyyy mm dd'),'YYYY/MM/DD'))";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,member.getUser_id());
            preparedStatement.setString(2,member.getFname());
            preparedStatement.setString(3,member.getLname());


            if(preparedStatement.executeUpdate() > 0){
                member.setId(getMemberByUserId(member.getUser_id()).getId());
                //add default package to gym and cardio
                trainingDao.AddOrremoveTrainingPackageForMember(2,member,true);
                paymentDao.addInvoice(member,"Gym fee",trainingDao.getTrainingPackageByMember(member,"Gym").getPackagePrice());

                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            //e.printStackTrace();
            return false;
        }

    }


    private void setMember(Member member,ResultSet rs) throws SQLException {
        member.setId(rs.getInt("MEMBER_ID"));
        member.setFname(rs.getString("FNAME"));
        member.setLname(rs.getString("LNAME"));
        member.setEmail(rs.getString("EMAIL"));
        member.setContact(rs.getString("MOBILE"));
        member.setAddline1(rs.getString("ADDRESS1"));
        member.setAddline2(rs.getString("ADDRESS2"));
        member.setEcontactname(rs.getString("EMERGENCY_CONTACT"));
        member.setEcontactno(rs.getString("EMERGENCY_CONTACT_NO"));
        member.setAccountcreated(rs.getDate("ACCOUNT_CREATED"));
        member.setNextpaydate(rs.getDate("NEXT_PAY_DATE"));
    }


    public boolean setNextDueDate(Member member,Date nextDate) {

        String sql = "UPDATE member SET NEXT_PAY_DATE = ?  WHERE MEMBER_ID =?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1,nextDate);
            preparedStatement.setInt(2,member.getId());



            if(preparedStatement.executeUpdate() > 0){
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
