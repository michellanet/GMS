package com.gms.dao;

import com.gms.model.User;

import java.sql.*;

public class UserDao {

    Connection connection;

    public UserDao(){
        connection = Database.getDatabase().getConn();
    }

    public void login(User user) throws SQLException {
        String sql = "SELECT * FROM users JOIN user_type on users.user_type_id = user_type.user_type_id WHERE USERNAME = ? AND PASSWORD = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,user.getUsername());
        preparedStatement.setString(2,user.getPassword());

        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            user.setId(rs.getInt("USER_ID"));
            user.setUser_type(rs.getString("TYPE"));
        }

    }

    public boolean addNewUser(User user){
            if(!userExist(user)){
                //usertype 1: admin
                //usertype 2: member
                //usertype 3: trainer
                int user_type = 2;
                if(user.getUser_type() =="Admin"){
                    user_type = 1;
                }else if(user.getUser_type() =="Member"){
                    user_type = 2;
                }else if(user.getUser_type() =="Trainer"){
                    user_type = 3;
                }
                try {
                    String sql = "INSERT INTO USERS(USER_TYPE_ID,USERNAME,PASSWORD) VALUES(?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, user_type);
                    preparedStatement.setString(2, user.getUsername());
                    preparedStatement.setString(3, user.getPassword());


                   if(preparedStatement.executeUpdate() > 0){
                       //now get the user
                       String getUserIdSql = "SELECT user_id from users where username = '"+user.getUsername()+"'";
                       Statement statement = connection.createStatement();
                       ResultSet rs = statement.executeQuery(getUserIdSql);
                       if(rs.next()){
                           user.setId(rs.getInt("user_id"));
                       }
                       return true;
                   }

                }
                catch(SQLException   e){

                }
            }
            return false;
    }

    public boolean userExist(User user){

        try {
            String sql = "SELECT * FROM users JOIN user_type on users.user_type_id = user_type.user_type_id WHERE USERNAME = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getUsername());

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        catch(SQLException   e){

        }
        return false;
    }

    public void removeUser(User user) {
        try{
            String sql = "DELETE FROM users where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, user.getId());

           preparedStatement.execute();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
