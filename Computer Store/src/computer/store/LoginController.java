/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computer.store;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author nazmul
 */
public class LoginController implements Initializable {

    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private Label labelAlert;
    private String DB_URL = "jdbc:mysql://127.0.0.1/productdb";
    private String DB_USERNAME = "root";
    private String DB_PASSWORD = "nazmul";
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleLoginAction(ActionEvent event) throws IOException {
        String userName2 = userNameField.getText();
        String password2 = loginPasswordField.getText();

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM customer WHERE userName = '" + userName2 + "' AND password = '" + password2 + "'");

            if (resultSet.next()) {
                Parent root = FXMLLoader.load(getClass().getResource("ProductView.fxml"));

                Scene scene = new Scene(root);

                ComputerStore.getMainStage().setScene(scene);
                ComputerStore.getMainStage().show();
                labelAlert.setText("Your Login Successfully");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Please Give an Right Password And User Name");
                alert.setContentText("Ooops, there was an error!");

                alert.showAndWait();
            }
            userNameField.clear();
            loginPasswordField.clear();
            labelAlert.setText("Your Login Failed");
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }

    @FXML
    private void handleResetAction(ActionEvent event) {
        userNameField.clear();
        loginPasswordField.clear();
    }

    @FXML
    private void handleBackAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BuyProduct.fxml"));

        Scene scene = new Scene(root);

        ComputerStore.getMainStage().setScene(scene);
        ComputerStore.getMainStage().show();
    }

}
