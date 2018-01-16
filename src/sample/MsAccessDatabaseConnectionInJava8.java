package sample;
import javafx.scene.control.Alert;
import org.apache.commons.lang.ObjectUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MsAccessDatabaseConnectionInJava8 {
    Statement statement;
    Connection connection;
    public MsAccessDatabaseConnectionInJava8() {
        // variables
         connection = null;
        statement = null;

        // Step 1: Loading or registering Oracle JDBC driver class
        try {

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) {

            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }
        // Step 2: Opening database connection
        try {
            String path= System.getProperty("user.dir");
            String msAccDB = path+ "\\db.accdb";
            String dbURL = "jdbc:ucanaccess://" + msAccDB;

            // Step 2.A: Create and get connection using DriverManager class
            connection = DriverManager.getConnection(dbURL);

            // Step 2.B: Creating JDBC Statement
            statement = connection.createStatement();
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
    }

    public void insertToUsers(String name, String lastName, String email1, String pass,String phoneNumber,String owner) {
        String sql = "INSERT INTO Users(Name1,lastName,email,password,phoneNumber,owner) " +"Values ('"+name+"'," +"'"+lastName+"'," +"'"+email1+"'," +"'"+pass+"'," +"'"+phoneNumber+"'," +"'"+owner+"')";
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertToProduct(String ownerName,String namePro, String type1, String location, String price,String loanDuration,String exchange) {
        String sql = "INSERT INTO Product(ownerName,nameProduct,type1,location,price,loanDuration,exchange,rentable) " +"Values ('"+ownerName+"',"
                +"'"+namePro+"',"
                +"'"+type1+"',"
                +"'"+location+"',"
                +"'"+price+"',"
                +"'"+loanDuration+"',"
                + "'"+exchange+"',"
                + "'"+"1"+"')";
        showErrorAlert("Create Product","","The product was added") ;

        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showErrorAlert(String s1, String s2,String s3)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(s2);
        alert.setTitle(s1);
        alert.setContentText(s3);
        alert.show();
    }
}