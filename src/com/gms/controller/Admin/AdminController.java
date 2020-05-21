package com.gms.controller.Admin;

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

public class AdminController implements Initializable{
    @FXML
    VBox rootPane;

    @FXML
    HBox logoutBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            VBox pane = FXMLLoader.load(getClass().getResource("/view/admin/adminHome.fxml"));
            rootPane.getChildren().setAll(pane);
        }
        catch(IOException e){

        }
    }

    public void homeClicked(MouseEvent mouseEvent) {
        try {

            VBox profilePane = FXMLLoader.load(getClass().getResource("/view/admin/adminHome.fxml"));
            rootPane.getChildren().setAll(profilePane);
        }
        catch(IOException e){

        }
    }
    public void profileClicked(MouseEvent mouseEvent) {
        try {

            VBox profilePane = FXMLLoader.load(getClass().getResource("/view/admin/adminProfile.fxml"));
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



    public void memberListClicked(MouseEvent mouseEvent) {
        try {

            VBox profilePane = FXMLLoader.load(getClass().getResource("/view/admin/memberList.fxml"));
            rootPane.getChildren().setAll(profilePane);
        }
        catch(IOException e){

        }
    }

    public void trainerListClicked(MouseEvent mouseEvent) {
        try {

            VBox profilePane = FXMLLoader.load(getClass().getResource("/view/admin/trainerList.fxml"));
            rootPane.getChildren().setAll(profilePane);
        }
        catch(IOException e){

        }
    }

    public void adminListClicked(MouseEvent mouseEvent) {
        try {

            VBox profilePane = FXMLLoader.load(getClass().getResource("/view/admin/adminList.fxml"));
            rootPane.getChildren().setAll(profilePane);
        }
        catch(IOException e){

        }
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) logoutBox.getScene().getWindow();
        // do what you have to do
        stage.close();

        UserSession.getInstance().clearSession();

        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        Stage loginStage = new Stage();
        loginStage.getIcons().add(new Image("/images/logo.png"));

        loginStage.setScene(new Scene(root, 421, 380));
        loginStage.setResizable(false);

        loginStage.show();
    }



}
