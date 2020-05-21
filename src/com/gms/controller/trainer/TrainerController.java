package com.gms.controller.trainer;

import com.gms.model.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TrainerController implements Initializable{
    @FXML
    VBox rootPane;

    @FXML
    HBox logoutBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            VBox pane = FXMLLoader.load(getClass().getResource("/view/trainer/trainerHome.fxml"));
            rootPane.getChildren().setAll(pane);
        }
        catch(IOException e){

        }
    }

    public void homeClicked(MouseEvent mouseEvent) {
        try {

            VBox profilePane = FXMLLoader.load(getClass().getResource("/view/trainer/trainerHome.fxml"));
            rootPane.getChildren().setAll(profilePane);
        }
        catch(IOException e){

        }
    }
    public void profileClicked(MouseEvent mouseEvent) {
        try {
            VBox profilePane = FXMLLoader.load(getClass().getResource("/view/trainer/trainerProfile.fxml"));
            rootPane.getChildren().setAll(profilePane);
        }
        catch(IOException e){

        }
    }

    public void aboutClicked(MouseEvent mouseEvent) {
        try {

            VBox profilePane = FXMLLoader.load(getClass().getResource("/view/about.fxml"));
            rootPane.getChildren().setAll(profilePane);
        }
        catch(IOException e){

        }
    }

    public void payStubHubClicked(MouseEvent mouseEvent) {
        try {

            VBox profilePane = FXMLLoader.load(getClass().getResource("/view/trainer/payStubSummary.fxml"));
            rootPane.getChildren().setAll(profilePane);
        }
        catch(IOException e){

        }
    }

    public void defineRoutineClicked(MouseEvent mouseEvent) {
        try {

            VBox profilePane = FXMLLoader.load(getClass().getResource("/view/trainer/defineRoutine.fxml"));
            rootPane.getChildren().setAll(profilePane);
        }
        catch(IOException e){

        }
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) logoutBox.getScene().getWindow();
        // do what you have to do
        stage.close();

        //clear the session
        UserSession.getInstance().clearSession();

        Parent  root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        Stage loginStage = new Stage();
        loginStage.getIcons().add(new Image("/images/logo.png"));

        loginStage.setScene(new Scene(root, 421, 380));
        loginStage.setResizable(false);

        loginStage.show();
    }
}
