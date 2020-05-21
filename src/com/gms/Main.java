package com.gms;

import com.gms.controller.LoginController;
import com.gms.dao.Database;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        //Initialize the database connection


        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
            root =  fxmlLoader.load();
            LoginController loginController = fxmlLoader.getController();
            //root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            //

            try {
                Database.getDatabase().connect();
            } catch (SQLException e) {
                loginController.setLoginError(e.getMessage());
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        primaryStage.getIcons().add(new Image("/images/logo.png"));

        primaryStage.setScene(new Scene(root, 421, 380));
        primaryStage.setResizable(false);

        primaryStage.show();

    }

    @Override
    public void stop() {
        //stop the database connection.
        Database.getDatabase().disconnect();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
