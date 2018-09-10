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
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author nazmul
 */
public class ProductViewController implements Initializable {

    @FXML
    private TableView<Product> userProductTable;
    @FXML
    private TableColumn<Product, String> userBrandColumn;
    @FXML
    private TableColumn<Product, String> userModelColumn;
    @FXML
    private TableColumn<Product, Number> userPriceColumn;
    private ObservableList<Product> productList;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private int currentIndex;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentIndex = 0;
        productList = FXCollections.observableArrayList();
        userProductTable.setItems(productList);

        userBrandColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBrandName()));
        userModelColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getModel()));
        userPriceColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getPrice()));

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/productdb", "root", "nazmul");
            statement = connection.createStatement();

            String query = "SELECT * FROM product ";
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
        } catch (SQLException sqle) {

        }
    }

    @FXML
    private void handleUserClickedAction(MouseEvent event) {
        currentIndex = userProductTable.getSelectionModel().getSelectedIndex();
    }

    @FXML
    private void handleAddCartAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Successfully Completed");
//alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

            Scene scene = new Scene(root);

            ComputerStore.getMainStage().setScene(scene);
            ComputerStore.getMainStage().show();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

            Scene scene = new Scene(root);

            ComputerStore.getMainStage().setScene(scene);
            ComputerStore.getMainStage().show();
        }
    }

    @FXML
    private void handleBackAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDucument.fxml"));

        Scene scene = new Scene(root);

        ComputerStore.getMainStage().setScene(scene);
        ComputerStore.getMainStage().show();
    }

}
