package com.gms.controller.trainer;

import com.gms.dao.member.MemberDao;
import com.gms.dao.trainer.TrainerDao;
import com.gms.model.UserSession;
import com.gms.model.member.Member;
import com.gms.model.trainer.Trainer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class HomeController {

    @FXML
    Label trainerId;

    @FXML
    Label trainerName;

    @FXML
    private TextField searchInput;

    @FXML
    private Button searchBtn;

    @FXML
    private TableView<Member> trainerMembers;

    @FXML
    private TableColumn<Member, String> name;

    @FXML
    private TableColumn<Member, String> email;

    @FXML
    private TableColumn<Member, String> phone;

    @FXML
    private TableColumn<Member, String> paymentStatus;

    @FXML
    private TableColumn<Member, String> eContactName;

    @FXML
    private TableColumn<Member, String> ePhone;

    @FXML
    private TableColumn<Member, String> eContactNo;
    @FXML
    public void initialize(){
        TrainerDao trainerDao = new TrainerDao();
        //get current session;
        UserSession session = UserSession.getInstance();
        System.out.println(session.getSession().getId());

        Trainer trainer = trainerDao.getTrainerByUserId(session.getSession().getId());
        System.out.println(trainer);

        //get member for the trainer from trainer list


        name.setCellValueFactory(new PropertyValueFactory<Member,String>("fname"));
       // member_lname.setCellValueFactory(new PropertyValueFactory<Member,String>("lname"));
        phone.setCellValueFactory(new PropertyValueFactory<Member,String>("contact"));
        email.setCellValueFactory(new PropertyValueFactory<Member,String>("email"));
        eContactNo.setCellValueFactory(new PropertyValueFactory<Member,String>("econtactno"));



        //fName.setText("Welcome, " + trainer.getFirstName());
        trainerName.setText(trainer.getFname() + " " + trainer.getLname());
        //email.setText(trainer.getEmail());
        //phone.setText("" + trainer.getPhone());
        trainerId.setText("" + ("TR" + trainer.getTrainer_id()));
        //experience.setText(trainer.getExperience());

        trainerMembers.setItems(trainerDao.getTrainingMembers(trainer));
    }

    @FXML
    void search(ActionEvent event) {
        if(searchBtn.getText().equals("Search") ){
            searchBtn.setText("Clear");
            TrainerDao trainerDao = new TrainerDao();
            //get current session;
            UserSession session = UserSession.getInstance();
            System.out.println(session.getSession().getId());

            Trainer trainer = trainerDao.getTrainerByUserId(session.getSession().getId());
            System.out.println(trainer);

            //get member for the trainer from trainer list


            name.setCellValueFactory(new PropertyValueFactory<Member,String>("fname"));
            // member_lname.setCellValueFactory(new PropertyValueFactory<Member,String>("lname"));
            phone.setCellValueFactory(new PropertyValueFactory<Member,String>("contact"));
            email.setCellValueFactory(new PropertyValueFactory<Member,String>("email"));
            eContactNo.setCellValueFactory(new PropertyValueFactory<Member,String>("econtactno"));



            //fName.setText("Welcome, " + trainer.getFirstName());
            trainerName.setText(trainer.getFname() + " " + trainer.getLname());
            //email.setText(trainer.getEmail());
            //phone.setText("" + trainer.getPhone());
            trainerId.setText("" + ("TR" + trainer.getTrainer_id()));
            //experience.setText(trainer.getExperience());

            trainerMembers.setItems(trainerDao.getTrainingMembers(trainer, searchInput.getText()));
        }
        else{
            searchInput.clear();
            searchBtn.setText("Search");
            TrainerDao trainerDao = new TrainerDao();
            //get current session;
            UserSession session = UserSession.getInstance();
            System.out.println(session.getSession().getId());

            Trainer trainer = trainerDao.getTrainerByUserId(session.getSession().getId());
            System.out.println(trainer);

            //get member for the trainer from trainer list


            name.setCellValueFactory(new PropertyValueFactory<Member,String>("fname"));
            // member_lname.setCellValueFactory(new PropertyValueFactory<Member,String>("lname"));
            phone.setCellValueFactory(new PropertyValueFactory<Member,String>("contact"));
            email.setCellValueFactory(new PropertyValueFactory<Member,String>("email"));
            eContactNo.setCellValueFactory(new PropertyValueFactory<Member,String>("econtactno"));



            //fName.setText("Welcome, " + trainer.getFirstName());
            trainerName.setText(trainer.getFname() + " " + trainer.getLname());
            //email.setText(trainer.getEmail());
            //phone.setText("" + trainer.getPhone());
            trainerId.setText("" + ("TR" + trainer.getTrainer_id()));
            //experience.setText(trainer.getExperience());

            trainerMembers.setItems(trainerDao.getTrainingMembers(trainer));
        }
    }

}
