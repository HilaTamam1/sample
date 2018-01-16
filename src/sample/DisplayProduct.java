package sample;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lenovo on 28/12/2017.
 */
public class DisplayProduct {

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
    public void setEmail(String email) {
        this.email = email;
    }
    public void previousWindow()
    {
        Stage stage1 = (Stage) txt_name.getScene().getWindow();
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
}
