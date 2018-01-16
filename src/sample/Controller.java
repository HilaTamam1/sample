package sample;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    MsAccessDatabaseConnectionInJava8 myDB=new MsAccessDatabaseConnectionInJava8();
    @FXML
    private javafx.scene.control.Button newUser;
    @FXML
    private javafx.scene.control.TextField email;
    @FXML
    private javafx.scene.control.TextField password;
    @FXML
    public void NewUser(ActionEvent actionEvent) throws IOException {

        Stage stage1 = (Stage) newUser.getScene().getWindow();
        stage1.close();
        Stage stage2 = new Stage();
        stage2.setTitle("Sign up");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("../sample/newUser.fxml").openStream());
        Scene scene = new Scene(root, 600, 350);
        scene.getStylesheets().add(getClass().getResource("newUser.css").toExternalForm());
        stage2.setScene(scene);
        stage2.show();


    }
    @FXML
    public void SignedUser(){
        String sMail=email.getText();
        if(!sMail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
            showErrorAlert("Invalid Email","","Please enter a new mail");
        }


        ResultSet resultSet = null;
        try {
            resultSet = myDB.statement.executeQuery("SELECT * FROM Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            boolean exist=false;
            while(resultSet.next()) {
                if(sMail.equals(resultSet.getString(4)))
                {
                    exist=true;
                    if(password.getText().equals(resultSet.getString(5))){
                            openAction();

                    }
                    else {
                        showErrorAlert("Invalid password", "", "Please enter correct password");
                    }
                }
            }
            if(!exist){
                showErrorAlert("Invalid User","","Please Sign up to the system");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openAction() {
        Stage stage1 = (Stage) email.getScene().getWindow();
        stage1.close();

        Stage stage2 = new Stage();
        stage2.setTitle("Choose Action");
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("../sample/actions.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 450, 300);
        scene.getStylesheets().add(getClass().getResource("newUser.css").toExternalForm());
        Actions actions= fxmlLoader.getController();
        actions.setText(email.getText());

        stage2.setScene(scene);
        stage2.show();
    }


    public void showErrorAlert(String s1, String s2,String s3)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(s1);
        alert.setHeaderText(s2);
        alert.setContentText(s3);
        alert.show();
    }

    public void searchProduct(ActionEvent actionEvent) {
        Stage stage1 = (Stage) email.getScene().getWindow();
        stage1.close();

        Stage stage2 = new Stage();
        stage2.setTitle("Search Product");
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("../sample/searchProduct.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 450, 300);
        scene.getStylesheets().add(getClass().getResource("newUser.css").toExternalForm());


        stage2.setScene(scene);
        stage2.show();
    }
}
