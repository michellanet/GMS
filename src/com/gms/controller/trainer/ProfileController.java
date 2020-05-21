package com.gms.controller.trainer;

import com.gms.dao.trainer.TrainerDao;
import com.gms.model.UserSession;
import com.gms.model.trainer.Trainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javax.swing.*;

public class ProfileController {

    @FXML
    Label fName;

    @FXML
    Label fullName;

    @FXML
    Label trainerID;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextArea experience;

    @FXML
    private Button update;

    @FXML
    public void initialize(){
        TrainerDao trainerDao = new TrainerDao();
        //get current session;
        UserSession session = UserSession.getInstance();
        System.out.println(session.getSession().getId());


        Trainer trainer = trainerDao.getTrainerByUserId(session.getSession().getId());
        System.out.println(trainer);



        fName.setText("Welcome, " + trainer.getFname());
        fullName.setText(trainer.getFname() + " " + trainer.getLname());
        email.setText(trainer.getEmail());
        phone.setText("" + trainer.getPhone());
        trainerID.setText("" + ("TR" + trainer.getTrainer_id()));
        experience.setText(trainer.getExperience());


    }


    public void updateClicked(ActionEvent event) {

        if(update.getText().equals("Update")) { //Toggling on update text rather than checking editable is easier and efficiend
            // way
            phone.requestFocus(); //This is to put focus on editable text
            phone.setEditable(true);
            email.setEditable(true);
            experience.setEditable(true);
            update.setText("Save");
        }

        else{
            phone.setEditable(false);
            email.setEditable(false);
            experience.setEditable(false);
            update.setText("Update");

            TrainerDao trainerDao = new TrainerDao();
            //get current session;
            UserSession session = UserSession.getInstance();
            System.out.println(session.getSession().getId());

            Trainer trainer = trainerDao.getTrainerByUserId(session.getSession().getId());
            System.out.println(trainer);

            trainer.setPhone(phone.getText());
            trainer.setEmail(email.getText());
            trainer.setExperience(experience.getText());

            trainerDao.updateTrainer(trainer);

            JOptionPane.showMessageDialog(null, "Profile Updated");
        }


    }
}
