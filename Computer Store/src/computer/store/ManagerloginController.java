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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author nazmul
 */
public class ManagerloginController implements Initializable {

    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleLoginAction(ActionEvent event) throws IOException {

        try {
            String userName1 = userNameField.getText();
            String password1 = passwordField.getText();
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/productdb", "root", "nazmul");
            Statement statement = connection.createStatement();
            String Query = "SELECT * FROM managarlogin where userName = '" + userName1 + "' AND password = '" + password1 + "'";

            ResultSet resultSet = statement.executeQuery(Query);

            if (resultSet.next()) {
                Parent root = FXMLLoader.load(getClass().getResource("ManagerView.fxml"));

                Scene scene = new Scene(root);

                ComputerStore.getMainStage().setScene(scene);
                ComputerStore.getMainStage().show();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Please Give an Right Password And User Name");
                alert.setContentText("Ooops, there was an error!");

                alert.showAndWait();
            }

        } catch (SQLException sqle) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Please Give an Right Password And User Name");
            alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();
        }

    }

    @FXML
    private void handleResetAction(ActionEvent event) {
        userNameField.clear();
        passwordField.clear();
    }

    @FXML
    private void handleBackACtion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        ComputerStore.getMainStage().setScene(scene);
        ComputerStore.getMainStage().show();
    }

}
