package com.gms.controller.Admin;

import com.gms.dao.member.MemberDao;
import com.gms.model.member.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class MemberListController {

    @FXML
    VBox addMember;
    @FXML
    TableView<Member> membersTable;

    @FXML
    TableColumn<Member,String> member_fname;

    @FXML
    TableColumn<Member,String> member_lname;

    @FXML
    TableColumn<Member,String> member_phone;

    @FXML
    TableColumn<Member,String> member_email;

    @FXML
    TableColumn payment_due;

    @FXML
    TableColumn<Member,String> emergency_contact;
    @FXML
    public void initialize(){

        MemberDao memberDao = new MemberDao();
        member_fname.setCellValueFactory(new PropertyValueFactory<Member,String>("fname"));
        member_lname.setCellValueFactory(new PropertyValueFactory<Member,String>("lname"));
        member_phone.setCellValueFactory(new PropertyValueFactory<Member,String>("contact"));
        member_email.setCellValueFactory(new PropertyValueFactory<Member,String>("email"));
        emergency_contact.setCellValueFactory(new PropertyValueFactory<Member,String>("econtactno"));

        membersTable.setItems(memberDao.getMembers());

    }

    public void addMember(ActionEvent actionEvent) {
        try {
            //gets the wrapper parent component of current component
            VBox rootPane = (VBox) addMember.getParent();

            VBox addMemberPane = FXMLLoader.load(getClass().getResource("/view/admin/addMember.fxml"));
            rootPane.getChildren().setAll(addMemberPane);
        }
        catch(IOException e){

        }

    }
}
