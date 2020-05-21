//package com.gms.controller.member;
//
//import com.gms.dao.admin.AdminDao;
//import com.gms.model.UserSession;
//import com.gms.model.admin.Admin;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//
//import java.io.IOException;
//
//public class EditAdmin {
//
//    @FXML VBox editProfilePane;
//    @FXML Label welcomeLabel;
//    @FXML Label labelMessage;
//
//    @FXML Label full_name;
//    @FXML Label username;
//    @FXML TextField textFieldEmail;
//    @FXML TextField textFieldPhone;
//    @FXML TextField textFieldAdd1;
//    @FXML TextField textFieldAdd2;
//    @FXML TextField emgCName;
//    @FXML TextField emgCNo;
//
//    AdminDao adao;
//
//    @FXML
//    public void initialize(){
//        adao = new AdminDao();
//        Admin  admin = adao.getAdminByAdminId(UserSession.getInstance().getSession().getId());
//
//        // this.full_name.setText(admin.getFname()+" "+admin.getLname());
//        this.welcomeLabel.setText("Welcome " + admin.getFname()+" "+admin.getLname());
//        this.username.setText(UserSession.getInstance().getSession().getUsername());
//        this.textFieldEmail.setText(admin.getEmail());
//        this.textFieldPhone.setText(admin.getContact());
//        this.textFieldAdd1.setText(admin.getAddline1());
//        this.textFieldAdd2.setText(admin.getAddline2());
//        this.emgCName.setText(admin.getEcontactname());
//        this.emgCNo.setText(admin.getEcontactno());
//    }
//
//    public void update(ActionEvent actionEvent) {
//        Admin admin = new Admin();
//        admin.setEmail(textFieldEmail.getText());
//        admin.setContact(textFieldPhone.getText());
//        admin.setAddline1(textFieldAdd1.getText());
//        admin.setAddline2(textFieldAdd2.getText());
//        admin.setEcontactname(emgCName.getText());
//        admin.setEcontactno(emgCNo.getText());
//        admin.setUser_id(UserSession.getInstance().getSession().getId());
//
//        if(adao.updateAdmin(admin)){
//            labelMessage.setText("SuccessFully Updated");
//            labelMessage.setTextFill(Color.web("#16de77"));
//            VBox rootPane = (VBox) editProfilePane.getParent();
//            try {
//
//                VBox adminProfilePane = FXMLLoader.load(getClass().getResource("/view/admin/adminProfile.fxml"));
//                rootPane.getChildren().setAll(adminProfilePane);
//            }
//            catch(IOException e){
//
//            }
//        }else{
//            labelMessage.setText("Error updating the course!! Please try again.");
//            labelMessage.setTextFill(Color.web("#de1616"));
//
//        }
//    }
//}
//
//
//
//
//
