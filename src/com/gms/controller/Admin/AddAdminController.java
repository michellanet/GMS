package com.gms.controller.Admin;

import com.gms.dao.UserDao;
import com.gms.dao.admin.AdminDao;
import com.gms.dao.member.MemberDao;
import com.gms.model.User;
import com.gms.model.UserSession;
import com.gms.model.admin.Admin;
import com.gms.model.member.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AddAdminController {
    @FXML
    TextField textfname;
    @FXML TextField textlname;
    @FXML TextField textusername;
    @FXML TextField textpassword;
    @FXML TextField textrpassword;
    @FXML
    Label labelInfo;

    @FXML VBox addAdmin;

    @FXML Label createAccountError;

    @FXML TextField textMobile;

    @FXML TextField textEmail;

    //DAO initialization
    UserDao dao;
    AdminDao adao;
    @FXML
    public void initialize(){
        dao = new UserDao();
        adao = new AdminDao();
    }

    public void createAccount(ActionEvent actionEvent) {
        User user = new User("Admin",textusername.getText(),textpassword.getText());
        Admin admin = new Admin();

        if(!dao.userExist(user)){
            if(dao.addNewUser(user)){

                admin.setUser_id(user.getId());
                admin.setContact(textMobile.getText());
                admin.setEmail(textEmail.getText());
                admin.setFname(textfname.getText());
                admin.setLname(textlname.getText());

                if(adao.addNewAdmin(admin)){


                    //gets the wrapper parent component of current component
                    VBox rootPane = (VBox) addAdmin.getParent();
                    try {

                        VBox editProfilePane = FXMLLoader.load(getClass().getResource("/view/admin/adminList.fxml"));

                        rootPane.getChildren().setAll(editProfilePane);
                    }
                    catch(IOException e){

                    }


                }else{
                    createAccountError.setText("Error adding new admin.");
                }
            }else{
                createAccountError.setText("Error adding new Admin.");
            }

        }else{
            createAccountError.setText("Admin already exist");
        }
   }

}
