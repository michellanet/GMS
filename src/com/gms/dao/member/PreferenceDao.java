package com.gms.dao.member;

import com.gms.dao.Database;
import com.gms.model.member.BodyType;
import com.gms.model.member.Member;
import com.gms.model.member.Preference;
import com.gms.model.member.TrainingPackage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Toggle;

import java.sql.*;

public class PreferenceDao {

    Connection connection;

    public PreferenceDao(){
        connection = Database.getDatabase().getConn();
    }

    public Preference getPreferenceByMemberId(int memberId){
        String sql = "SELECT * FROM MEMBER_PREFERENCE JOIN SHIFT USING(SHIFT_ID) JOIN BODY_TYPE USING(IDEAL_BODY_TYPE_ID) WHERE MEMBER_ID = ?";
        PreparedStatement preparedStatement = null;
        Preference preference = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,memberId);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                BodyType bodyType = new BodyType(rs.getInt("IDEAL_BODY_TYPE_ID"),rs.getString("BODY_TYPE"));
                preference = new Preference(rs.getString("SHIFT"),bodyType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preference;
    }


    public ObservableList<BodyType> getAllBodyType(){
        ObservableList<BodyType> bodyTypes = FXCollections.observableArrayList();

        String sql = "SELECT * FROM BODY_TYPE";
        PreparedStatement preparedStatement = null;
        Preference preference = null;
        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                BodyType bodyType = new BodyType(rs.getInt("IDEAL_BODY_TYPE_ID"),rs.getString("BODY_TYPE"));
                bodyTypes.add(bodyType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bodyTypes;
    }

    public boolean addPreference(Member member, String shift, BodyType preferredBoyType) {

        int shift_id = 1;
        if(shift.equals("Morning")){
            shift_id = 1;
        }else if(shift.equals("Day")){
            shift_id = 2;
        }else if(shift.equals("Evening")){
            shift_id = 3;
        }

        String sql = "INSERT INTO MEMBER_PREFERENCE(MEMBER_ID, SHIFT_ID,IDEAL_BODY_TYPE_ID) VALUES(?,?,?)";
        PreparedStatement preparedStatement = null;
        Preference preference = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,member.getId());
            preparedStatement.setInt(2,shift_id);
            preparedStatement.setInt(3,preferredBoyType.getId());

            if(preparedStatement.executeUpdate() > 0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updatePreference(Member member, String shift, BodyType preferredBoyType) {

        int shift_id = 1;
        if(shift.equals("Morning")){
            shift_id = 1;
        }else if(shift.equals("Day")){
            shift_id = 2;
        }else if(shift.equals("Evening")){
            shift_id = 3;
        }

        String sql = "UPDATE MEMBER_PREFERENCE SET SHIFT_ID=? ,IDEAL_BODY_TYPE_ID=? WHERE MEMBER_ID = ?";
        PreparedStatement preparedStatement = null;
        Preference preference = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,shift_id);
            preparedStatement.setInt(2,preferredBoyType.getId());
            preparedStatement.setInt(3,member.getId());

            if(preparedStatement.executeUpdate() > 0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
