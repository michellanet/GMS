package com.gms.controller.Admin;

import com.gms.dao.UserDao;
import com.gms.dao.member.MemberDao;
import com.gms.model.User;
import com.gms.model.member.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AddMemberController {

    @FXML
    TextField txtfname;
    @FXML TextField txtlname;
    @FXML TextField txtusername;
    @FXML TextField txtpassword;
    @FXML TextField txtrpassword;
    @FXML
    Label createAccountError;

    @FXML VBox addMemberPane;

    //DAO initialization
    UserDao dao;
    MemberDao mdao;
    @FXML
    public void initialize(){
        dao = new UserDao();
        mdao = new MemberDao();
    }

    public void createAccount(ActionEvent actionEvent) {
        User user = new User("Member",txtusername.getText(),txtpassword.getText());
        Member member = new Member();

        if(!dao.userExist(user)){
                if(dao.addNewUser(user)){
                    System.out.println(user);
                    member.setUser_id(user.getId());
                    member.setFname(txtfname.getText());
                    member.setLname(txtlname.getText());
                    //member.setAccountcreated(new java.sql.Date(java.util.Calendar.getInstance().getTime().getTime()));
                    System.out.println(member);
                    if(mdao.addNewMember(member)) {
                        //gets the wrapper parent component of current component
                        try {
                            //gets the wrapper parent component of current component
                            VBox rootPane = (VBox) addMemberPane.getParent();

                            VBox editMember = FXMLLoader.load(getClass().getResource("/view/admin/memberList.fxml"));
                            rootPane.getChildren().setAll(editMember);
                        } catch (IOException e) {

                        }
                    }
                    else{
                        //cannot create the user
                        System.out.println(user);
                        createAccountError.setText("Couldn't add new member.");
                        dao.removeUser(user);
                        //remove user as well.
                    }
                }else{
                    createAccountError.setText("Error adding new member.");
                }
        }else{

            createAccountError.setText("Member already exist");
        }
    }
}
