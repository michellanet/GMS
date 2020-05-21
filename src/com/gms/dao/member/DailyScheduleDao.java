package com.gms.dao.member;

import com.gms.dao.Database;
import com.gms.model.member.*;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;

public class DailyScheduleDao {

    Connection connection;
    public DailyScheduleDao(){
        connection = Database.getDatabase().getConn();
    }
    public Gym_Schedule getTodayScheduleByMember(Member member){

        String sql = "SELECT * FROM GYM_DAILY_MEMBER_WORKOUT dmw JOIN DAILY_EXERCIES de ON (dmw.member_id = de.member_id) AND (dmw.day_id = de.day_id) WHERE de.member_id = ? AND COMPLETED_STATUS IS null";
        Gym_Schedule schedule = new Gym_Schedule();
        ObservableList<Workout> listWorkouts = FXCollections.observableArrayList();
        int scheduleContains = 0;
        try{
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             preparedStatement.setInt(1,member.getId());

             ResultSet rs = preparedStatement.executeQuery();

             while(rs.next()){
                 scheduleContains++;
                 schedule.setDay_no(rs.getInt("DAY_ID"));
                 System.out.println(rs.getInt("EXERCISE_CATEGORY_ID"));
                 if(rs.getInt("EXERCISE_CATEGORY_ID") == 1) {
                     Gym_Workout gym_workout = new Gym_Workout();
                     gym_workout.setWorkout_id(rs.getInt("EXERCIES_ID"));
                     gym_workout.setWorkout_type("Gym");


                     String sql2 = "SELECT * FROM GYM_WORKOUT JOIN WORKOUT_TARGET USING(WORKOUT_TARGET_ID) WHERE workout_id = ?";
                     PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
                     preparedStatement1.setInt(1,gym_workout.getWorkout_id());
                     ResultSet rs2 = preparedStatement1.executeQuery();
                     if(rs2.next()){
                         gym_workout.setIdeal_reps(rs2.getInt("IDEAL_REPS"));
                         gym_workout.setIdeal_sets(rs2.getInt("IDEAL_SETS"));
                         gym_workout.setIdeal_weight(rs2.getInt("IDEAL_WEIGHT"));
                         gym_workout.setTarget_muscle(rs2.getString("TARGET"));
                         gym_workout.setWorkout(rs2.getString("WORKOUT"));
                     }
                    listWorkouts.add(gym_workout);
                 }else if(rs.getInt("EXERCISE_CATEGORY_ID") == 2) {
                     Abs_Workout abs_workout = new Abs_Workout();
                     abs_workout.setWorkout_id(rs.getInt("EXERCIES_ID"));
                     abs_workout.setWorkout_type("Abs");


                     String sql2 = " SELECT * FROM ABS_WORKOUT WHERE abs_workout_id = ?";
                     PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
                     preparedStatement1.setInt(1,abs_workout.getWorkout_id());
                     ResultSet rs2 = preparedStatement1.executeQuery();
                     if(rs2.next()){
                         abs_workout.setIdeal_reps(rs2.getInt("IDEAL_REPS"));
                         abs_workout.setIdeal_sets(rs2.getInt("IDEAL_SETS"));
                         abs_workout.setWorkout(rs2.getString("WORKOUT"));
                     }
                     listWorkouts.add(abs_workout);
                 }else if(rs.getInt("EXERCISE_CATEGORY_ID") == 3) {
                     Cardio_Workout cardio_workout = new Cardio_Workout();
                     cardio_workout.setWorkout_id(rs.getInt("EXERCIES_ID"));
                     cardio_workout.setWorkout_type("Cardio");


                     String sql2 = " SELECT * FROM CARDIO_WORKOUT WHERE cardio_workout_id = ?";
                     PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
                     preparedStatement1.setInt(1,cardio_workout.getWorkout_id());
                     ResultSet rs2 = preparedStatement1.executeQuery();
                     if(rs2.next()){
                         cardio_workout.setWorkoutLength(rs2.getInt("WORKOUT_LENGTH"));
                         cardio_workout.setWorkout(rs2.getString("WORKOUT"));

                     }
                     listWorkouts.add(cardio_workout);
                 }
             }
             schedule.setWorkouts(listWorkouts);
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        if(scheduleContains>0){
            return schedule;
        }else{
            return null;
        }

    }

    public ObservableList getLastThreeWorkouts(){
        String sql = "SELECT * FROM GYM_DAILY_MEMBER_WORKOUT dmw \n" +
                "    JOIN DAILY_EXERCIES de ON (dmw.member_id = de.member_id) AND (dmw.day_id = de.day_id) \n" +
                "    JOIN DIFFICULTY USING(DIFFICULTY_ID) \n" +
                "    WHERE dmw.member_id = 1 \n" +
                "    ORDER BY dmw.day_id desc FETCH FIRST 3 ROWS ONLY;";

        return null;
    }

    public ObservableList getCardioWorkOutList(){
        ObservableList<Cardio_Workout> cardio_workouts = FXCollections.observableArrayList();
        String sql = "SELECT * FROM cardio_workout";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                Cardio_Workout cardio_workout = new Cardio_Workout();
                cardio_workout.setWorkout_id(rs.getInt("CARDIO_WORKOUT_ID"));
                cardio_workout.setWorkoutLength(rs.getInt("WORKOUT_LENGTH"));
                cardio_workout.setWorkout(rs.getString("WORKOUT"));
                cardio_workouts.add(cardio_workout);
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return cardio_workouts;
    }

    public ObservableList<Gym_Workout> getGymWorkOutListByTarget(int target){
        ObservableList<Gym_Workout> cardio_workouts = FXCollections.observableArrayList();
        String sql = "SELECT * FROM GYM_WORKOUT JOIN WORKOUT_TARGET using(WORKOUT_TARGET_ID) WHERE WORKOUT_TARGET_ID = '"+target+"'";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                Gym_Workout gym_workout = new Gym_Workout();
                gym_workout.setWorkout_id(rs.getInt("WORKOUT_ID"));
                gym_workout.setWorkout(rs.getString("WORKOUT"));
                gym_workout.setTarget_muscle(rs.getString("TARGET"));
                gym_workout.setIdeal_weight(rs.getInt("IDEAL_WEIGHT"));
                gym_workout.setIdeal_sets(rs.getInt("IDEAL_SETS"));
                gym_workout.setIdeal_reps(rs.getInt("IDEAL_REPS"));
                cardio_workouts.add(gym_workout);
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return cardio_workouts;
    }

    public boolean setSchedule(Workout workout, int day, Member member){
        String sql = "INSERT INTO GYM_DAILY_MEMBER_WORKOUT(Member_id,day_id) VALUES(?,?)";

        int exerciseCategoryid = 0;
        if(workout instanceof Cardio_Workout){
            exerciseCategoryid =  3;
        }else if(workout instanceof Gym_Workout){
            exerciseCategoryid =  1;
        }else if(workout instanceof Abs_Workout){
            exerciseCategoryid =  3;
        }
        boolean existDailyMember = existDailyMemberExercise(member.getId(),day);
        if(!existDailyMember){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,member.getId());
                preparedStatement.setInt(2,day);

                if(preparedStatement.executeUpdate() > 0){



                }
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        String sql2 = "INSERT INTO DAILY_EXERCIES(Member_id,day_id,Exercise_category_Id,Exercies_Id) " +
                "VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
            preparedStatement1.setInt(1, member.getId());
            preparedStatement1.setInt(2, day);
            preparedStatement1.setInt(3, exerciseCategoryid);
            preparedStatement1.setInt(4, workout.getWorkout_id());

            if (preparedStatement1.executeUpdate() > 0) {
                return true;
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    boolean existDailyMemberExercise(int member_id, int day){
        String sql = "SELECT * FROM GYM_DAILY_MEMBER_WORKOUT WHERE MEMBER_ID = ? AND DAY_ID =?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,member_id);
            preparedStatement.setInt(2,day);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return true;
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean setCompletedStatus(int day, Member member) {
        String sql = "UPDATE  GYM_DAILY_MEMBER_WORKOUT SET COMPLETED_STATUS = 1 WHERE MEMBER_ID = ? AND DAY_ID =?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,member.getId());
            preparedStatement.setInt(2,day);


            if(preparedStatement.executeUpdate() > 0){
                return true;
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public int getDay(Member member) {
        String sql = "SELECT MAX(DAY_ID) day FROM gym_daily_member_workout WHERE member_id = '"+member.getId()+"'";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                return rs.getInt("day");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
