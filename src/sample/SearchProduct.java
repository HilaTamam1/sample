package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SearchProduct {
    String email;
    String type;
    MsAccessDatabaseConnectionInJava8 myDB=new MsAccessDatabaseConnectionInJava8();
    @FXML
    ChoiceBox<String> cb;
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
    public void setEmail(String email) {
        this.email = email;
    }
    public void setType(String Type) {
        this.type = Type;
    }
    public void previousWindow()
    {
        Stage stage1 = (Stage) txt_name.getScene().getWindow();
        stage1.close();
    }
    public void searchProduct(ActionEvent actionEvent) {
        Stage stage2 = new Stage();
        stage2.setTitle("Display Search Results");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("../sample/SearchResults.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1000, 500);
        scene.getStylesheets().add(getClass().getResource("display.css").toExternalForm());

/*
        scene.getStylesheets().add(getClass().getResource("newUser.css").toExternalForm());
*/
        stage2.setScene(scene);

        SearchResults searchResults = fxmlLoader.getController();
        searchResults.setType(cb.getSelectionModel().getSelectedItem());
        searchResults.setText();


        stage2.show();
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
                if(type.equals(resultSet.getString(1))) {
                    txt_name.setText(txt_name.getText()+resultSet.getString(6) + "\n");
                    txt_id.setText(txt_id.getText()+resultSet.getString(7) + "\n");
                    txt_type.setText(txt_type.getText()+resultSet.getString(1) + "\n");
                    txt_location.setText(txt_location.getText()+resultSet.getString(2) + "\n");
                    txt_loanDuration.setText(txt_loanDuration.getText()+resultSet.getString(4) + "\n");
                    txt_price.setText(txt_price.getText()+resultSet.getString(3) + "\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public void toThePre(ActionEvent actionEvent) {
        Stage stage1 = (Stage) cb.getScene().getWindow();
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
