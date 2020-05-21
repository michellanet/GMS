package com.gms.dao.trainer;

import com.gms.dao.Database;
import com.gms.model.member.Member;
import com.gms.model.trainer.Trainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class TrainerDao {
    Connection connection;

    public TrainerDao(){
        connection = Database.getDatabase().getConn();
    }

    public Trainer getTrainerByUserId(int id){
        String sql = "SELECT * FROM trainers WHERE USER_ID = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            ResultSet rs = preparedStatement.executeQuery();
            Trainer trainer = new Trainer();
            if(rs.next()){
                setTrainer(trainer,rs);

                return trainer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Trainer getTrainerByUserName(String username){

        String sql = "SELECT * FROM trainers JOIN users using (user_id) WHERE username = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);

            ResultSet rs = preparedStatement.executeQuery();
            Trainer trainer = new Trainer();
            if(rs.next()){
                setTrainer(trainer,rs);

                return trainer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ObservableList<Trainer> getTrainers(){
        ObservableList<Trainer> trainers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM trainers";
        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                Trainer trainer = new Trainer();
                setTrainer(trainer,rs);
                System.out.println(trainer);
                trainers.add(trainer);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return trainers;
    }

    public boolean updateTrainer(Trainer trainer){
        String sql = "UPDATE trainers SET PHONE =?, EMAIL = ?, EXPERIENCE=? WHERE TRAINER_ID =?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,trainer.getPhone());
            preparedStatement.setString(2,trainer.getEmail());
            preparedStatement.setString(3,trainer.getExperience());
            preparedStatement.setInt(4,trainer.getTrainer_id());


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


    //get member list for a specific trainer
    public ObservableList<Member> getTrainingMembers(Trainer trainer){
        String sql  ="SELECT * FROM MEMBER_TRAINER JOIN MEMBER USING(MEMBER_ID) JOIN TRAINERS USING(TRAINER_ID) WHERE trainer_id = 1";
        ObservableList<Member> members = FXCollections.observableArrayList();

        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                Member member = new Member();
                member.setUser_id(rs.getInt("USER_ID"));
                member.setId(rs.getInt("MEMBER_ID"));
                member.setFname(rs.getString("FNAME"));
                member.setLname(rs.getString("LNAME"));
                member.setContact(rs.getString("MOBILE"));
                member.setEmail(rs.getString("EMAIL"));

                member.setEcontactno(rs.getString("EMERGENCY_CONTACT_NO"));
                members.add(member);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return members;
    }

    private void setTrainer(Trainer trainer, ResultSet rs) throws SQLException {
        trainer.setTrainer_id(rs.getInt("TRAINER_ID"));
        trainer.setFname(rs.getString("FIRSTNAME"));
        trainer.setLname(rs.getString("LASTNAME"));
        trainer.setEmail(rs.getString("EMAIL"));
        trainer.setPhone(rs.getString("PHONE"));
        trainer.setExperience(rs.getString("EXPERIENCE"));
        /*
        trainer.setStatus(rs.getString("STATUS"));
        trainer.setPaymentType(rs.getString("PAYMENT_TYPE"));
        trainer.setGender(rs.getString("GENDER"));
        trainer.setScheduleString(rs.getString("SCHEDULE_DATE"));
        trainer.setHours(rs.getInt("HOURS"));
        trainer.setSalary(rs.getDouble("SALARY"));
        trainer.setUsername(rs.getString("USERNAME"));
        trainer.setPayDate(rs.getString("PAYDATE"));


         */
    }

    //get selected members under specific trainer by fname or lname
    public ObservableList<Member> getTrainingMembers(Trainer trainer, String name){
        String sql  ="SELECT * FROM MEMBER_TRAINER JOIN MEMBER USING(MEMBER_ID) JOIN TRAINERS USING(TRAINER_ID) WHERE (trainer_id = 1) AND (fname = '" + name + "' OR lname = '"+ name +"')";
        ObservableList<Member> members = FXCollections.observableArrayList();

        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                Member member = new Member();
                member.setUser_id(rs.getInt("USER_ID"));
                member.setId(rs.getInt("MEMBER_ID"));
                member.setFname(rs.getString("FNAME"));
                member.setLname(rs.getString("LNAME"));
                member.setContact(rs.getString("MOBILE"));
                member.setEmail(rs.getString("EMAIL"));

                member.setEcontactno(rs.getString("EMERGENCY_CONTACT_NO"));
                members.add(member);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return members;
    }

}
