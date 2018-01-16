package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by בנואל on 15/01/2018.
 */
public class PackageCreate {
    String email;
    MsAccessDatabaseConnectionInJava8 myDB=new MsAccessDatabaseConnectionInJava8();

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
    @FXML
    private javafx.scene.control.TextField txt_toAdd;

    public void Add(ActionEvent actionEvent) {
        String toAdd=txt_toAdd.getText();
        String number=getNextNum();
        String []ProductsIds=toAdd.split(" ");
        for (int i = 0; i < ProductsIds.length; i++) {
            String productId = ProductsIds[i];
            String s="INSERT INTO Packages(ID,ProductID,Owner)"+"Values ('"+number+"'," + "'"+productId+"'," + "'"+email+"')";;

            try {
                myDB.statement.execute(s);
                showInfoAlert("Add package", "The Packge Created successful","");
                Stage stage1 = (Stage) txt_toAdd.getScene().getWindow();
                stage1.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public void previousWindow(ActionEvent actionEvent) {
        Stage stage1 = (Stage) txt_id.getScene().getWindow();
        stage1.close();
    }

    public void setEmail(String sEmail) {
        email=sEmail;
    }

    public void setText() {
        ResultSet resultSet = null;
        try {
            resultSet = myDB.statement.executeQuery("SELECT * FROM Product");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while(resultSet.next()) {
                if(email.equals(resultSet.getString(5))) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getNextNum(){
        ResultSet resultSet = null;
        String ans=null;
        try {
            resultSet = myDB.statement.executeQuery("SELECT * FROM Packages");
            while(resultSet.next()) {
                ans=resultSet.getString(3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(ans==null)
            return "1";
            int x=Integer.parseInt(ans);
            x++;
            ans=x+"";

          return ans;
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
