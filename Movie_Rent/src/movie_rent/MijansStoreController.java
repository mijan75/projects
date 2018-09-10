/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_rent;

import Entity.Cleark;
import Entity.Customer;
import Entity.MovieDetails;
import Entity.MovieSale;
import Entity.OfferedMovie;
import Entity.Price;
import Entity.StoreDetails;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import static java.awt.AlphaComposite.Clear;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import static movie_rent.FXMLDocumentController.loggedUsername;
import static movie_rent.Movie_Rent.changeScene;
import static movie_rent.Movie_Rent.session;
import static movie_rent.Movie_Rent.transaction;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MijansStoreController implements Initializable {

    @FXML
    private TableView<OfferedMovie> movieListTable;
    @FXML
    private TableColumn<OfferedMovie, String> movieNameView;

    private static int currentIndex;
    @FXML
    private Label storeNameLabel;
    @FXML
    private Label storeCityLabel;
    private Label storeNoLabel;
    @FXML
    private Label storeDateLabel;
    private ObservableList<OfferedMovie> offeredMovieList;
    private ObservableList<StoreDetails> storeList;
    @FXML
    private TableColumn<OfferedMovie, String> disNameView;
    @FXML
    private TableColumn<OfferedMovie, String> buyDateView;
    @FXML
    private TableColumn<Cleark, String> dateView;
    @FXML
    private TableView<Cleark> clearkTable;
    @FXML
    private TableColumn<Cleark, String> clearkNameView;
    @FXML
    private TableColumn<Cleark, String> cCityView;
    @FXML
    private TableColumn<Cleark, String> cNumberView;
    private ObservableList<Cleark> clearkList;
    private ObservableList<Price> priceList;

    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> customerNameView;
    @FXML
    private TableColumn<Customer, String> customeraddressView;
    @FXML
    private TableColumn<Customer, String> customerNumberView;

    private ObservableList<Customer> customerList;
    private ObservableList<MovieSale> movieSale;
    private TableColumn<Customer, String> priceView;
    @FXML
    private JFXTextField customerNameField;
    @FXML
    private JFXTextField customerAddressField;
    @FXML
    private JFXTextField customerNumberField;
    @FXML
    private JFXComboBox<OfferedMovie> movieListCombo;
    @FXML
    private JFXComboBox<Cleark> clearkListCombo;
    @FXML
    private JFXComboBox<Price> priceListCombo;
    @FXML
    private DatePicker saleDate;
    @FXML
    private JFXComboBox<Customer> customerListCombo;
    @FXML
    private TableView<MovieSale> saleTableView;
    @FXML
    private TableColumn<MovieSale, String> customerView;
    @FXML
    private TableColumn<MovieSale, String> movieView;
    @FXML
    private TableColumn<MovieSale, String> clearkView;
    @FXML
    private TableColumn<MovieSale, String> priceListView;
    @FXML
    private TableColumn<MovieSale, String> saleDateView;
    public static StoreDetails loggedStoreUser;
    @FXML
    private JFXTextField contractNoField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        currentIndex = 0;
        offeredMovieList = FXCollections.observableArrayList();
        storeList = FXCollections.observableArrayList();
        clearkList = FXCollections.observableArrayList();
        customerList = FXCollections.observableArrayList();
        priceList = FXCollections.observableArrayList();
        movieSale = FXCollections.observableArrayList();

        movieListCombo.setItems(offeredMovieList);
        clearkListCombo.setItems(clearkList);
        customerTable.setItems(customerList);
        customerListCombo.setItems(customerList);
        saleTableView.setItems(movieSale);

        movieNameView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMv() + ""));
        disNameView.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getDistributor() + ""));
        buyDateView.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getDate()));

        clearkNameView.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getClearkName()));
        cCityView.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getClearkCity()));
        cNumberView.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getClearkNo()));
        dateView.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getClearkEntryDate()));

        customerNameView.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getCustomerName()));
        customeraddressView.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getCustomerAddress()));
        customerNumberView.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getContractNo()));

        movieView.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getOm() + ""));
        clearkView.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getCleark() + ""));
        customerView.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getCustomer() + ""));
        priceListView.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getPrice() + ""));
        saleDateView.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getDate()));

        try {
            session = SingleFactory.getSessionFactory().openSession();
            session.beginTransaction();
            List<Price> price = session.createCriteria(Price.class).list();

            priceList.addAll(price);
            priceListCombo.setItems(priceList);
        } catch (Exception e) {
        }

        Session session = null;
        Transaction transaction = null;
        StoreDetails loggedStore = null;
        try {
            session = SingleFactory.getSessionFactory().openSession();
            System.err.println(loggedUsername);
            session.beginTransaction();
            Criteria criteria = session.createCriteria(StoreDetails.class);
            criteria.add(Restrictions.eq("storeUserName", loggedUsername));
            List<StoreDetails> stores = (List<StoreDetails>) criteria.list();

            for (StoreDetails store : stores) {
                loggedStore = store;

            }

            List<OfferedMovie> log = session.createCriteria(OfferedMovie.class).list();
            List<Cleark> log1 = session.createCriteria(Cleark.class).list();

            for (OfferedMovie offeredMovie : log) {
                Set<StoreDetails> sd = offeredMovie.getSd();
                for (StoreDetails storeDetails : sd) {
                    if (storeDetails.getStoreUserName().equals(loggedStoreUser.getStoreUserName())) {
                        offeredMovieList.add(offeredMovie);
                        movieListTable.setItems(offeredMovieList);

                    }

                }
            }

            for (Cleark cleark : log1) {
                Set<StoreDetails> sd = cleark.getSd();
                for (StoreDetails storeDetails : sd) {
                    if (storeDetails.getStoreUserName().equals(loggedStoreUser.getStoreUserName())) {
                        clearkList.add(cleark);
                        clearkTable.setItems(clearkList);
                    }
                }
            }

            String name = stores.get(currentIndex).getStoreName();
            String city = stores.get(currentIndex).getStoreCity();
            String number = stores.get(currentIndex).getContractNo();
            String date = stores.get(currentIndex).getEstaDate();

            storeNameLabel.setText(name);
            storeCityLabel.setText(city);
            contractNoField.setText(number);
            storeDateLabel.setText(date);

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

       
        // Movie Sale List  
        try {

            session = SingleFactory.getSessionFactory().openSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(MovieSale.class);

            List<MovieSale> log4 = session.createCriteria(MovieSale.class).list();
            List<MovieSale> CmovieSale = new ArrayList<>();
            for (MovieSale movieSale1 : log4) {
                Set<StoreDetails> sd = movieSale1.getSd();
                for (StoreDetails storeDetails : sd) {
                    if (storeDetails.getStoreUserName().equals(loggedStoreUser.getStoreUserName())) {
                        CmovieSale.add(movieSale1);

                    }

                }
            }

            movieSale.addAll(CmovieSale);
            saleTableView.setItems(movieSale);

        } catch (Exception e) {
            System.err.println(e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        try {

            session = SingleFactory.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Customer.class);

            List<Customer> log4 = session.createCriteria(Customer.class).list();
            List<Customer> customerMovie = new ArrayList<>();

            for (Customer customer : log4) {
                Set<StoreDetails> sd = customer.getSd();
                for (StoreDetails storeDetails : sd) {
                    if (storeDetails.getStoreUserName().equals(loggedStoreUser.getStoreUserName())) {
                        customerMovie.add(customer);

                    }
                }
            }

            customerList.addAll(customerMovie);
            customerTable.setItems(customerList);
            customerListCombo.setItems(customerList);

        } catch (Exception e) {
            System.err.println(e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

    }

    @FXML
    private void handleLogoutAction(ActionEvent event) {
        changeScene("FXMLDocument.fxml");
        loggedUsername = null;
        loggedStoreUser = null;

    }

    @FXML
    private void handleSubmitAction(ActionEvent event) {
        String Cname = customerNameField.getText();
        String Caddress = customerAddressField.getText();
        String Cnumber = customerNumberField.getText();
        String date = saleDate.getEditor().getText();

        Set<StoreDetails> sd = new HashSet<>();
        sd.add(loggedStoreUser);

        Customer customer = new Customer(0, Cname, Caddress, Cnumber, sd);
        Session session = null;
        Transaction transaction = null;
        try {
            session = SingleFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(customer);
            transaction.commit();
            customerList.add(customer);

            customerNameField.clear();
            customerAddressField.clear();
            customerNumberField.clear();

            customerTable.setItems(customerList);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText("Information Added");
            alert.setContentText("Successfully Added Your Information");
            alert.showAndWait();
            return;
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    private void handleSaveAction(ActionEvent event) {
        OfferedMovie md = movieListCombo.getSelectionModel().getSelectedItem();
        Set<OfferedMovie> movie = new HashSet<>();
        movie.add(md);

        Customer c = customerListCombo.getSelectionModel().getSelectedItem();
        Set<Customer> customer = new HashSet<>();
        customer.add(c);

        Cleark cl = clearkListCombo.getSelectionModel().getSelectedItem();
        Set<Cleark> cleark = new HashSet<>();
        cleark.add(cl);

        Price p = priceListCombo.getSelectionModel().getSelectedItem();
        Set<Price> price = new HashSet<>();
        price.add(p);

        Set<StoreDetails> sd = new HashSet<>();
        sd.add(loggedStoreUser);

        String date = saleDate.getEditor().getText();

        MovieSale ms = new MovieSale(0, movie, customer, cleark, price, sd, date);

        Session session = null;
        Transaction transaction = null;

        try {
            session = SingleFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(ms);
            transaction.commit();

            movieSale.add(ms);
            saleTableView.setItems(movieSale);
            saleDate.setValue(null);
            movieListCombo.getSelectionModel().clearSelection();
            clearkListCombo.getSelectionModel().clearSelection();
            priceListCombo.getSelectionModel().clearSelection();
            customerListCombo.getSelectionModel().clearSelection();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText("Information Added");
            alert.setContentText("Successfully Added Your Information");
            alert.showAndWait();
            return;
        } catch (Exception e) {
        }
    }

    @FXML
    private void handleUpdateAction(ActionEvent event) {

        String number = contractNoField.getText();

        try {
            session = SingleFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            StoreDetails sd = new StoreDetails();
            String query = "UPDATE StoreDetails SET contractNo = '" + number + "' WHERE id = '" + loggedStoreUser.getId() + "'";

            SQLQuery sqlQuery = session.createSQLQuery(query);
            sqlQuery.executeUpdate();
            transaction.commit();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText("Information Updated");
            alert.setContentText("Successfully Updated Your Information");
            alert.showAndWait();
            return;
        } catch (Exception e) {
            System.out.println(e);
            if (transaction != null) {
                transaction.rollback();
            }

        } finally {
            session.close();
        }

    }

}
