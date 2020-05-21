package com.gms.dao.member;

import com.gms.dao.Database;
import com.gms.model.member.Instructor_Training;
import com.gms.model.member.Member;
import com.gms.model.trainer.Trainer;
import com.gms.model.trainer.TrainerType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

import java.sql.*;

public class TrainerDao {
    Connection connection;

    public TrainerDao(){
        connection = Database.getDatabase().getConn();
    }

    //Adding a trainer (code by admin)
    public boolean addNewTrainer(Trainer trainer){
        int trainer_type = 0;
        if(trainer.getTrainer_type().equals("gym")){
            trainer_type = 1;
        }else if(trainer.getTrainer_type().equals("swimming")){
            trainer_type = 2;
        }
        System.out.println(trainer);
        String sql = "INSERT INTO trainers(USER_ID,FIRSTNAME,LASTNAME,TRAINER_TYPE_ID) VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,trainer.getUser_id());
            preparedStatement.setString(2,trainer.getFname());
            preparedStatement.setString(3,trainer.getLname());
            preparedStatement.setInt(4,trainer_type);


            if(preparedStatement.executeUpdate() > 0){
                System.out.println("Successfully added");
                return true;

            }else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public  Instructor_Training getMemberInstructor(int memberId, String instructorType){
        //ObservableList<Instructor_Training> instructor_trainings = FXCollections.observableArrayList();
        String sql= "SELECT * FROM TRAINERS JOIN TRAINER_TYPE USING(TRAINER_TYPE_ID) JOIN MEMBER_TRAINER USING(TRAINER_ID) WHERE MEMBER_TRAINER.MEMBER_ID = ? AND TRAINER_TYPE = ?";
        PreparedStatement preparedStatement = null;
        Instructor_Training training = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,memberId);
            preparedStatement.setString(2,instructorType);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                Trainer trainer = new Trainer(rs.getInt("TRAINER_ID"),rs.getString("FIRSTNAME"),rs.getString("LASTNAME"),rs.getString("EXPERIENCE"),rs.getString("EMAIL"),rs.getString("PHONE"),rs.getString("TRAINER_TYPE"));
                training = new Instructor_Training(trainer, rs.getDate("TRAINING_BEGIN_DATE"));
                return training;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return training;
    }



    public ObservableList<Trainer> getAllTrainer(String trainer_type){

        ObservableList<Trainer> trainers = FXCollections.observableArrayList();

        String sql = "SELECT * FROM TRAINERS JOIN TRAINER_TYPE USING(TRAINER_TYPE_ID) WHERE TRAINER_TYPE = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,trainer_type);
            ResultSet rs= statement.executeQuery();
            while(rs.next()){
                Trainer trainer = new Trainer(rs.getInt("TRAINER_ID"),rs.getString("FIRSTNAME"),rs.getString("LASTNAME"),rs.getString("EXPERIENCE"),rs.getString("EMAIL"),rs.getString("PHONE"),rs.getString("TRAINER_TYPE"));
                trainers.add(trainer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainers;
    }

    //Subscribe to trainer by member
    public boolean subscribeTrainer(Trainer trainer, Member member){

        String sql = "INSERT INTO MEMBER_TRAINER(MEMBER_ID,TRAINER_ID,TRAINING_BEGIN_DATE) VALUES(?,?,TO_DATE(TO_CHAR(sysdate,'yyyy mm dd'),'YYYY/MM/DD'))";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,member.getId());
            preparedStatement.setInt(2,trainer.getTrainer_id());

            if(preparedStatement.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existSubscribedTrainer(Trainer trainer, Member member){
        String sql = "SELECT * FROM MEMBER_TRAINER WHERE MEMBER_ID =? AND TRAINER_ID =?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,member.getId());
            preparedStatement.setInt(2,trainer.getTrainer_id());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTrainer(Trainer myGymTrainer, Trainer newGymtrainer, Member member) {
        String sql = "UPDATE  MEMBER_TRAINER SET TRAINER_ID =?, TRAINING_BEGIN_DATE= TO_DATE(TO_CHAR(sysdate,'yyyy mm dd'),'YYYY/MM/DD') WHERE TRAINER_ID =? AND MEMBER_ID =?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,newGymtrainer.getTrainer_id());
            preparedStatement.setInt(2,myGymTrainer.getTrainer_id());
            preparedStatement.setInt(3,member.getId());

            if(preparedStatement.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeSubscription(Member member, Trainer myGymTrainer) {
        String sql = "DELETE FROM MEMBER_TRAINER WHERE MEMBER_ID =? AND TRAINER_ID =?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,member.getId());
            preparedStatement.setInt(2,myGymTrainer.getTrainer_id());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //get all list of trainers regardless of type
    public ObservableList<Trainer> getAllTrainers(){

        ObservableList<Trainer> trainers = FXCollections.observableArrayList();

        String sql = "SELECT * FROM TRAINERS JOIN TRAINER_TYPE USING(TRAINER_TYPE_ID)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs= statement.executeQuery();
            while(rs.next()){
                Trainer trainer = new Trainer(rs.getInt("TRAINER_ID"),rs.getString("FIRSTNAME"),rs.getString("LASTNAME"),rs.getString("EXPERIENCE"),rs.getString("EMAIL"),rs.getString("PHONE"),rs.getString("TRAINER_TYPE"));
                trainers.add(trainer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainers;
    }

    //Getting trainers type from database
    public ObservableList<TrainerType> getAllTrainerType(){
        ObservableList<TrainerType> trainerTypes = FXCollections.observableArrayList();

        String sql = "SELECT * FROM TRAINER_TYPE";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs= statement.executeQuery();
            while(rs.next()){

                TrainerType trainerType = new TrainerType(rs.getInt("TRAINER_TYPE_ID"),rs.getString("TRAINER_TYPE"));
                trainerTypes.add(trainerType);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainerTypes;


    }
}