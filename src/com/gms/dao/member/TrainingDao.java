package com.gms.dao.member;

import com.gms.dao.Database;
import com.gms.model.member.Member;
import com.gms.model.member.Training;
import com.gms.model.member.TrainingPackage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class TrainingDao {
    Connection connection;

    public TrainingDao(){
        connection = Database.getDatabase().getConn();
    }
    public TrainingPackage getTrainingPackageByMember(Member member, String packageName){
        String sql = "SELECT * FROM TRAINING_PACKAGE JOIN PACKAGE USING(PACKAGE_ID) WHERE MEMBER_ID = ? AND PACKAGE=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,member.getId());
            preparedStatement.setString(2,packageName);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                return new TrainingPackage(rs.getInt("PACKAGE_ID"),rs.getString("PACKAGE"),rs.getDouble("PRICE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public ObservableList<TrainingPackage> getPackageListByMemberId(int memberId){
        ObservableList<TrainingPackage> packages = FXCollections.observableArrayList();
        String sql = "SELECT * FROM TRAINING_PACKAGE JOIN PACKAGE USING(PACKAGE_ID) WHERE MEMBER_ID = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,memberId);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                    packages.add(new TrainingPackage(rs.getInt("PACKAGE_ID"),rs.getString("PACKAGE"),rs.getDouble("PRICE")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return packages;
    }


    public boolean existTrainingPackageForMember(int trainingpackage_id, Member member){
        String sql = "SELECT * FROM TRAINING_PACKAGE WHERE MEMBER_ID = ? AND PACKAGE_ID =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,member.getId());
            preparedStatement.setInt(2,trainingpackage_id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean AddOrremoveTrainingPackageForMember(int packageId, Member member, boolean add) {

        String sql;
            //
        if (add) {
            if(existTrainingPackageForMember(packageId,member)) return false;
              sql = "INSERT INTO TRAINING_PACKAGE VALUES(?,?)";
        } else {
             sql = "DELETE FROM TRAINING_PACKAGE WHERE MEMBER_ID = ? AND PACKAGE_ID =?";

        }
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,member.getId());
            statement.setInt(2,packageId);
            if(statement.executeUpdate()>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
