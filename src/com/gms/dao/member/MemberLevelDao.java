package com.gms.dao.member;

import com.gms.dao.Database;
import com.gms.model.member.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberLevelDao {

    Connection connection;
    public MemberLevelDao(){
        connection = Database.getDatabase().getConn();
    }

    public boolean updateLevel(Member member, int level){
        String sql = "UPDATE member SET MEMBER_LEVEL = ?  WHERE MEMBER_ID =?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,level);
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
