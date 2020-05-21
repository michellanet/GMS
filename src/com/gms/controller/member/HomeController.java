package com.gms.controller.member;

import com.gms.dao.member.DailyScheduleDao;
import com.gms.dao.member.MemberDao;
import com.gms.dao.member.PreferenceDao;
import com.gms.model.UserSession;
import com.gms.model.member.*;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.swing.plaf.basic.BasicButtonUI;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.Guard;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class HomeController {

    @FXML VBox homePane;
    @FXML VBox exerciseList;

    @FXML Label level;
    @FXML Label dayLabel;
    @FXML
    Button buttonBegin;

    PreferenceDao preferenceDao;
    MemberDao memberDao;

    Member member;

    DailyScheduleDao scheduleDao;
    Random rand = new Random();

    int day=0;
    @FXML
    public void initialize() {
        scheduleDao = new DailyScheduleDao();
        preferenceDao = new PreferenceDao();
        //get what type of workout the user needs
        memberDao = new MemberDao();

        member = memberDao.getMemberByUserId(UserSession.getInstance().getSession().getId());

        Preference preference = preferenceDao.getPreferenceByMemberId(member.getId());
        System.out.println(preference);
        if (preference == null) {
            buttonBegin.setDisable(true);

            exerciseList.getChildren().add( createHbox("Please Set the preferred body type in settings menu to generate exercises"));
        } else {

            String preferredBodyType = preference.getIdeal_body().getBodyType();


            int mylevel = member.getLevel();
            if (mylevel < 10) {
                level.setText("Beginner (Level: " + (mylevel) + ")");
            } else if (mylevel < 20) {
                level.setText("Amateur (Level: " + (mylevel - 10) + ")");
            } else if (mylevel < 30) {
                level.setText("Semi-Pro (Level: " + (mylevel - 20) + ")");
            } else if (mylevel < 40) {
                level.setText("Professional (Level: " + (mylevel - 30) + ")");
            } else if (mylevel < 50) {
                level.setText("World Class (Level: " + (mylevel - 40) + ")");
            } else {
                level.setText("God (Level: " + (mylevel - 50) + ")");
            }

            ObservableList<Gym_Schedule> gym_schedules = scheduleDao.getLastThreeWorkouts();
            Gym_Schedule today_schedule = scheduleDao.getTodayScheduleByMember(member);

            if (gym_schedules != null) day = gym_schedules.get(1).getDay_no();

            //System.out.println(day);
            if (today_schedule != null) day = today_schedule.getDay_no();

            if (today_schedule == null) {
                day = scheduleDao.getDay(member);
                day++;
                if (preferredBodyType.equals("Slim")) {
                    ObservableList<Cardio_Workout> cardio_workouts;
                    cardio_workouts = scheduleDao.getCardioWorkOutList();


                    //choose 4 random workouts
                    int numberOfElements = 4;
                    System.out.println(cardio_workouts.size());

                    ObservableList<Cardio_Workout> choosenWorkouts = FXCollections.observableArrayList();
                    for (int i = 0; i < numberOfElements; i++) {
                        int randomIndex = rand.nextInt(cardio_workouts.size());
                        choosenWorkouts.add(cardio_workouts.get(randomIndex));
                        cardio_workouts.remove(randomIndex);

                    }

                    for (Cardio_Workout cardio_workout : choosenWorkouts) {
                        scheduleDao.setSchedule(cardio_workout, day, member);
                    }
                }
                if (preferredBodyType.equals("Muscular")) {

                    ObservableList<Gym_Workout> gym_workouts;

                    //generate random schedule by generating random target instead of previous targets
                    gym_workouts = scheduleDao.getGymWorkOutListByTarget(rand.nextInt(6));


                    //choose 4 random workouts
                    int numberOfElements = 3;


                    ObservableList<Gym_Workout> choosenWorkouts = FXCollections.observableArrayList();

                    System.out.println(numberOfElements);
                    for (int i = 0; i < numberOfElements; i++) {
                        int randomIndex = rand.nextInt(gym_workouts.size());
                        System.out.println(gym_workouts.get(randomIndex));
                        choosenWorkouts.add(gym_workouts.get(randomIndex));
                        gym_workouts.remove(randomIndex);

                    }


                    for (Gym_Workout gym_workout : choosenWorkouts) {
                        System.out.println(gym_workout);
                        scheduleDao.setSchedule(gym_workout, day, member);
                    }
                }


                today_schedule = scheduleDao.getTodayScheduleByMember(member);

            }
            if (today_schedule != null) {
                //set day no
                dayLabel.setText("Day: " + day);
                ObservableList<Workout> workouts = today_schedule.getWorkouts();
                for (Workout workout : workouts) {
                    System.out.println(workout.toString());
                    exerciseList.getChildren().add(createHbox(workout.toString()));
                }
            }


            //Payment


            //set next payment date

            //first check if the next pay date is null
            if (member.getNextpaydate() == null && member.getAccountcreated() != null) {
                System.out.println("hello");
                Date currentTime = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                //long diffTimeStamp = currentTime.getTime()-member.getAccountcreated().getTime();
                //System.out.println(member.getAccountcreated().getTime());
                //long diffDays = diffTimeStamp / (24 * 60 * 60 * 1000);

                Calendar c = Calendar.getInstance();
                c.setTime(member.getAccountcreated());
                c.add(Calendar.DATE, 30);

                Date nextDate = new java.sql.Date(c.getTime().getTime());
                System.out.println(nextDate.getTime());
                if (memberDao.setNextDueDate(member, nextDate)) {
                    System.out.println("successfully updated");
                } else {
                    System.out.println("couldn't update next schedule");
                }

            } else if (member.getNextpaydate() != null) {
                Calendar c = Calendar.getInstance();
                Date currentTime = new java.sql.Date(c.getTime().getTime());
                long diffTimeStamp = member.getNextpaydate().getTime() - currentTime.getTime();
                //System.out.println(member.getAccountcreated().getTime());
                long diffDays = diffTimeStamp / (24 * 60 * 60 * 1000);


                c.setTime(member.getNextpaydate());
                c.add(Calendar.DATE, 30);
                Date nextDate = new java.sql.Date(c.getTime().getTime());
                if (diffDays <= 0) {
                    //set next due date
                    memberDao.setNextDueDate(member, nextDate);
                }
            }
            //System.out.println(member.getNextpaydate());

        }
    }

    public HBox createHbox(String text){
        HBox exercisebox = new HBox();
        exercisebox.setPrefHeight(44);

        Label exerciseText = new Label();
        exerciseText.setAlignment(Pos.CENTER);
        exerciseText.setPrefHeight(44);
        exerciseText.setPrefWidth(380);
        exerciseText.setTextFill(Color.web("#d5dbd6"));
        exerciseText.setText("✪  " + text);

        Font font = new Font("Arial Rounded MT Bold",15);
        exerciseText.setFont(font);

        exercisebox.getChildren().setAll(exerciseText);

        return exercisebox;
    }


    public void beginExercise(ActionEvent event) {
        //For now i would not put the feedback system.
//        VBox rootPane = (VBox) homePane.getParent();
//        try {
//
//            VBox feedBackpane = FXMLLoader.load(getClass().getResource("/view/admin/exerciseFeedBack.fxml"));
//            rootPane.getChildren().setAll(feedBackpane);
//        }
//        catch(IOException e){
//
//        }

        //change completed status
        //System.out.println("day" + day);
        //if(scheduleDao.setCompletedStatus(day, member)){
            VBox rootPane = (VBox) homePane.getParent();
            try {

                FXMLLoader homeFxml = new FXMLLoader(getClass().getResource("/view/member/exerciseFeedBack.fxml"));
                VBox homepane2 = homeFxml.load();
                ExerciseFeedBackController feedBackController = homeFxml.getController();
                feedBackController.setValues(member,day);
                rootPane.getChildren().setAll(homepane2);


            }
            catch(IOException e){

            }
        //}
    }


}
