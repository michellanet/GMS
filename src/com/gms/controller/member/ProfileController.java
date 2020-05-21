package com.gms.controller.member;

import com.gms.dao.member.MemberDao;
import com.gms.model.UserSession;
import com.gms.model.member.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class ProfileController {

    @FXML
    VBox profilePane;

    @FXML
    Label full_name;

    @FXML
    Label email;

    @FXML
    Label mobile;

    @FXML
    Label address1;

    @FXML
    Label address2;

    @FXML
    Label username;

    @FXML
    Label eContactName;

    @FXML
    Label eContact;

    @FXML
    public void initialize(){
        MemberDao memberDao = new MemberDao();
        //get current session;
        UserSession session = UserSession.getInstance();
        System.out.println(session.getSession().getId());
        Member member = memberDao.getMemberByUserId(session.getSession().getId());

        full_name.setText(member.getFname() + " "+ member.getLname());
        email.setText(member.getEmail());
        username.setText(session.getSession().getUsername());
        mobile.setText(member.getContact());
        address1.setText(member.getAddline1());
        address2.setText(member.getAddline2());
        eContact.setText(member.getEcontactno());
        eContactName.setText(member.getEcontactname());

    }

    public void editProfile(ActionEvent actionEvent) {
        //gets the wrapper parent component of current component
        VBox rootPane = (VBox) profilePane.getParent();
        try {

            VBox editProfilePane = FXMLLoader.load(getClass().getResource("/view/member/editProfile.fxml"));
            rootPane.getChildren().setAll(editProfilePane);
        }
        catch(IOException e){

        }

    }
}
