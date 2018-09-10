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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author nazmul
 */
public class CompareController implements Initializable {

    @FXML
    private Label label1Id;
    @FXML
    private Text label1Model;
    @FXML
    private Label label1Price;
    @FXML
    private Label label1Status;
    @FXML
    private Label label2Id;
    @FXML
    private Label label2Price;
    @FXML
    private Label label2Status;
    @FXML
    private Text label2Model;
    ObservableList<String> catagoryList;
    @FXML
    private ComboBox<String> brand1Combo;
    ObservableList<Product> filteredProductList;
    ObservableList<Product> productList;
    private int currentIndex;
    private int currentIndex1;
    @FXML
    private ComboBox<String> brand2Combo;
    @FXML
    private Button next1Field;
    @FXML
    private Button previous1Field;
    private int numberOfProduct = 0;
    private int numberOfProduct1 = 0;

    @FXML
    private Button previous2Field;
    @FXML
    private Button next2Field;
    @FXML
    private Label label1Compare;
    @FXML
    private Label label2Compare;
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
        currentIndex = 0;
        currentIndex1 = 0;
        label1Compare.setText("Please Select Any Product");
        productList = FXCollections.observableArrayList();
        catagoryList = FXCollections.observableArrayList();
        filteredProductList = FXCollections.observableArrayList();
        brand2Combo.setItems(catagoryList);
        brand1Combo.setItems(catagoryList);

        try {

            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM brand");

            while (resultSet.next()) {
                String brand = resultSet.getString("brandName");
                catagoryList.add(brand);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CompareController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void display() {
        int id = productList.get(currentIndex).getId();
        String model = productList.get(currentIndex).getModel();
        int price = productList.get(currentIndex).getPrice();
        String status = productList.get(currentIndex).getStatus();

        label1Id.setText(id + "");
        label1Model.setText(model);
        label1Price.setText(price + "");
        label1Status.setText(status);

    }

    public void display1() {
        int id = productList.get(currentIndex1).getId();
        String model = productList.get(currentIndex1).getModel();
        int price = productList.get(currentIndex1).getPrice();
        String status = productList.get(currentIndex1).getStatus();

        label2Id.setText(id + "");
        label2Model.setText(model);
        label2Price.setText(price + "");
        label2Status.setText(status);

    }

    @FXML
    private void handleBrand1ComboAction(ActionEvent event) {
        numberOfProduct = 0;
        productList.clear();
        next1Field.setDisable(false);

        currentIndex = 0;
        try {
            String catagory = brand1Combo.getSelectionModel().getSelectedItem();

            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement();
            String query = "SELECT * FROM product WHERE  brandName = '" + catagory + "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String model = resultSet.getString("model");
                int price = resultSet.getInt("price");
                String ProductCatagory = resultSet.getString("ProductCatagory");
                String status = resultSet.getString("status");
                String BrandName = resultSet.getString("BrandName");

                numberOfProduct++;
                Product product = new Product(id, model, price, ProductCatagory, status, BrandName);
                productList.add(product);
                label2Compare.setText("Please Select Other Product");
            }

            display();

        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }

    @FXML
    private void handleBrand2ComboAction(ActionEvent event) {

        numberOfProduct1 = 0;

        productList.clear();
        next2Field.setDisable(false);
        currentIndex1 = 0;
        try {
            String catagory = brand2Combo.getSelectionModel().getSelectedItem();

            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement();
            String query = "SELECT * FROM product WHERE  brandName = '" + catagory + "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String model = resultSet.getString("model");
                int price = resultSet.getInt("price");
                String ProductCatagory = resultSet.getString("ProductCatagory");
                String status = resultSet.getString("status");
                String BrandName = resultSet.getString("BrandName");

                numberOfProduct1++;
                Product product = new Product(id, model, price, ProductCatagory, status, BrandName);
                productList.add(product);

                label1Compare.setText("");
            }

            display1();
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }

    }

    @FXML
    private void handleNext1Action(ActionEvent event) {
        currentIndex++;
        previous1Field.setDisable(false);
        if (currentIndex == numberOfProduct - 1) {
            next1Field.setDisable(true);
        }
        display();
    }

    @FXML
    private void handlePrevious1Action(ActionEvent event) {
        currentIndex--;
        next1Field.setDisable(false);
        if (currentIndex == 0) {
            previous1Field.setDisable(true);
        }

        display();
    }

    @FXML
    private void handlePrevious2Action(ActionEvent event) {

        currentIndex1--;
        next2Field.setDisable(false);
        if (currentIndex1 == 0) {
            previous2Field.setDisable(true);
        }
        display1();
    }

    @FXML
    private void handleNext2Action(ActionEvent event) {

        currentIndex1++;
        previous2Field.setDisable(false);
        if (currentIndex1 == numberOfProduct1 - 1) {
            next2Field.setDisable(true);
        }
        display1();
    }

    @FXML
    private void handleBackAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        ComputerStore.getMainStage().setScene(scene);
        ComputerStore.getMainStage().show();
    }

}
