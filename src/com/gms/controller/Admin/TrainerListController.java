package com.gms.controller.Admin;

import com.gms.dao.member.TrainerDao;
import com.gms.model.member.Member;
import com.gms.model.trainer.Trainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TrainerListController {

    @FXML
    VBox trainerList;
    @FXML
    TableView<Trainer> trainersTable;

    @FXML
    TableColumn<Trainer, String> trainer_fname;

    @FXML
    TableColumn<Trainer, String> trainer_lname;

    @FXML
    TableColumn<Trainer,String> trainer_phone;

    @FXML
    TableColumn<Trainer,String> trainer_email;

    @FXML
    TableColumn<Trainer,String> trainer_type;


    @FXML
    public void initialize(){

        TrainerDao trainerDao = new TrainerDao();
        trainer_fname.setCellValueFactory(new PropertyValueFactory<Trainer,String>("fname"));
        trainer_lname.setCellValueFactory(new PropertyValueFactory<Trainer,String>("lname"));
        trainer_phone.setCellValueFactory(new PropertyValueFactory<Trainer,String>("phone"));
        trainer_email.setCellValueFactory(new PropertyValueFactory<Trainer,String>("email"));
        trainer_type.setCellValueFactory(new PropertyValueFactory<Trainer,String>("trainer_type"));
        System.out.println(trainerDao.getAllTrainers());
//       Trainer trainer = trainerDao.getAllTrainers().get(0);
//        System.out.println(trainer.getFname());
//        System.out.println(trainer.getPhone());
//        System.out.println(trainer.getEmail());
//        System.out.println(trainer.getTrainer_type());

        trainersTable.setItems(trainerDao.getAllTrainers());

    }

    public void addTrainer(ActionEvent actionEvent) {
        try {
            //gets the wrapper parent component of current component
            VBox rootPane = (VBox) trainerList.getParent();

            VBox addMemberPane = FXMLLoader.load(getClass().getResource("/view/admin/addTrainer.fxml"));
            rootPane.getChildren().setAll(addMemberPane);
        }
        catch(IOException e){

        }

    }
}
