package com.gms.controller.trainer;

import com.gms.dao.trainer.TrainerDao;
import com.gms.model.UserSession;
import com.gms.model.trainer.Trainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class PaymentController {

    @FXML
    private VBox settingPane;

    @FXML
    private Button showPreviousClick;

    @FXML
    private VBox paymentHistoryContainer;

    @FXML
    Label status;

    @FXML
    Label payDate;
    @FXML
    Label hours;

    @FXML
    void previousPaymentsClicked(ActionEvent event) {
        if(paymentHistoryContainer.isVisible()) {
            showPreviousClick.setText("Show previous Payments");
            paymentHistoryContainer.setVisible(false);
        }
        else {
            showPreviousClick.setText("Hide previous Payments");
            paymentHistoryContainer.setVisible(true);
        }
    }

    /*@FXML
    public void initialize(){
        TrainerDao trainerDao = new TrainerDao();
        //get current session;
        UserSession session = UserSession.getInstance();
        System.out.println(session.getSession().getId());
        Trainer trainer = trainerDao.getTrainerByUserId(session.getSession().getId());

        status.setText(trainer.getStatus());
        payDate.setText(trainer.getPayDate());
        hours.setText("" + trainer.getHours());

    }*/

}
