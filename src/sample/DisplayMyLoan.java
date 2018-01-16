package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;


public class DisplayMyLoan {

    @FXML
    private TextArea txt_ownerEmail;
    @FXML
    private TextArea txt_name;
    @FXML
    private TextArea txt_type;
    @FXML
    private TextArea txt_price;
    @FXML
    private TextArea txt_id;
    @FXML
    private TextArea txt_loanDuration;

    @FXML
    private javafx.scene.control.Button pre;
    MsAccessDatabaseConnectionInJava8 myDB = new MsAccessDatabaseConnectionInJava8();
    String email;

    public void setMail(String mail){
        email=mail;
    }
    public void previousWindow(ActionEvent actionEvent) {
        Stage stage1 = (Stage) pre.getScene().getWindow();
        stage1.close();
    }

    public void setText(){
        ResultSet resultSet = null;
        ResultSet resultSetPack = null;
        ArrayList<String> products=new ArrayList<>();
        boolean found=false;
        try {
            resultSet = myDB.statement.executeQuery("SELECT * FROM Product");
            resultSetPack = myDB.statement.executeQuery("SELECT * FROM Packages");


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            // resultSet.next();
            while(resultSetPack.next()) {
                if (email.equals(resultSetPack.getString(4))) {
                    products.add(resultSetPack.getString(1));
                }
            }
            resultSet=myDB.statement.executeQuery("SELECT * FROM Product");
            while (resultSet.next()) {
                if(products.contains(resultSet.getString(7))){
                    txt_name.setText(txt_name.getText() + resultSet.getString(6) + "\n");
                    txt_id.setText(txt_id.getText() + resultSet.getString(7) + "\n");
                    txt_type.setText(txt_type.getText() + resultSet.getString(1) + "\n");
                    txt_loanDuration.setText(txt_loanDuration.getText() + resultSet.getString(4) + "\n");
                    txt_price.setText(txt_price.getText() + resultSet.getString(3) + "\n");
                    txt_ownerEmail.setText(txt_ownerEmail.getText() + resultSet.getString(5) + "\n");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}