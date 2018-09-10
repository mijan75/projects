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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author nazmul
 */
public class CustomerListController implements Initializable {

    @FXML
    private TableView<CustomerList> customerTableView;
    @FXML
    private TableColumn<CustomerList, Number> customeridColumn;
    @FXML
    private TableColumn<CustomerList, String> customerNameColumn;
    @FXML
    private TableColumn<CustomerList, String> customerAddresscolumn;
    @FXML
    private TableColumn<CustomerList, String> CustomerNumberColumn;
    private ObservableList<CustomerList> customerList;

    private String DB_URL = "jdbc:mysql://127.0.0.1/productdb";
    private String DB_USERNAME = "root";
    private String DB_PASSWORD = "nazmul";
    @FXML
    private TableColumn<CustomerList, String> passwordColumn;
    @FXML
    private TableColumn<CustomerList, String> userNameColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerList = FXCollections.observableArrayList();
        customerTableView.setItems(customerList);

        customeridColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()));
        customerNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        customerAddresscolumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress()));
        passwordColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPassword()));
        userNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUserName()));
        CustomerNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCellNumber()));
        try {

            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM customer";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String password = resultSet.getString("password");
                String userName = resultSet.getString("userName");
                String phoneNumber = resultSet.getString("cellNumber");

                CustomerList cl = new CustomerList(id, name, address, password, userName, phoneNumber);
                customerList.add(cl);
            }
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }

    @FXML
    private void handleBackACtion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ManagerView.fxml"));

        Scene scene = new Scene(root);

        ComputerStore.getMainStage().setScene(scene);
        ComputerStore.getMainStage().show();
    }

    @FXML
    private void handleLogOutAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        ComputerStore.getMainStage().setScene(scene);
        ComputerStore.getMainStage().show();

    }

}
