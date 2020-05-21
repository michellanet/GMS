package com.gms.controller.member;

import com.gms.dao.member.DailyScheduleDao;
import com.gms.dao.member.DifficultyDao;
import com.gms.dao.member.MemberLevelDao;
import com.gms.model.member.Difficulty;
import com.gms.model.member.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;

public class ExerciseFeedBackController {
    @FXML VBox feedBackPane;
    @FXML
    ComboBox<Difficulty> comboDifficulty;
    private Member member;
    private int day;


    DailyScheduleDao scheduleDao;
    DifficultyDao difficultyDao;
    MemberLevelDao memberLevelDao;

    @FXML
    private void initialize(){

        scheduleDao = new DailyScheduleDao();
        difficultyDao = new DifficultyDao();
        memberLevelDao = new MemberLevelDao();
        comboDifficulty.setItems(difficultyDao.getAllDifficulty());

    }

    public void completeWorkout(ActionEvent event) {

        Difficulty difficulty = comboDifficulty.getValue();
        int level = member.getLevel();

        if(scheduleDao.setCompletedStatus(day, member)) {
            switch(difficulty.getId()){
                case 1: level += 5;
                break;
                case 2: level += 3;
                    break;
                case 3: level += 1;
                    break;

                case 5: level += -2;
                    break;
                    case 6: level += -4;
                    break;
                default: level += 0;
            }
            memberLevelDao.updateLevel(member,level);
            VBox rootPane = (VBox) feedBackPane.getParent();
            try {

                VBox editProfilePane = FXMLLoader.load(getClass().getResource("/view/member/memberHome.fxml"));
                rootPane.getChildren().setAll(editProfilePane);
            }
            catch(IOException e){

            }
        }

    }

    public void setValues(Member member,int day){
        this.member = member;
        this.day    = day;
    }
}
