package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
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


    public static void main(String[] args) {
        launch(args);
    }
}
