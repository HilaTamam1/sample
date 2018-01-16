package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CreateProduct {
    MsAccessDatabaseConnectionInJava8 myDB=new MsAccessDatabaseConnectionInJava8();
    String email;

    @FXML
    private javafx.scene.control.TextField location1;
    @FXML
    CheckBox box;
    @FXML
    ChoiceBox<String> cb;
    @FXML
    private javafx.scene.control.TextField namePro;
    @FXML
    private javafx.scene.control.Button add;
    @FXML
    private javafx.scene.control.TextField price;
    @FXML
    private javafx.scene.control.TextField loanDuration;

    public void setEmail(String email)
    {
        this.email=email;
    }

    public void addProduct() {

        String sNamePro = namePro.getText();
        String sLocation = location1.getText();
        String sPrice = price.getText();
        String sLoanDuration = loanDuration.getText();
        String sType = cb.getSelectionModel().getSelectedItem();
        String isExchange="0";
        if(box.isSelected()){
            isExchange="1";
        }
        if (sNamePro.equals("") || sLoanDuration.equals("") || sLocation.equals("") || sType.equals("") || sPrice.equals("")) {
            showErrorAlert("Error","Please fill all the fields","");

        }
        else{
            myDB.insertToProduct(email,sNamePro, sType,sLocation,sPrice,sLoanDuration,isExchange);

            Stage stage1 = (Stage) location1.getScene().getWindow();
            stage1.close();
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

    //  ChoiceBox cb1=new ChoiceBox(FXCollections.observableArrayList("1","2"));

}
