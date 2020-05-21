package com.gms.controller.member;

import com.gms.dao.member.*;
import com.gms.model.UserSession;
import com.gms.model.member.*;
import com.gms.model.trainer.Trainer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PreferenceController {

    @FXML
    VBox preferencePane;
    @FXML
    ToggleGroup shiftRadio;

    @FXML
    CheckBox checkGymPackage;

    @FXML
    CheckBox checkSwimPackage;
    @FXML
    CheckBox checkGymInstructor;
    @FXML
    CheckBox checkSwimInstructor;

    @FXML
    ComboBox<Trainer> selectGymInstructor;

    @FXML
    ComboBox<Trainer> selectSwimInstructor;
    @FXML RadioButton morningRadio;
    @FXML RadioButton dayRadio;
    @FXML RadioButton eveningRadio;

    @FXML ComboBox<BodyType> selectBodyType;


    //initialize daos
    PreferenceDao preferenceDao;

    //get swimming or gym trainer
    TrainerDao trainerDao;
    //Trainer dao gives the package a member is subscribed to but
    TrainingDao trainingDao;
    PaymentDao paymentDao;

    Member member;
    Trainer myGymTrainer;
    Trainer mySwimTrainer;

    //Member Dao
    MemberDao memberDao;

    //Member Preference
    Preference preference;

    TrainingPackage swimpackage;
    TrainingPackage gympackage;


    @FXML
    public void initialize(){
        //set certain elements disabled in the beggining

        checkGymInstructor.setDisable(true);
        checkSwimInstructor.setDisable(true);
        selectSwimInstructor.setDisable(true);
        selectGymInstructor.setDisable(true);
        //get shift and ideal body type using preference dao from database
        preferenceDao = new PreferenceDao();
        //get package  from database
        trainingDao = new TrainingDao();
        //get selected instructor from database;
        trainerDao = new TrainerDao();
        //get instance of member dao;
        memberDao = new MemberDao();
        paymentDao = new PaymentDao();

       // System.out.println(UserSession.getInstance().getSession().getId());

        member = memberDao.getMemberByUserId(UserSession.getInstance().getSession().getId());


        //ObservableList<TrainingPackage> packages= trainingDao.getPackageListByMemberId(member.getId());

         swimpackage  = trainingDao.getTrainingPackageByMember(member,"Swimming");
        gympackage = trainingDao.getTrainingPackageByMember(member,"Gym");
        /*
        for(TrainingPackage pack: packages){

            if(pack.getPackageName().equals("Swimming")){
                swimpackage = true;
            }else if(pack.getPackageName().equals("Gym")){
                gympackage = true;
            }
            System.out.println(pack.getPackageName());
        }
         */

       ObservableList<Trainer> gymTrainerList = trainerDao.getAllTrainer("gym");
       ObservableList<Trainer> swimmingTrainerList = trainerDao.getAllTrainer("swimming");
        selectGymInstructor.setItems(gymTrainerList);
        selectSwimInstructor.setItems(swimmingTrainerList);


        if(swimpackage != null){
            checkSwimPackage.setSelected(true);
            checkSwimInstructor.setDisable(false);
            //Get instructor of the current member
            Instructor_Training swimmingTraining = trainerDao.getMemberInstructor(member.getId(),"swimming");
            if(swimmingTraining != null){
                checkSwimInstructor.setSelected(true);
                selectSwimInstructor.setDisable(false);

                mySwimTrainer = swimmingTraining.getTrainer();
                //The following code is just to set show the trainer
//                 int swimmingTrainerIndex = swimmingTrainerList.size();
//                for(Trainer trainer:swimmingTrainerList){
//                    if(trainer.getTrainer_id() == mySwimTrainer.getTrainer_id()){
//                        swimmingTrainerIndex--;
//                    }
//                }
//                selectSwimInstructor.getSelectionModel().select(swimmingTrainerIndex);
                selectSwimInstructor.setValue(mySwimTrainer);

            }
            //Return the swimming trainer list


            //selectGymInstructor.setSelectionModel();
        }
        if(gympackage != null){
            checkGymPackage.setSelected(true);
            checkGymInstructor.setDisable(false);

            Instructor_Training gymTraining = trainerDao.getMemberInstructor(member.getId(),"gym");

            if(gymTraining != null){
                selectGymInstructor.setDisable(false);
                checkGymInstructor.setSelected(true);
                myGymTrainer = gymTraining.getTrainer();
//                 int gymtrainerindex = gymTrainerList.size();
//                for(Trainer trainer:gymTrainerList){
//                    if(trainer.getTrainer_id() == myGymTrainer.getTrainer_id()){
//                        gymtrainerindex--;
//                    }
//                }
//                System.out.println(gymtrainerindex);
//                selectGymInstructor.getSelectionModel().select(gymtrainerindex);

                selectGymInstructor.setValue(myGymTrainer);

            }

        }

        //Now shift and ideal body type
        ObservableList<BodyType> bodyTypes = preferenceDao.getAllBodyType();
        selectBodyType.setItems(bodyTypes);

       preference = preferenceDao.getPreferenceByMemberId(member.getId());

        if(preference != null) {
            selectBodyType.setValue(preference.getIdeal_body());


             if (preference.getShift().equals("Day")) {
                dayRadio.setSelected(true);
            } else if (preference.getShift().equals("Evening")) {
                eveningRadio.setSelected(true);
            }
             else {
                morningRadio.setSelected(true);
            }
        }



    }

    public void update(ActionEvent actionEvent) {

        //trainingPackage.setPackageId();

//        RadioButton selectedRadioButton = (RadioButton) shiftRadio.getSelectedToggle();
//        System.out.println(selectedRadioButton.getText());



        //Add or remove packages
        //if gym checkbox selected;

        //package id is 1 for swimming  2 for gym
        if(checkGymPackage.isSelected()){
            trainingDao.AddOrremoveTrainingPackageForMember(2,member,true);
            if(gympackage == null)
            paymentDao.addInvoice(member,"Gym fee",trainingDao.getTrainingPackageByMember(member,"Gym").getPackagePrice());

            //now setting trainers for the user
            if(checkGymInstructor.isSelected()){
                //add the trainer if not existed
                //get the selected trainer
                Trainer newGymtrainer = new Trainer();
                newGymtrainer = selectGymInstructor.getValue();

                //first check if this is the first time adding trainer
                if(newGymtrainer != null) { // only if one of the instructor is selected

                    if (myGymTrainer == null) {
                        trainerDao.subscribeTrainer(newGymtrainer, member);
                        paymentDao.addInvoice(member,"Gym Instructor fee",100);

                    } else if (newGymtrainer.getTrainer_id() == myGymTrainer.getTrainer_id()) {
                        //do nothing
                    } else {
                        trainerDao.updateTrainer(myGymTrainer, newGymtrainer, member);
                    }

                }
                //now add the trainer

            }else{
                //remove the trainer subscription if existed
                if(myGymTrainer != null) trainerDao.removeSubscription(member, myGymTrainer);
            }

        }else{

            trainingDao.AddOrremoveTrainingPackageForMember(2,member,false);

            //remove trainer if existed
            if(myGymTrainer != null) trainerDao.removeSubscription(member, myGymTrainer);
        }

        if(checkSwimPackage.isSelected()){
            trainingDao.AddOrremoveTrainingPackageForMember(1,member,true);

            if(swimpackage== null) paymentDao.addInvoice(member,"Swimming fee",trainingDao.getTrainingPackageByMember(member,"Swimming").getPackagePrice());

            if(checkSwimInstructor.isSelected()){

                Trainer newSwimTrainer = selectSwimInstructor.getValue();

                //first check if this is the first time adding trainer
                if(newSwimTrainer != null) { // only if one of the instructor is selected


                    if (mySwimTrainer == null) {
                        trainerDao.subscribeTrainer(newSwimTrainer, member);
                        paymentDao.addInvoice(member,"Swimming Instructor fee",150);
                    } else if (newSwimTrainer.getTrainer_id() == mySwimTrainer.getTrainer_id()) {
                        //do nothing
                    } else {
                        trainerDao.updateTrainer(mySwimTrainer, newSwimTrainer, member);
                    }

                }
                //now add the trainer

            }else{
                //remove the trainer subscription if existed
                if(mySwimTrainer != null) trainerDao.removeSubscription(member, mySwimTrainer);
            }
        }else{
            trainingDao.AddOrremoveTrainingPackageForMember(1,member,false);
            if(mySwimTrainer != null) trainerDao.removeSubscription(member, mySwimTrainer);
        }


        BodyType preferredBodyType = selectBodyType.getValue();
        //Now update the preferences
        RadioButton selectedRadioButton = (RadioButton) shiftRadio.getSelectedToggle();


        if(preference == null) {
            if (preferredBodyType != null)
                preferenceDao.addPreference(member, selectedRadioButton.getText(), preferredBodyType);
        }else{
            if (preferredBodyType != null)
                preferenceDao.updatePreference(member, selectedRadioButton.getText(), preferredBodyType);
        }
        //go to the home after update


        VBox rootPane = (VBox) preferencePane.getParent();
        try {

            VBox homePane = FXMLLoader.load(getClass().getResource("/view/member/memberHome.fxml"));
            rootPane.getChildren().setAll(homePane);
        }
        catch(IOException e){

        }


    }






    public void checkGymPackageOnClick(ActionEvent actionEvent) {
        if(checkGymPackage.isSelected()) {
            checkGymInstructor.setDisable(false);
        }else{
            checkGymInstructor.setDisable(true);
        }

    }

    public void checkSwimPackageOnClick(ActionEvent actionEvent) {
        if(checkSwimPackage.isSelected()) {
            checkSwimInstructor.setDisable(false);
        }else{
            checkSwimInstructor.setDisable(true);
        }
    }

    public void checkGymInstructorOnClick(ActionEvent actionEvent) {
        if(checkGymInstructor.isSelected()){
            selectGymInstructor.setDisable(false);
        }else{
            selectGymInstructor.setDisable(true);
        }
    }

    public void checkSwimmingInstructorOnClick(ActionEvent actionEvent) {
        if(checkSwimInstructor.isSelected()){
            selectSwimInstructor.setDisable(false);
        }else{
            selectSwimInstructor.setDisable(true);
        }
    }
}
