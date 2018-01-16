package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class OptionMyInfo {
    @FXML
    private javafx.scene.control.Button preBtn;

    String email;
    public void deleteUser(ActionEvent actionEvent) {

    }

    public void openDisplayDetail(ActionEvent actionEvent) {
        Stage stage2 = new Stage();
        stage2.setTitle("Update User");
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("../sample/displayUser1.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 550, 490);
        scene.getStylesheets().add(getClass().getResource("newUser.css").toExternalForm());

        DisplayUser displayUser=fxmlLoader.getController();
        displayUser.setEmail(email);
        displayUser.setText();

        stage2.setScene(scene);
        stage2.show();
    }

    public void setText(String email)
    {
        this.email=email;
    }

    public void previousWindow()
    {
        Stage stage1 = (Stage) preBtn.getScene().getWindow();
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
        actions.setText(email);

        stage2.setScene(scene);
        stage2.show();
    }

    public void DisplayLoanProduct(ActionEvent actionEvent) {

        Stage stage2 = new Stage();
        stage2.setTitle("Display my loan");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("../sample/DisplayMyLoan.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 725, 400);
        scene.getStylesheets().add(getClass().getResource("display.css").toExternalForm());
        stage2.setScene(scene);

        DisplayMyLoan displayMyLoan=fxmlLoader.getController();
        displayMyLoan.setMail(email);
        displayMyLoan.setText();


        stage2.show();
    }
}
