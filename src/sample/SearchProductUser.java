package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by lenovo on 13/01/2018.
 */
public class SearchProductUser {
    @FXML
    ChoiceBox<String> cb;
    String email;


    public void searchProductUser(ActionEvent actionEvent) {
   //     Stage stage1 = (Stage) search.getScene().getWindow();
  //      stage1.close();

        Stage stage2 = new Stage();
        stage2.setTitle("order");
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("../sample/order.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 1000, 500);
        scene.getStylesheets().add(getClass().getResource("delete.css").toExternalForm());

        Order order=fxmlLoader.getController();
        order.setType(cb.getSelectionModel().getSelectedItem());
        order.setEmail(email);
        order.setText();

        //    CRUDproduct cruDproduct= fxmlLoader.getController();
        //    cruDproduct.setText(email);

        stage2.setScene(scene);
        stage2.show();
    }

    public void toThePre(ActionEvent actionEvent) {
        Stage stage1 = (Stage) cb.getScene().getWindow();
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

    public void setText(String text) {
        this.email = text;
    }
}
