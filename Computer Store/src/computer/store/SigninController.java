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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class SigninController implements Initializable {

    @FXML
    private TextField userNameField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField userAddressField;
    @FXML
    private TextField userNumberField;
    @FXML
    private PasswordField userPasswordField;
    @FXML
    private TextField userIdField;
    @FXML
    private Label labelPassword;
    private Connection connection;
    private Statement statement;
    // private ResultSet resultSet;
    ////private  String query;
    private String DB_URL = "jdbc:mysql://127.0.0.1/productdb";
    private String DB_USERNAME = "root";
    private String DB_PASSWORD = "nazmul";
    private ObservableList<CustomerList> customerList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerList = FXCollections.observableArrayList();

    }

    @FXML
    private void handleSigninAction(ActionEvent event) throws IOException {

        int id = Integer.parseInt(userIdField.getText());

        String name = userNameField.getText();
        String address = userAddressField.getText();
        String password = userPasswordField.getText();

        String userName = nameField.getText();

        String cellNumber = userNumberField.getText();
        try {

            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO  customer VALUES(" + id + ", '" + name + "', '" + address + "', '" + password + "', '" + userName + "', '" + cellNumber + "')");

            CustomerList cl = new CustomerList(id, name, address, password, userName, cellNumber);
            customerList.add(cl);
            Parent root = FXMLLoader.load(getClass().getResource("ProductView.fxml"));

            Scene scene = new Scene(root);

            ComputerStore.getMainStage().setScene(scene);
            ComputerStore.getMainStage().show();

        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
        userIdField.clear();
        userNameField.clear();
        userPasswordField.clear();
        nameField.clear();
        userAddressField.clear();
        userNumberField.clear();
    }

    @FXML
    private void handleResetAction(ActionEvent event) {
        userIdField.clear();
        userNameField.clear();
        userPasswordField.clear();
        nameField.clear();
        userAddressField.clear();
        userNumberField.clear();
    }

    @FXML
    private void handleBackAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BuyProduct.fxml"));

        Scene scene = new Scene(root);

        ComputerStore.getMainStage().setScene(scene);
        ComputerStore.getMainStage().show();
    }

    @FXML
    private void handleCloseAction(ActionEvent event) {
        System.exit(0);
    }

}
