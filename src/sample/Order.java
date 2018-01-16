package sample;

import com.sun.org.apache.regexp.internal.RE;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * Created by Eden on 13/01/2018.
 */
public class Order {
    String email,type;
    MsAccessDatabaseConnectionInJava8 myDB=new MsAccessDatabaseConnectionInJava8();
    MsAccessDatabaseConnectionInJava8 myDB1=new MsAccessDatabaseConnectionInJava8();
    String tomail="";
    @FXML
    private javafx.scene.control.TextField txt_toOrder;
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


    public void previousWindow()
    {
        Stage stage1 = (Stage) txt_toOrder.getScene().getWindow();
        stage1.close();
    }
    public void exchange (ActionEvent actionEvent ){
        String id=txt_toOrder.getText();

        if(id.equals("")){
            showErrorAlert("", "You can't exchange this product", "");
        }
        else {

            try {
                ResultSet resultSet = myDB.statement.executeQuery("SELECT * FROM Packages");
                while (resultSet.next()) {
                    if (resultSet.getString(3).equals(id)) {
                        tomail = resultSet.getString(2);

                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String sq1 = "UPDATE Packages SET " + "Loaner" + " = '" + email + "' Where id" + " = " + id;

            try {

                myDB.statement.executeUpdate(sq1);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            Stage stage1 = (Stage) txt_toOrder.getScene().getWindow();
            stage1.close();

            Stage stage2 = new Stage();
            stage2.setTitle("Choose product to exchange with");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = null;
            try {
                root = fxmlLoader.load(getClass().getResource("../sample/myProductToExchange.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root, 1000, 500);
            scene.getStylesheets().add(getClass().getResource("display.css").toExternalForm());
            stage2.setScene(scene);
            MyProductToExchange myProductToExchange = fxmlLoader.getController();
            myProductToExchange.setEmail(email);
            myProductToExchange.setId(id);
            myProductToExchange.setText();
            myProductToExchange.setEx(tomail);
            stage2.show();
        }
    }

    public void setEmail(String mail){
        email=mail;
    }
    public void rent(ActionEvent actionEvent) {
        String id=txt_toOrder.getText();

        if(id.equals("")){
            showErrorAlert("", "You can't loan this product", "");
        }
        else {
            String sq1="UPDATE Packages SET " + "Loaner" + " = '" + email + "' Where id" + " = " + id;

            try {

                myDB.statement.executeUpdate(sq1);
                showInfoAlert("Loan Product","The product was loaned","");
                Stage stage1 = (Stage) txt_toOrder.getScene().getWindow();
                stage1.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

   public void setText(){
        ResultSet resultSet = null;
        ResultSet resultSetPack = null;
        ResultSet resultSetPack2 = null;
       HashSet<String> packages=new HashSet<>();
        boolean found=false;
        try {
            resultSet = myDB.statement.executeQuery("SELECT * FROM Product");
            resultSetPack = myDB.statement.executeQuery("SELECT * FROM Packages");
            resultSetPack2 = myDB.statement.executeQuery("SELECT * FROM Packages");
        } catch (SQLException e) {
            e.printStackTrace();
        }

       try {
            String PackId="";
           while(resultSet.next()) {
               if(type.equals(resultSet.getString(1))) {

                    while (resultSetPack.next()){
                        if(resultSetPack.getString(1).equals(resultSet.getString(7))){
                            PackId=resultSetPack.getString(3);
                            if(!packages.contains(PackId)) {
                                found = true;
                                packages.add(PackId);
                            }
                            break;
                        }
                    }
                   resultSetPack=myDB.statement.executeQuery("SELECT * FROM Packages");
                   resultSetPack2=myDB.statement.executeQuery("SELECT * FROM Packages");
                   while (resultSetPack2.next()&&found) {
                       if(resultSetPack2.getString(3).equals(PackId)){
                                ResultSet  rs = myDB.statement.executeQuery("SELECT * FROM Product");
                                while(rs.next()){
                                if((rs.getString(7).equals(resultSetPack2.getString(1)))&&resultSetPack2.getString(4).equals("5")) {
                                    txt_name.setText(txt_name.getText() + rs.getString(6) + "\n");
                                    txt_id.setText(txt_id.getText() + PackId + "\n");
                                    txt_type.setText(txt_type.getText() + rs.getString(1) + "\n");
                                    txt_location.setText(txt_location.getText() + rs.getString(2) + "\n");
                                    txt_loanDuration.setText(txt_loanDuration.getText() + rs.getString(4) + "\n");
                                    txt_price.setText(txt_price.getText() + rs.getString(3) + "\n");
                                    txt_exchangable.setText(txt_exchangable.getText() + rs.getString(9) + "\n");
                                    txt_rentable.setText(txt_rentable.getText() + rs.getString(8) + "\n");
                                    txt_ownerEmail.setText(txt_ownerEmail.getText() + rs.getString(5) + "\n");
                                }
                                }

                       }
                   }
                   found=false;

               }
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setType(String Type) {
        this.type = Type;
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
