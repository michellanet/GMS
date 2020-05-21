package com.gms.controller.member;

import com.gms.dao.member.MemberDao;
import com.gms.model.UserSession;
import com.gms.model.member.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;

public class EditProfile {

    @FXML VBox editProfilePane;
    @FXML Label welcomeLabel;
    @FXML Label labelMessage;

    @FXML
    Label full_name;
    @FXML Label username;
    @FXML
    TextField textFieldEmail;
    @FXML TextField textFieldPhone;
    @FXML TextField textFieldAdd1;
    @FXML TextField textFieldAdd2;
    @FXML TextField emgCName;
    @FXML TextField emgCNo;

    MemberDao mdao;

    @FXML
    public void initialize(){
        mdao = new MemberDao();
        Member member = mdao.getMemberByUserName(UserSession.getInstance().getSession().getUsername());

        this.full_name.setText(member.getFname()+" "+member.getLname());
        this.welcomeLabel.setText("Welcome " + member.getFname()+" "+member.getLname());
        this.username.setText(UserSession.getInstance().getSession().getUsername());
        this.textFieldEmail.setText(member.getEmail());
        this.textFieldPhone.setText(member.getContact());
        this.textFieldAdd1.setText(member.getAddline1());
        this.textFieldAdd2.setText(member.getAddline2());
        this.emgCName.setText(member.getEcontactname());
        this.emgCNo.setText(member.getEcontactno());
    }

    public void update(ActionEvent actionEvent) {
        Member member = new Member();
        member.setEmail(textFieldEmail.getText());
        member.setContact(textFieldPhone.getText());
        member.setAddline1(textFieldAdd1.getText());
        member.setAddline2(textFieldAdd2.getText());
        member.setEcontactname(emgCName.getText());
        member.setEcontactno(emgCNo.getText());
        member.setUser_id(UserSession.getInstance().getSession().getId());

        if(mdao.updateMember(member)){
            labelMessage.setText("SuccessFully Updated");
            labelMessage.setTextFill(Color.web("#16de77"));
            VBox rootPane = (VBox) editProfilePane.getParent();
            try {

                VBox memberProfilePane = FXMLLoader.load(getClass().getResource("/view/member/memberProfile.fxml"));
                rootPane.getChildren().setAll(memberProfilePane);
            }
            catch(IOException e){

            }
        }else{
            labelMessage.setText("Error updating the course!! Please try again.");
            labelMessage.setTextFill(Color.web("#de1616"));

        }
    }
}
