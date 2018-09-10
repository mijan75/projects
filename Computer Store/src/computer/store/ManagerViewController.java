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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author nazmul
 */
public class ManagerViewController implements Initializable {

    @FXML
    private TableView<Product> managerTableView;
    @FXML
    private TableColumn<Product, Number> managerIdColumn;
    @FXML
    private TableColumn<Product, String> managerModelColumn;
    @FXML
    private TableColumn<Product, Number> managerPriceColumn;
    @FXML
    private TableColumn<Product, String> managerCatagoryColumn;
    @FXML
    private TableColumn<Product, String> managerSattusColumn;
    @FXML
    private TableColumn<Product, String> brandColoumn;
    private ObservableList<Product> productList;
    private ObservableList<String> catagoryList;
    @FXML
    private TextField statusField;
    @FXML
    private ComboBox<String> catagoryCombo;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String query;
    private String DB_URL = "jdbc:mysql://127.0.0.1/productdb";
    private String DB_USERNAME = "root";
    private String DB_PASSWORD = "nazmul";
    @FXML
    private TextField idField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField priceField;
    private int currentIndex;
    @FXML
    private TextField brandField;
    @FXML
    private Label successfullyLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentIndex = 0;

        productList = FXCollections.observableArrayList();
        managerTableView.setItems(productList);
        catagoryList = FXCollections.observableArrayList();
        catagoryCombo.setItems(catagoryList);

        managerIdColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()));
        managerModelColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getModel()));
        managerPriceColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getPrice()));
        managerCatagoryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProductCatagory()));
        managerSattusColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus()));
        brandColoumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBrandName()));
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement();
            query = "SELECT * FROM product";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String model = resultSet.getString("model");
                int price = resultSet.getInt("price");
                String ProductCatagory = resultSet.getString("ProductCatagory");
                String status = resultSet.getString("status");
                String brandName = resultSet.getString("BrandName");

                Product product = new Product(id, model, price, ProductCatagory, status, brandName);
                productList.add(product);

            }
            query = "SELECT * FROM component";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String catagory = resultSet.getString("catagory");
                catagoryList.add(catagory);
            }

        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }

    @FXML
    private void handleCustomerListAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("CustomerList.fxml"));

        Scene scene = new Scene(root);

        ComputerStore.getMainStage().setScene(scene);
        ComputerStore.getMainStage().show();
    }

    public void display() {
        int id = productList.get(currentIndex).getId();
        String model = productList.get(currentIndex).getModel();
        int price = productList.get(currentIndex).getPrice();
        String ProductCatagory = productList.get(currentIndex).getProductCatagory();
        String status = productList.get(currentIndex).getStatus();
        String brand = productList.get(currentIndex).getBrandName();

        idField.setText(id + "");
        modelField.setText(model);
        priceField.setText(price + "");
        catagoryCombo.getSelectionModel().clearAndSelect(currentIndex);
        statusField.setText(status);
        brandField.setText(brand);
    }

    @FXML
    private void handleCatagoryAction(ActionEvent event) {

    }

    @FXML
    private void handleAddAction(ActionEvent event) {
        int id;
        try {
            id = Integer.parseInt(idField.getText());
        } catch (NumberFormatException nfe) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("Incorrect Product Id");
            alert.setContentText("Product Id has to be an integer");
            alert.showAndWait();
            return;
        }

        String model = modelField.getText();
        int price;
        try {
            price = Integer.parseInt(priceField.getText());
        } catch (NumberFormatException nfe) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("Incorrect Product Price Value");
            alert.setContentText("Product Price has to be an integer");
            alert.showAndWait();
            return;
        }

        String ProductCatagory = catagoryCombo.getSelectionModel().getSelectedItem();
        String status = statusField.getText();
        String BrandName = brandField.getText();

        try {
            connection = DriverManager
                    .getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO product VALUES(" + id + ", '" + model + "', '" + price + "', '" + ProductCatagory + "', '" + status + "', '" + BrandName + "')");
            Product product = new Product(id, model, price, ProductCatagory, status, BrandName);
            productList.add(product);
            successfullyLabel.setText("New Product Added Successfully");
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
        idField.clear();
        modelField.clear();
        priceField.clear();
        statusField.clear();
        brandField.clear();

    }

    @FXML
    private void handleResetACtion(ActionEvent event) {
        idField.clear();
        modelField.clear();
        priceField.clear();
        statusField.clear();
        brandField.clear();
    }

    @FXML
    private void handleDeleteAction(ActionEvent event) {

        String model = productList.get(currentIndex).getModel();
        try {
            connection = DriverManager
                    .getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement();
            query = "DELETE FROM product WHERE model = '" + model + "'";
            int resultSet = statement.executeUpdate(query);

            productList.remove(currentIndex);
        } catch (SQLException sqle) {

        }
        idField.clear();
        modelField.clear();
        priceField.clear();
        statusField.clear();
        brandField.clear();
        successfullyLabel.setText("Existing Product Deleted Successfully");
    }

    @FXML
    private void handleTableViewClickedAction(MouseEvent event) {
        currentIndex = managerTableView.getSelectionModel().getSelectedIndex();
        display();

    }

    @FXML
    private void handleLogOutAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        ComputerStore.getMainStage().setScene(scene);
        ComputerStore.getMainStage().show();
    }

    @FXML
    private void handleCloseAction(ActionEvent event) {
        System.exit(0);
    }

}
