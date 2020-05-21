package com.gms.controller.Admin;

import com.gms.dao.admin.AdminDao;
import com.gms.dao.member.MemberDao;
import com.gms.model.admin.Admin;
import com.gms.model.member.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AdminListController {
    @FXML
    VBox adminList;
    @FXML
    TableView<Admin> AdminTable;

    @FXML
    TableColumn<Admin,String> admin_fname;

    @FXML
    TableColumn<Admin,String> admin_lname;

    @FXML
    TableColumn<Admin,String> admin_phone;

    @FXML
    TableColumn<Admin,String> admin_email;

    AdminDao adminDao;
    @FXML
    public void initialize(){


        adminDao = new AdminDao();
        admin_fname.setCellValueFactory(new PropertyValueFactory<Admin,String>("fname"));
        admin_lname.setCellValueFactory(new PropertyValueFactory<Admin,String>("lname"));
        admin_phone.setCellValueFactory(new PropertyValueFactory<Admin,String>("contact"));
        admin_email.setCellValueFactory(new PropertyValueFactory<Admin,String>("email"));

        System.out.println(adminDao.getAdmins());

      AdminTable.setItems(adminDao.getAdmins());



    }

    public void addAdmin(ActionEvent actionEvent) {
        try {
            //gets the wrapper parent component of current component
            VBox rootPane = (VBox) adminList.getParent();

            VBox addMemberPane = FXMLLoader.load(getClass().getResource("/view/admin/AddAdmin1.fxml"));
            rootPane.getChildren().setAll(addMemberPane);
        }
        catch(IOException e){

        }

    }
}
