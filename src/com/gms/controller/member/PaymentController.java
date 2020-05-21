package com.gms.controller.member;

import com.gms.dao.member.MemberDao;
import com.gms.dao.member.PaymentDao;
import com.gms.model.UserSession;
import com.gms.model.member.Member;
import com.gms.model.member.Payment;
import com.gms.model.member.PaymentHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import javax.swing.plaf.LabelUI;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PaymentController{

    @FXML
    Label labelPayment;
    @FXML Label paymentText;

    @FXML VBox paymentPane;
    @FXML
    Button payButton;

    PaymentDao paymentDao;
    MemberDao memberDao;
    Member member;
    double totalDue;
    double totalLastMonthPay;

    @FXML
    TableColumn<PaymentHistory,String> colPayDate;
    @FXML
    TableColumn<PaymentHistory,String> colPayAmount;

    @FXML TableView<PaymentHistory> payTable;
    @FXML
    public void initialize(){

        colPayDate.setCellValueFactory(new PropertyValueFactory<PaymentHistory,String>("pay_date"));
        colPayAmount.setCellValueFactory(new PropertyValueFactory<PaymentHistory,String>("amount"));

        memberDao = new MemberDao();
        paymentDao = new PaymentDao();

        member = memberDao.getMemberByUserId(UserSession.getInstance().getSession().getId());

        ObservableList<Payment> lastMonthbalanceList = FXCollections.observableArrayList();


        lastMonthbalanceList = paymentDao.getAlllastMonthBalanceList(member);

        double lastmonthPayAmountDue = 0;


        System.out.println(lastmonthPayAmountDue);
        for(Payment p: lastMonthbalanceList){
            lastmonthPayAmountDue += p.getAmount();
        }
        System.out.println(lastmonthPayAmountDue);
        ObservableList<PaymentHistory> lastMonth = paymentDao.getLastMonthPayment(member);

        for(PaymentHistory eachpay: lastMonth){
            totalLastMonthPay += eachpay.getAmount();
        }
        totalDue = lastmonthPayAmountDue - totalLastMonthPay;

        labelPayment.setText("$"+totalDue);

        if(totalDue > 0){
            paymentText.setText("Required Payment Date: "+ member.getNextpaydate().toLocalDate());
        }else{
            paymentText.setText("Hurray! No payment due");
            payButton.setDisable(true);
        }

        payTable.setItems(paymentDao.getAllpayHistory(member));

    }


    public void pay(ActionEvent event) {
        if(paymentDao.addPaymentHistory(member,totalDue)){

            Alert alert = new Alert(Alert.AlertType.INFORMATION,"", ButtonType.OK);
            //alert.setContentText(content);
            alert.setContentText("Successfully added the record.");
            alert.getDialogPane().setPrefSize(300, 150);
            alert.showAndWait();

        VBox rootPane = (VBox) paymentPane.getParent();
        try {

            VBox paymentPaneAgain = FXMLLoader.load(getClass().getResource("/view/member/paymentHistory.fxml"));
            rootPane.getChildren().setAll(paymentPaneAgain);
        }
        catch(IOException e){

        }
        }
    }
}
