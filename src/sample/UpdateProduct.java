package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.sql.*;

/**
 * Created by lenovo on 29/12/2017.
 */
public class UpdateProduct {
    String email;
    MsAccessDatabaseConnectionInJava8 myDB=new MsAccessDatabaseConnectionInJava8();
    @FXML
    ChoiceBox<String> cb;
    @FXML
    private javafx.scene.control.TextField toChange;
    @FXML
    private javafx.scene.control.TextField id;
    @FXML
    private javafx.scene.control.TextArea txt_id;
    @FXML
    private javafx.scene.control.TextArea txt_name;
    @FXML
    private javafx.scene.control.TextArea txt_type;
    @FXML
    private javafx.scene.control.TextArea txt_location;
    @FXML
    private javafx.scene.control.TextArea txt_loanDuration;
    @FXML
    private javafx.scene.control.TextArea txt_price;
    @FXML
    private javafx.scene.control.TextArea txt_exchangable;
    @FXML
    private javafx.scene.control.TextArea txt_rentable;
    @FXML
    private javafx.scene.control.TextArea txt_ownerEmail;
    public void setEmail(String email) {
        this.email = email;
    }
    public void updateProduct(){
        String sType = cb.getSelectionModel().getSelectedItem();
        String sType1=null;
        if(sType.equals("Name Product")){
            sType1="nameProduct";
        }
        else if(sType.equals("Location")){
            sType1="location";
        }
        else if(sType.equals("Price" )){
            sType1="price";
        }
        else if(sType.equals("Loan duration")){
            sType1="loanDuration";
        }
        ResultSet resultSet=null;
        boolean ans=false;
        try {
        resultSet = myDB.statement.executeQuery("SELECT * FROM Product");
        while(resultSet.next()) {
            if (email.equals(resultSet.getString(5)) && id.getText().equals(resultSet.getString(7))) {
                ans = true;

            }
        }
        String sq1 = "UPDATE Product SET " + sType1+" = '" + toChange.getText()+"' Where ProductId"+" = "+ id.getText()  ;

            if(ans){
                myDB.statement.executeUpdate(sq1);


            }
            else{
                showErrorAlert("", "You can't update this product", "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        clearTextArea();
        setText();

    }
    public void previousWindow()
    {
        Stage stage1 = (Stage) toChange.getScene().getWindow();
        stage1.close();
    }
    public void setText(){
        ResultSet resultSet = null;
        try {
            resultSet = myDB.statement.executeQuery("SELECT * FROM Product");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while(resultSet.next()) {
                if(email.equals(resultSet.getString(5))) {
                    txt_name.setText(txt_name.getText()+resultSet.getString(6) + "\n");
                    txt_id.setText(txt_id.getText()+resultSet.getString(7) + "\n");
                    txt_type.setText(txt_type.getText()+resultSet.getString(1) + "\n");
                    txt_location.setText(txt_location.getText()+resultSet.getString(2) + "\n");
                    txt_loanDuration.setText(txt_loanDuration.getText()+resultSet.getString(4) + "\n");
                    txt_price.setText(txt_price.getText()+resultSet.getString(3) + "\n");
                    txt_exchangable.setText(txt_exchangable.getText()+resultSet.getString(9) + "\n");
                    txt_rentable.setText(txt_rentable.getText()+resultSet.getString(8) + "\n");
                    txt_ownerEmail.setText(txt_ownerEmail.getText()+resultSet.getString(5) + "\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void clearTextArea(){
        txt_id.setText("");
        txt_name.setText("");
        txt_type.setText("");
        txt_location.setText("");
        txt_loanDuration.setText("");
        txt_price.setText("");
        txt_exchangable.setText("");
        txt_rentable.setText("");
        txt_ownerEmail.setText("");
    }
    public void showErrorAlert(String s1, String s2,String s3)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(s1);
        alert.setHeaderText(s2);
        alert.setContentText(s3);
        alert.show();
    }

}
