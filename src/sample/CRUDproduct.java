package sample;

import javafx.collections.FXCollections;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lenovo on 26/12/2017.
 */
public class CRUDproduct {
    MsAccessDatabaseConnectionInJava8 myDB=new MsAccessDatabaseConnectionInJava8();

    @FXML
    private javafx.scene.control.Button create;


    String sEmail;
    @FXML
    public void addProduct(){
        openCreateProduct();
    }

    public void setText(String email)
    {
        sEmail=email;
    }
    private void openCreateProduct()  {


        Stage stage2 = new Stage();
        stage2.setTitle("Add product");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("../sample/createProduct.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 480, 350);
        scene.getStylesheets().add(getClass().getResource("newUser.css").toExternalForm());
        stage2.setScene(scene);
        CreateProduct createProduct= fxmlLoader.getController();
        createProduct.setEmail(sEmail);
        stage2.show();
    }

    @FXML
    private void openDisplayProduct()  {


        Stage stage2 = new Stage();
        stage2.setTitle("Display my products");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("../sample/DisplayProduct.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1000, 500);
        scene.getStylesheets().add(getClass().getResource("display.css").toExternalForm());

/*
        scene.getStylesheets().add(getClass().getResource("newUser.css").toExternalForm());
*/
        stage2.setScene(scene);

        DisplayProduct displayProduct= fxmlLoader.getController();
        displayProduct.setEmail(sEmail);
        displayProduct.setText();


        stage2.show();
    }
    @FXML
    private void deleteProduct()  {


        Stage stage2 = new Stage();
        stage2.setTitle("Delete my product");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("../sample/DeleteProduct.fxml").openStream());


        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1000, 500);
        scene.getStylesheets().add(getClass().getResource("delete.css").toExternalForm());
/*
        scene.getStylesheets().add(getClass().getResource("newUser.css").toExternalForm());
*/
        stage2.setScene(scene);

        DeleteProduct deleteProduct= fxmlLoader.getController();
        deleteProduct.setEmail(sEmail);
        deleteProduct.setText();


        stage2.show();
    }
    @FXML
    public void updateProduct(){
        Stage stage2 = new Stage();
        stage2.setTitle("Update Product");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("../sample/UpdateProduct.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1000, 500);
        scene.getStylesheets().add(getClass().getResource("update.css").toExternalForm());
/*
        scene.getStylesheets().add(getClass().getResource("newUser.css").toExternalForm());
*/
        stage2.setScene(scene);

        UpdateProduct updateProduct= fxmlLoader.getController();
        updateProduct.setEmail(sEmail);
        updateProduct.setText();


        stage2.show();
    }

    public void showErrorAlert(String s1, String s2,String s3)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(s1);
        alert.setHeaderText(s2);
        alert.setContentText(s3);
        alert.show();
    }

    public void toThePre(ActionEvent actionEvent) {
        Stage stage1 = (Stage) create.getScene().getWindow();
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
        actions.setText(sEmail);

        stage2.setScene(scene);
        stage2.show();

    }
    @FXML
    private void openCreatePackage()  {


        Stage stage2 = new Stage();
        stage2.setTitle("Add Package");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("../sample/PackageCreate.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1000, 500);
        scene.getStylesheets().add(getClass().getResource("newUser.css").toExternalForm());
        stage2.setScene(scene);
        PackageCreate packageCreate= fxmlLoader.getController();
        packageCreate.setEmail(sEmail);
        packageCreate.setText();
        stage2.show();
    }

}
