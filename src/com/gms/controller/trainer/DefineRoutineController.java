package com.gms.controller.trainer;

import com.gms.dao.trainer.TrainerDao;
import com.gms.model.UserSession;
import com.gms.model.trainer.Trainer;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DefineRoutineController {

    @FXML
    private VBox homePane;

    @FXML
    private ComboBox<?> category;

    @FXML
    private TextField workoutName;

    @FXML
    private HBox gym;

    @FXML
    private TextField sets;

    @FXML
    private TextField reps;

    @FXML
    private HBox cardio;

    @FXML
    private TextField timer;

    @FXML
    public void initialize(){
        TrainerDao trainerDao = new TrainerDao();
        //get current session;
        UserSession session = UserSession.getInstance();
        System.out.println(session.getSession().getId());

        Trainer trainer = trainerDao.getTrainerByUserId(session.getSession().getId());
        System.out.println(trainer);



        /*fName.setText(trainer.getFirstName());
        fullName.setText(trainer.getFirstName() + " " + trainer.getLastName());
        gender.setText(trainer.getGender());
        email.setText(trainer.getEmail());
        phone.setText("" + trainer.getPhone());
        username.setText(trainer.getUsername());
        trainerID.setText(trainer.getTrainerID());
        status.setText(trainer.getStatus());
        paymentType.setText(trainer.getPaymentType());
        experience.getItems().add(trainer.getExperience());

         */
    }

}
