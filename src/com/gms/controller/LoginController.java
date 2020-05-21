package com.gms.controller;

import com.gms.dao.UserDao;
import com.gms.model.User;
import com.gms.model.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController{

    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button loginButton;
    @FXML
    Label loginError;
    public void login(ActionEvent actionEvent) throws IOException {
        //clearing the login
        loginError.setText("");
        User user = new User();
        user.setUsername(username.getText());
        user.setPassword(password.getText());
        //First validate the login if user exists.
        UserDao userDao = new UserDao();
        System.out.println(user);
        try {
            userDao.login(user);
            System.out.println(user);
            System.out.println(user.getUser_type());
            if(user.getUser_type() == null){
                loginError.setText("Error! username or password incorrect.");
            }

            //log in member
            else if(user.getUser_type().equals("member")){
                //Sample username: sajitkhadka
                //password: canada

                //A new seesion is created to store the logged in member
                UserSession.getInstance().setSession(user);


                Stage stage = (Stage) loginButton.getScene().getWindow();
                // do what you have to do
                stage.close();

                Parent root = FXMLLoader.load(getClass().getResource("/view/member/member.fxml"));
                Stage memberStage = new Stage();
                memberStage.getIcons().add(new Image("/images/logo.png"));

                memberStage.setScene(new Scene(root, 900, 600));
                memberStage.setResizable(true);

                memberStage.show();
            }


            else if(user.getUser_type().equals("trainer")){
                //trainer code
                //sample username password
                //username: michael
                //password: canada
                UserSession.getInstance().setSession(user);


                Stage stage = (Stage) loginButton.getScene().getWindow();
                // do what you have to do
                stage.close();

                Parent root = FXMLLoader.load(getClass().getResource("/view/trainer/trainer.fxml"));
                Stage memberStage = new Stage();
                memberStage.getIcons().add(new Image("/images/logo.png"));

                memberStage.setScene(new Scene(root, 900, 600));
                memberStage.setResizable(true);

                memberStage.show();
            }



            else if(user.getUser_type().equals("admin")){


                //admin code
                //Prekshya's code to login
                //Sample username password
                //username: prekshya
                //password: canada

                //A new seesion is created to store the logged in member
                UserSession.getInstance().setSession(user);

                Stage stage = (Stage) loginButton.getScene().getWindow();
                // do what you have to do
                stage.close();

                Parent root = FXMLLoader.load(getClass().getResource("/view/admin/admin.fxml"));
                Stage memberStage = new Stage();
                memberStage.getIcons().add(new Image("/images/logo.png"));

                memberStage.setScene(new Scene(root, 900, 600));
                memberStage.setResizable(true);

                memberStage.show();
            }
            else{
                loginError.setText("Something has gone wrong.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void setLoginError(String error){
        loginError.setText(error);
    }
}
