package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UpdateUser {
    @FXML
    private javafx.scene.control.TextArea firstName;
    @FXML
    private javafx.scene.control.TextArea lastName;
    @FXML
    private javafx.scene.control.TextArea email11;
    @FXML
    private javafx.scene.control.TextArea password;
    @FXML
    private javafx.scene.control.TextArea phonenumber;
   // @FXML
  //  private javafx.scene.control.TextArea renter;
    @FXML
    private javafx.scene.control.ChoiceBox <String> cb;
    @FXML
    private javafx.scene.control.TextArea toChange;
    @FXML
    private javafx.scene.control.ChoiceBox areRenter;

    MsAccessDatabaseConnectionInJava8 myDB=new MsAccessDatabaseConnectionInJava8();
    String sEmail;


    public void previousWindow(ActionEvent actionEvent) {
        Stage stage1 = (Stage) firstName.getScene().getWindow();
        stage1.close();
    }

    public void updateUser1(ActionEvent actionEvent) {
       // setText(sEmail);
        String sType = cb.getSelectionModel().getSelectedItem();
        String sType1=null;

        if(sType.equals("First name")){
            sType1="Name1";
        }
        else if(sType.equals("Last name")){
            sType1="lastName";
        }
        else if(sType.equals("email" )){
            sType1="email";
        }
        else if(sType.equals("password")){
            sType1="password";
        }
        else if(sType.equals("phone number")){
            sType1="phoneNumber";
        }
        else if(sType.equals("Are you a renter?")){
            sType1="owner";
        }
        ResultSet resultSet=null;
        boolean ans=false;
        try {
            resultSet = myDB.statement.executeQuery("SELECT * FROM Users");
            while(resultSet.next()) {
                if (sEmail.equals(resultSet.getString(4))) {
                    ans = true;
                }
            }
            String change=toChange.getText();

            String sq1 = "UPDATE Users SET " + sType1+" = '" + toChange.getText()+"' Where email"+" = "+ sEmail  ;

            if(ans){
                myDB.statement.executeUpdate(sq1);
            }
            else{
                showErrorAlert("", "You can't update this product", "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void updateUser(ActionEvent actionEvent) {
        firstName.setText(firstName.getText());
        lastName.setText(lastName.getText());
        this.email11.setText(this.email11.getText());
        password.setText(password.getText());
        phonenumber.setText(phonenumber.getText());
        areRenter.setValue(areRenter.getValue());


            String renter="0";
            if(areRenter.getValue().equals("Yes"))
                renter="1";

            String sql = "DELETE FROM Users WHERE email=" + sEmail;
        try {
            myDB.statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        myDB.insertToUsers(firstName.getText(), lastName.getText(), this.email11.getText(), password.getText(), phonenumber.getText(), renter);
            showInfoAlert("Update detail","User information update successful","");

            Stage stage1 = (Stage) firstName.getScene().getWindow();
            stage1.close();
    }

    public void setText(String email)
    {
        this.sEmail=email;
        ResultSet resultSet = null;
        try {
            resultSet = myDB.statement.executeQuery("SELECT * FROM USERS");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (resultSet.next()) {
                if (sEmail.equals(resultSet.getString(4))) {
                    firstName.setText(firstName.getText() + resultSet.getString(1) );
                    lastName.setText(lastName.getText() + resultSet.getString(3) );
                    this.email11.setText(this.email11.getText() + resultSet.getString(4));
                    password.setText(password.getText() + resultSet.getString(5) );
                    phonenumber.setText(phonenumber.getText() + resultSet.getString(6));
                    if(resultSet.getString(2).equals("1"))
                        areRenter.setValue("Yes");
                    else
                        areRenter.setValue("No");
                }
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void showErrorAlert(String s1, String s2,String s3)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(s1);
        alert.setHeaderText(s2);
        alert.setContentText(s3);
        alert.show();
    }
    public void showInfoAlert(String s1, String s2,String s3)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(s1);
        alert.setHeaderText(s2);
        alert.setContentText(s3);
        alert.show();
    }
}
