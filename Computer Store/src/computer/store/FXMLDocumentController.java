/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computer.store;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author nazmul
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ComboBox<String> componentCombo;
    ObservableList<String> catagoryList;
    ObservableList<Product> productList;
    ObservableList<Product> filteredProductList;

    @FXML
    private TableView<Product> detailsProductView;
    @FXML
    private TableColumn<Product, Number> idColumn;
    @FXML
    private TableColumn<Product, String> modelColumn;
    @FXML
    private TableColumn<Product, Number> priceColumn;
    @FXML
    private TableColumn<Product, String> statusColumn;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String catagory;
    @FXML
    private TextField searchField;

    private int currentIndex;

    private TableColumn<Product, String> productCatagoryColumn;
    @FXML
    private ListView<String> listCatagoryView;
    private String DB_URL = "jdbc:mysql://127.0.0.1/productdb";
    private String DB_USERNAME = "root";
    private String DB_PASSWORD = "nazmul";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentIndex = 0;
        catagoryList = FXCollections.observableArrayList();
        filteredProductList = FXCollections.observableArrayList();
        productList = FXCollections.observableArrayList();
        componentCombo.setItems(catagoryList);

        detailsProductView.setItems(filteredProductList);
        listCatagoryView.setItems(catagoryList);

        idColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()));
        modelColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getModel()));
        priceColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getPrice()));
        statusColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus()));

        FilteredList<Product> filteredList = new FilteredList(productList, e -> true);
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredList.setPredicate(product -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (product.getBrandName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    if (product.getProductCatagory().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    if (product.getModel().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false;
                });
            });

            SortedList<Product> sortedData = new SortedList<>(filteredList);
            sortedData.comparatorProperty().bind(detailsProductView.comparatorProperty());
            detailsProductView.setItems(sortedData);

        });

        try {

            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement();
            String query = "SELECT * FROM component";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                catagory = resultSet.getString("catagory");
                catagoryList.add(catagory);
            }

            query = "SELECT * FROM product ";
            resultSet = statement.executeQuery(query);
            productList.clear();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String model = resultSet.getString("model");
                int price = resultSet.getInt("price");
                String ProductCatagory = resultSet.getString("ProductCatagory");
                String status = resultSet.getString("status");
                String BrandName = resultSet.getString("BrandName");

                Product product = new Product(id, model, price, ProductCatagory, status, BrandName);
                productList.add(product);
            }
            filteredProductList.addAll(productList);

        } catch (SQLException sqle) {
            System.err.println(sqle);

        }
    }

    @FXML
    private void handleComponentComboAction(ActionEvent event) {

        String catagory = componentCombo.getSelectionModel().getSelectedItem();

        filteredProductList.clear();
        for (Product product : productList) {
            if (product.getProductCatagory().equals(catagory)) {
                filteredProductList.add(product);
            }
        }
        detailsProductView.setItems(filteredProductList);
    }

    @FXML
    private void handleCompareAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Compare.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleManagerLoginAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Managerlogin.fxml"));

        Scene scene = new Scene(root);

        ComputerStore.getMainStage().setScene(scene);
        ComputerStore.getMainStage().show();
    }

    @FXML
    private void handleListViewAction(MouseEvent event) {
        String catagory = listCatagoryView.getSelectionModel().getSelectedItem();

        filteredProductList.clear();
        for (Product product : productList) {
            if (product.getProductCatagory().equals(catagory)) {
                filteredProductList.add(product);
            }
        }
        detailsProductView.setItems(filteredProductList);
    }

    @FXML
    private void handleBuyAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BuyProduct.fxml"));

        Scene scene = new Scene(root);

        ComputerStore.getMainStage().setScene(scene);
        ComputerStore.getMainStage().show();
    }

    @FXML
    private void handleKeyReleasedAction(KeyEvent event) {
    }

    @FXML
    private void handleKeySearchAction(KeyEvent event) {

    }

    @FXML
    private void handleShowDataAction(ActionEvent event) {
        filteredProductList.setAll(productList);
        
    }

    @FXML
    private void handleClearAction(ActionEvent event) {
        filteredProductList.clear();

    }

    @FXML
    private void handleCloseAction(ActionEvent event) {
        System.exit(0);
    }

}
