package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class NewUser {
    MsAccessDatabaseConnectionInJava8 myDB=new MsAccessDatabaseConnectionInJava8();
    @FXML
    private javafx.scene.control.TextField firstName;
    @FXML
    private javafx.scene.control.TextField lastName;
    @FXML
    private javafx.scene.control.TextField email;
    @FXML
    private javafx.scene.control.TextField password;
    @FXML
    private javafx.scene.control.TextField phonenumber;
    @FXML
    private javafx.scene.control.CheckBox checkBox;
    @FXML
    public void addUser(){
        String sfirstName=firstName.getText();
        String slastName=lastName.getText();
        String semail=email.getText();
        String spassword=password.getText();
        String sphonenumber=phonenumber.getText();
        String sOwner="0";
        if(checkBox.isSelected()){
            sOwner="1";
        }
        if (firstName.getText().equals("") || lastName.getText().equals("") || email.getText().equals("") || password.getText().equals("") || phonenumber.getText().equals("")) {
            showErrorAlert("Error","Please fill all the fields","");

        }
        else {
            String sMail = email.getText();
            if (!sMail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                showErrorAlert("Invalid Email", "", "Please enter a valid mail");
            } else {
                myDB.insertToUsers(sfirstName, slastName, semail, spassword, sphonenumber, sOwner);
                sendMail(semail, sfirstName, slastName);

                showErrorAlert("", "", "Your registration has been successfully registered");
                Stage stage1 = (Stage) firstName.getScene().getWindow();
                stage1.close();


                Stage stage2 = new Stage();
                stage2.setTitle("Login");
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root = null;
                try {
                    root = fxmlLoader.load(getClass().getResource("../sample/signIn.fxml").openStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root, 675, 400);
                scene.getStylesheets().add(getClass().getResource("signIn.css").toExternalForm());
                stage2.setScene(scene);
                stage2.show();
            }
        }
    }


    public void showErrorAlert(String s1, String s2,String s3)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(s1);
        alert.setHeaderText(s2);
        alert.setContentText(s3);
        alert.show();
    }
    private  void sendMail(String to, String sfirstName,String slastName)
    {

        String from="everything4rent2018@gmail.com";
        String password="placerenter";
        String message="Thank You "+sfirstName+" "+slastName+" for registering to our app Everything4Rent";
        String host="smtp.gmail.com";
        Properties props= System.getProperties();
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.user",from);
        props.put("mail.smtp.password",password);
        props.put("mail.smtp.port",587);
        props.put("mail.smtp.auto","true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        MimeMessage mimeMessage=new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(from));
            InternetAddress toAddress=new InternetAddress(to);
            mimeMessage.addRecipient(Message.RecipientType.TO,toAddress);
            mimeMessage.setSubject("Welcome to Everything4Rent");
            mimeMessage.setText(message);
            Transport transport=session.getTransport("smtp");
            transport.connect(host,from,password);
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toThePre(ActionEvent actionEvent) {
        Stage stage1 = (Stage) firstName.getScene().getWindow();
        stage1.close();

        Stage primaryStage=new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("signIn.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root,675,400);
        scene.getStylesheets().add(getClass().getResource("signIn.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Everything4Rent");
        primaryStage.show();
    }
}
