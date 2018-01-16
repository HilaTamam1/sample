package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by lenovo on 13/01/2018.
 */
public class Actions {
    @FXML
    private javafx.scene.control.Button search;

    String email;

    public void openSearchUser(ActionEvent actionEvent) {
        Stage stage1 = (Stage) search.getScene().getWindow();
        stage1.close();

        Stage stage2 = new Stage();
        stage2.setTitle("Search Product");
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("../sample/searchProductUser.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 450, 300);
        scene.getStylesheets().add(getClass().getResource("newUser.css").toExternalForm());

        SearchProductUser searchProductUser=fxmlLoader.getController();
        searchProductUser.setText(email);
        stage2.setScene(scene);
        stage2.show();
    }

    public void openCRUDProduct(ActionEvent actionEvent) {
        Stage stage1 = (Stage) search.getScene().getWindow();
        stage1.close();

        Stage stage2 = new Stage();
        stage2.setTitle("CRUDE Product");
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("../sample/CRUDproduct.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 600, 350);
        scene.getStylesheets().add(getClass().getResource("newUser.css").toExternalForm());

        CRUDproduct cruDproduct= fxmlLoader.getController();
        cruDproduct.setText(email);

        stage2.setScene(scene);
        stage2.show();
    }

    public void openMyInfo(ActionEvent actionEvent) {
        Stage stage1 = (Stage) search.getScene().getWindow();
        stage1.close();

        Stage stage2 = new Stage();
        stage2.setTitle("Option My Info");
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("../sample/optionMyInfo.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 450, 295);
        scene.getStylesheets().add(getClass().getResource("newUser.css").toExternalForm());

        OptionMyInfo optionMyInfo=fxmlLoader.getController();
        optionMyInfo.setText(email);

        stage2.setScene(scene);
        stage2.show();

    }

    public void setText(String text) {
        email = text;
    }

/*    public void openCRUDproduct() throws java.io.IOException {

        Stage stage1 = (Stage) email.getScene().getWindow();
        stage1.close();

        Stage stage2 = new Stage();
        stage2.setTitle("CRUDE Product");
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = fxmlLoader.load(getClass().getResource("../sample/CRUDproduct.fxml").openStream());

        Scene scene = new Scene(root, 600, 350);
        scene.getStylesheets().add(getClass().getResource("newUser.css").toExternalForm());

        CRUDproduct cruDproduct= fxmlLoader.getController();
        cruDproduct.setText(email.getText());

        stage2.setScene(scene);
        stage2.show();
    }*/
}
