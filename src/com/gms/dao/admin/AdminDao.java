package com.gms.dao.admin;

import com.gms.dao.Database;
import com.gms.model.admin.Admin;
import com.gms.model.member.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

import java.sql.*;

public class AdminDao {
    Connection connection;

    //Connecting database
    public AdminDao(){
        connection = Database.getDatabase().getConn();
    }

    //For getting list of admins from database
    public ObservableList<Admin> getAdmins() {

        ObservableList<Admin> admins = FXCollections.observableArrayList();
        String sql = "SELECT * FROM admin";
        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                Admin admin = new Admin();
                setAdmin(admin,rs);
                admins.add(admin);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;

    }

    //For setting values in Admin Table while updating
    private void setAdmin(Admin admin, ResultSet rs) throws SQLException{
        admin.setUser_id(rs.getInt("USER_ID"));
        admin.setAdmin_id(rs.getInt("ADMIN_ID"));
        admin.setFname(rs.getString("FNAME"));
        admin.setLname(rs.getString("LNAME"));
        admin.setEmail(rs.getString("EMAIL"));
        admin.setContact(rs.getString("CONTACT"));

    }

    /*
    //for getting admin
    public Admin getAdminByAdminId(int id){

        String sql = "SELECT * FROM admin WHERE ADMIN_ID=?";
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            ResultSet rs = preparedStatement.executeQuery();
            Admin admin = new Admin();
            if(rs.next()){
                setAdmin(admin,rs);

                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    //For updating admin info in databse
    public boolean updateAdmin(Admin admin){
        String sql = "UPDATE member SET MOBILE =?, EMAIL = ?, ADDRESS1=?, ADDRESS2 = ?,EMERGENCY_CONTACT=?,EMERGENCY_CONTACT_NO=? WHERE USER_ID =?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,admin.getContact());
            preparedStatement.setString(2,admin.getEmail());
            preparedStatement.setString(3,admin.getAddline1());
            preparedStatement.setString(4,admin.getAddline2());
            preparedStatement.setString(5,admin.getEcontactname());
            preparedStatement.setString(6,admin.getEcontactno());
            preparedStatement.setInt(7,admin.getUser_id());


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

*/
    public boolean addNewAdmin(Admin admin) {
            String sql = "INSERT INTO admin(USER_ID,FNAME,LNAME,CONTACT,EMAIL) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,admin.getUser_id());
                preparedStatement.setString(2,admin.getFname());
                preparedStatement.setString(3,admin.getLname());
                preparedStatement.setString(4,admin.getContact());
                preparedStatement.setString(5,admin.getEmail());


                if(preparedStatement.executeUpdate() > 0){
                    return true;
                }else {
                    return false;
                }

            } catch (SQLException e) {
                //e.printStackTrace();
                return false;
            }

    }


}
