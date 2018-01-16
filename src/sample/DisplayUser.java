package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DisplayUser {
    MsAccessDatabaseConnectionInJava8 myDB=new MsAccessDatabaseConnectionInJava8();
    @FXML
    private TextArea firstName;
    @FXML
    private TextArea lastName;
    @FXML
    private TextArea email;
    @FXML
    private TextArea password;
    @FXML
    private TextArea phonenumber;
    @FXML
    private TextArea renter;

    private String sEmail;
    public void previousWindow(ActionEvent actionEvent) {
        Stage stage1 = (Stage) firstName.getScene().getWindow();
        stage1.close();
    }

    public void setEmail(String email) {
        sEmail = email;
    }
    public void setText() {
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
                    email.setText(email.getText() + resultSet.getString(4));
                    password.setText(password.getText() + resultSet.getString(5) );
                    phonenumber.setText(phonenumber.getText() + resultSet.getString(6));
                    if(resultSet.getString(2).equals("1"))
                        renter.setText(renter.getText() + "yes");
                    else
                        renter.setText(renter.getText() + "no");

                }
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
