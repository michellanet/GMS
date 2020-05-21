package com.gms.controller.Admin;

import com.gms.dao.UserDao;
import com.gms.dao.admin.AdminDao;
import com.gms.dao.member.MemberDao;
import com.gms.dao.member.TrainerDao;
import com.gms.model.User;
import com.gms.model.admin.Admin;
import com.gms.model.member.Member;
import com.gms.model.trainer.Trainer;
import com.gms.model.trainer.TrainerType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AddTrainerController {

    @FXML TextField textfname;
    @FXML TextField textlname;
    @FXML TextField textusername;
    @FXML TextField textpassword;
    @FXML TextField textrpassword;
    @FXML Label createAccountError;
    @FXML VBox addTrainer;

    @FXML ComboBox<TrainerType>comboTrainerType;

    //DAO initialization
    UserDao dao;
    TrainerDao tdao;


    @FXML
    public void initialize(){
        dao = new UserDao();
        tdao = new TrainerDao();

        //binding observable list into the combo box
        comboTrainerType.setItems(tdao.getAllTrainerType());
    }

    public void createAccount(ActionEvent actionEvent) {

        User user = new User("Trainer",textusername.getText(),textpassword.getText());



        if(!dao.userExist(user)){
            if(dao.addNewUser(user)){
                Trainer trainer = new Trainer();
                trainer.setUser_id(user.getId());
                trainer.setFname(textfname.getText());
                trainer.setLname(textlname.getText());


                //retrieving selected trainer from gui
                TrainerType trainerType = comboTrainerType.getValue();

                //setting the trainer string of Trainer class to trainer of trainer type class
                trainer.setTrainer_type(trainerType.getType());
                if(tdao.addNewTrainer(trainer)) {



                    //gets the wrapper parent component of current component
                    try {
                        //gets the wrapper parent component of current component
                        VBox rootPane = (VBox) addTrainer.getParent();

                        VBox trainerList = FXMLLoader.load(getClass().getResource("/view/admin/trainerList.fxml"));
                        rootPane.getChildren().setAll(trainerList);
                    } catch (IOException e) {

                    }
                }
                else{
                    //cannot create the user
                    System.out.println(user);
                    createAccountError.setText("Couldn't add new Trainer.");
                    dao.removeUser(user);
                    //remove user as well.
                }
            }else{
                createAccountError.setText("Error adding new Trainer.");
            }
        }else{

            createAccountError.setText("Trainer already exist");
        }
    }
}


