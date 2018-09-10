/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_rent;

import Entity.Cleark;
import Entity.Distributor;
import Entity.MovieDetails;
import Entity.MovieSale;
import Entity.OfferedMovie;

import Entity.Price;
import Entity.StoreDetails;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static movie_rent.MijansStoreController.loggedStoreUser;
import static movie_rent.FXMLDocumentController.loggedUsername;
import static movie_rent.Movie_Rent.changeScene;
import static movie_rent.Movie_Rent.session;
import static movie_rent.Movie_Rent.transaction;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MainViewController implements Initializable {

    @FXML
    private TableView<MovieDetails> movieListTable;
    @FXML
    private TableColumn<MovieDetails, String> movieNameView;
    @FXML
    private TableColumn<MovieDetails, String> dirNameView;

    @FXML
    private JFXTextField movieNameField;
    @FXML
    private JFXTextField dirNameField;
    @FXML
    private JFXTextField starNameField;
    private JFXTextField descField;
    @FXML
    private JFXTextField disNameField;
    @FXML
    private JFXTextField disCityField;
    @FXML
    private JFXTextField disNoField;
    @FXML
    private DatePicker distributorDate;
    @FXML
    private JFXTextField storeNameField;
    @FXML
    private JFXTextField storeCityField;
    @FXML
    private JFXTextField storeNoField;
    @FXML
    private DatePicker storeDate;

    private static Session session;
    private static Transaction transaction;
    private static ObservableList<MovieDetails> movieDetails;
    private static ObservableList<Distributor> distributorList;
    private static ObservableList<StoreDetails> storeList;
    private static ObservableList<Price> priceList;
    @FXML
    private TableColumn<MovieDetails, String> relDateView;
    @FXML
    private DatePicker relDate;
    @FXML
    private TableColumn<MovieDetails, String> starNameView;
    @FXML
    private TableView<Distributor> distributorTableView;
    @FXML
    private TableColumn<Distributor, String> disNameView;
    @FXML
    private TableColumn<Distributor, String> disCityView;
    @FXML
    private TableColumn<Distributor, String> disnumberView;
    @FXML
    private TableColumn<Distributor, String> disDateView;
    @FXML
    private TableView<StoreDetails> storeTableView;
    @FXML
    private TableColumn<StoreDetails, String> storeNameView;
    @FXML
    private TableColumn<StoreDetails, String> storeCityView;
    @FXML
    private TableColumn<StoreDetails, String> StorenumberView;
    @FXML
    private TableColumn<StoreDetails, String> estaDate;
    @FXML
    private JFXTextField storeuserNameField;
    @FXML
    private JFXComboBox<Price> buyPriceCombo;

    @FXML
    private JFXTextField priceAddedField;
    @FXML
    private JFXTextField clearkNameField;
    @FXML
    private JFXTextField clearkCityField;
    @FXML
    private JFXTextField clearkNoField;
    @FXML
    private DatePicker clearkEntryDate;
    @FXML
    private TableView<Cleark> clearkTable;
    @FXML
    private TableColumn<Cleark, String> clearkNameView;
    @FXML
    private TableColumn<Cleark, String> clearkCityView;
    @FXML
    private TableColumn<Cleark, String> clearkNoView;
    @FXML
    private TableColumn<Cleark, String> clearkDateView;
    @FXML
    private JFXComboBox<StoreDetails> storeNameCombo;
    private ObservableList<Cleark> clearkList;
    @FXML
    private JFXComboBox<MovieDetails> distributeMovieList;
    @FXML
    private JFXComboBox<StoreDetails> distributeStoreList;
    @FXML
    private JFXComboBox<Distributor> distributeDistList;
    @FXML
    private DatePicker distributeDate;
    private ObservableList<OfferedMovie> offerList;
    private ObservableList<MovieSale> customerMovieList;
    @FXML
    private TableView<OfferedMovie> offeredMovieTableView;
    @FXML
    private TableColumn<OfferedMovie, String> offeredMovieView;
    @FXML
    private TableColumn<OfferedMovie, String> offeredStoreView;
    @FXML
    private TableColumn<OfferedMovie, String> offeredDistributorView;
    @FXML
    private TableColumn<OfferedMovie, String> offeredDateView;
    @FXML
    private TableColumn<Cleark, String> employeeStoreName;
    private TableView<MovieSale> customerListTable;
    @FXML
    private TableColumn<MovieSale, String> customerView;
    @FXML
    private TableColumn<MovieSale, String> priceView;
    @FXML
    private TableColumn<MovieSale, String> dateView;
    @FXML
    private TableColumn<MovieSale, String> CstoreNameView;
    @FXML
    private TableView<MovieSale> cusomerListTable;
    @FXML
    private TableColumn<MovieSale, String> clearkView;
    @FXML
    private TableColumn<MovieSale, String> movieViewSale;

    private int currentIndex;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentIndex = 0;
        movieDetails = FXCollections.observableArrayList();
        distributorList = FXCollections.observableArrayList();
        customerMovieList = FXCollections.observableArrayList();
        
        storeList = FXCollections.observableArrayList();
        priceList = FXCollections.observableArrayList();
        clearkList = FXCollections.observableArrayList();
        offerList = FXCollections.observableArrayList();
        
        
        // movie List
        movieNameView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMovieName()));
        dirNameView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDirectorName()));
        starNameView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStarName()));
        relDateView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getReleaseDate()));

        //Distributor List
        disNameView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDistributorName()));
        disCityView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDistributorCity()));
        disnumberView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getContractNo()));
        disDateView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHiredDate()));

        //Store List
        storeNameView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStoreName()));
        storeCityView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStoreCity()));
        StorenumberView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getContractNo()));
        estaDate.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEstaDate()));

        // Clear List
        clearkNameView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getClearkName()));
        clearkCityView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getClearkCity()));
        clearkNoView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getClearkNo()));
        clearkDateView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getClearkEntryDate()));
        employeeStoreName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSd() + ""));

        //Offered Movie List
        offeredMovieView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMv() + ""));
        offeredStoreView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSd() + ""));
        offeredDistributorView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDistributor() + ""));
        offeredDateView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate()));

        /// Customer List
        customerView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCustomer() + ""));
        CstoreNameView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSd() + ""));
        priceView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrice() + ""));
        dateView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate()));
        clearkView.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCleark() + ""));
        movieViewSale.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOm() + ""));

        try {

            session = SingleFactory.getSessionFactory().openSession();
            List<MovieDetails> log = session.createCriteria(MovieDetails.class).list();
            List<Distributor> log1 = session.createCriteria(Distributor.class).list();
            List<StoreDetails> log2 = session.createCriteria(StoreDetails.class).list();
            List<Price> log3 = session.createCriteria(Price.class).list();
            List<Cleark> log4 = session.createCriteria(Cleark.class).list();

            movieDetails.addAll(log);
            distributorList.addAll(log1);
            storeList.addAll(log2);
            priceList.addAll(log3);
            clearkList.addAll(log4);

            movieListTable.setItems(movieDetails);
            distributorTableView.setItems(distributorList);
            storeTableView.setItems(storeList);
            buyPriceCombo.setItems(priceList);
            distributeMovieList.setItems(movieDetails);
            distributeStoreList.setItems(storeList);
            distributeDistList.setItems(distributorList);
            clearkTable.setItems(clearkList);
            storeNameCombo.setItems(storeList);

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
            List<OfferedMovie> log5 = session.createCriteria(OfferedMovie.class).list();

            offerList.addAll(log5);

            offeredMovieTableView.setItems(offerList);

        } catch (Exception e) {
            System.out.println(e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        try {
            session = SingleFactory.getSessionFactory().openSession();

            List<MovieSale> movie = session.createCriteria(MovieSale.class).list();

            customerMovieList.addAll(movie);

            cusomerListTable.setItems(customerMovieList);
        } catch (Exception e) {
            System.out.println(e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @FXML
    private void handleMovieAddAction(ActionEvent event) {

        String movieName = movieNameField.getText();
        String directorName = dirNameField.getText();
        String starName = starNameField.getText();
        String date = relDate.getEditor().getText();
        Price buyPrice = buyPriceCombo.getSelectionModel().getSelectedItem();

        Set<Price> p = new HashSet<>();
        p.add(buyPrice);

        MovieDetails md = new MovieDetails(0, movieName, directorName, starName, date, p);
        try {
            session = SingleFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(md);
            System.out.println(md);
            transaction.commit();
            movieDetails.add(md);
            clearField();
            buyPriceCombo.getSelectionModel().clearSelection();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText("Information Added");
            alert.setContentText("Successfully Added Your Information");
            alert.showAndWait();
            return;

        } catch (Exception e) {
            System.err.println(e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

    }

    public void clearField() {
        movieNameField.clear();
        dirNameField.clear();
        starNameField.clear();
        relDate.setValue(null);
        disNameField.clear();
        disCityField.clear();
        disNoField.clear();
        distributorDate.setValue(null);
        storeNameField.clear();
        storeCityField.clear();
        storeNoField.clear();
        storeDate.setValue(null);
        storeuserNameField.clear();
    }

    @FXML
    private void handleDistributorAddAction(ActionEvent event) {
        String distName = disNameField.getText();
        String distCity = disCityField.getText();
        String contractNo = disNoField.getText();
        String hiredDate = distributorDate.getEditor().getText();

        Distributor db = new Distributor(0, distName, distCity, contractNo, hiredDate);
        try {
            session = SingleFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(db);
            transaction.commit();
            distributorList.add(db);
            clearField();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText("Information Added");
            alert.setContentText("Successfully Added Your Information");
            alert.showAndWait();
            return;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

    }
    
     @FXML
    private void handleDistributorUpdateAction(ActionEvent event) {
      
        String distName = disNameField.getText();
        String distCity = disCityField.getText();
        String contractNo = disNoField.getText();
        String hiredDate = distributorDate.getEditor().getText();
        
         try {
            session = SingleFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Distributor dist = new Distributor();
           
            
           
            String query = "UPDATE Distributor SET distributorName = '"+distName+"', distributorCity = '"+distCity+"',"
                    + "contractNo = '"+contractNo+"'  WHERE hiredDate = '"+hiredDate+"'";
            SQLQuery sqlQuery = session.createSQLQuery(query);
            sqlQuery.executeUpdate();
            transaction.commit();
                     
          
            distributorList.add(dist);
            
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText("Data Updated Successfully");
            alert.setContentText("Successfully Updated Your Information");
            alert.showAndWait();
            return;
         } catch (Exception e) {
         }
    }

    @FXML
    private void handleStoreMovieAddAction(ActionEvent event) {
        String storeName = storeNameField.getText();
        String storeCity = storeCityField.getText();
        String storeNo = storeNoField.getText();
        String establishedDate = storeDate.getEditor().getText();
        String userName = storeuserNameField.getText();

        StoreDetails sd = new StoreDetails(0, storeName, storeCity, storeNo, establishedDate, userName);

        try {
            session = SingleFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(sd);
            transaction.commit();
            storeList.add(sd);
            clearField();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText("Information Added");
            alert.setContentText("Successfully Added Your Information");
            alert.showAndWait();
            return;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

    }

    
     @FXML
    private void handleStoreUpdateAction(ActionEvent event) {
        String storeName = storeNameField.getText();
        String storeCity  = storeCityField.getText();
        String con = storeNoField.getText();
        String date = storeDate.getEditor().getText();
        String storeUser = storeuserNameField.getText();
        
        try{
            session = SingleFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            StoreDetails sd = new StoreDetails();
           
            
           
            String query = "UPDATE StoreDetails SET storeName = '"+storeName+"', storeCity = '"+storeCity+"',"
                    + "contractNo = '"+con+"', storeUserName = '"+storeUser+"'  WHERE estaDate = '"+date+"'";
            
            SQLQuery sqlQuery = session.createSQLQuery(query);
            sqlQuery.executeUpdate();
            transaction.commit();
            
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText("Information Added");
            alert.setContentText("Successfully Added Your Information");
            alert.showAndWait();
            return;
            
        
        }catch(Exception e){
            
        }
        
        
    }
    
    
    @FXML
    private void handleLogoutAction(ActionEvent event) {
        changeScene("FXMLDocument.fxml");
        loggedUsername = null;
        loggedStoreUser = null;

    }

    @FXML
    private void handlePriceAddAction(ActionEvent event) {
        Double price = Double.parseDouble(priceAddedField.getText());

        Price p = new Price(price);

        try {
            session = SingleFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(p);
            transaction.commit();
            priceList.add(p);
            priceAddedField.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText("Information Added");
            alert.setContentText("Successfully Added Your Information");
            alert.showAndWait();
            return;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @FXML
    private void handleClearkAddAction(ActionEvent event) {
        String clearkname = clearkNameField.getText();
        String clearkcity = clearkCityField.getText();
        String clearkNo = clearkNoField.getText();
        String entryDate = clearkEntryDate.getEditor().getText();
        StoreDetails sd = storeNameCombo.getSelectionModel().getSelectedItem();
        Set<StoreDetails> sdd = new HashSet<>();
        sdd.add(sd);
        Cleark cl = new Cleark(0, clearkname, clearkcity, clearkNo, entryDate, sdd);
        try {
            session = SingleFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(cl);
            transaction.commit();
            clearkList.add(cl);

            clearkNameField.clear();
            clearkCityField.clear();
            clearkNoField.clear();
            clearkEntryDate.setValue(null);
            storeNameCombo.getSelectionModel().clearSelection();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText("Information Added");
            alert.setContentText("Successfully Added Your Information");
            alert.showAndWait();
            return;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

     @FXML
    private void handleClearkUpdateAction(ActionEvent event) {
         String clearkname = clearkNameField.getText();
        String clearkcity = clearkCityField.getText();
        String clearkNo = clearkNoField.getText();
        String entryDate = clearkEntryDate.getEditor().getText();
        StoreDetails sd = storeNameCombo.getSelectionModel().getSelectedItem();
        
         try{
            session = SingleFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Cleark cl = new Cleark();
           
            
           
            String query = "UPDATE Cleark SET clearkName = '"+clearkname+"', clearkCity = '"+clearkcity+"',"
                    + "clearkNo = '"+clearkNo+"' WHERE clearkEntryDate = '"+entryDate+"'";
            
            SQLQuery sqlQuery = session.createSQLQuery(query);
            sqlQuery.executeUpdate();
            transaction.commit();
            
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText("Information Added");
            alert.setContentText("Successfully Added Your Information");
            alert.showAndWait();
            return;
            
        
        }catch(Exception e){
            
        }
    }
    
    
    @FXML
    private void handleSendAction(ActionEvent event) {
        StoreDetails store = distributeStoreList.getSelectionModel().getSelectedItem();
        MovieDetails movie = distributeMovieList.getSelectionModel().getSelectedItem();
        Distributor distri = distributeDistList.getSelectionModel().getSelectedItem();
        String date = distributeDate.getEditor().getText();

        Set<StoreDetails> sd = new HashSet<>();
        sd.add(store);

        Set<MovieDetails> md = new HashSet<>();
        md.add(movie);

        Set<Distributor> ds = new HashSet<>();
        ds.add(distri);

        OfferedMovie om = new OfferedMovie(0, md, sd, ds, date);

        try {
            session = SingleFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.saveOrUpdate(om);
            transaction.commit();
            offerList.add(om);

            distributeDistList.getSelectionModel().clearSelection();
            distributeMovieList.getSelectionModel().clearSelection();
            distributeStoreList.getSelectionModel().clearSelection();
            distributeDate.setValue(null);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText("Information Added");
            alert.setContentText("Successfully Send Your Information");
            alert.showAndWait();
            return;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void handleMouseClickedAction(ActionEvent event) {

    }

    @FXML
    private void handleMouseClickedAction(MouseEvent event) {
        currentIndex = distributorTableView.getSelectionModel().getSelectedIndex();
        String distName = distributorList.get(currentIndex).getDistributorName();
        String distCity = distributorList.get(currentIndex).getDistributorCity();
        String contractNo = distributorList.get(currentIndex).getContractNo();
        String hiredDate =  distributorList.get(currentIndex).getHiredDate();
        
        disNameField.setText(distName);
        disCityField.setText(distCity);
        disNoField.setText(contractNo);
        distributorDate.setEditable(true);
    }

    @FXML
    private void handleDistributorDelAction(ActionEvent event) {
        int name = distributorList.get(currentIndex).getDistributorId();

        try {
            session = SingleFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Distributor dis = (Distributor) session.get(Distributor.class, name);

            session.delete(dis);

            transaction.commit();
            distributorList.remove(currentIndex);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText("Information Added");
            alert.setContentText("Successfully Send Your Information");
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

    @FXML
    private void handleStoreClickedAction(MouseEvent event) {
        currentIndex = storeTableView.getSelectionModel().getSelectedIndex();
        
        String storeName = storeList.get(currentIndex).getStoreName();
        String storeCity = storeList.get(currentIndex).getStoreCity();
        String contractNo = storeList.get(currentIndex).getContractNo();
        String userName = storeList.get(currentIndex).getStoreUserName();
        
        storeNameField.setText(storeName);
        storeCityField.setText(storeCity);
        storeNoField.setText(contractNo);
        storeuserNameField.setText(userName);
        
    }

    @FXML
    private void handleStoreMovieDelAction(ActionEvent event) {
        int name = storeList.get(currentIndex).getId();

        try {
            session = SingleFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            StoreDetails sd = (StoreDetails) session.get(StoreDetails.class, name);

            session.delete(sd);

            transaction.commit();
            storeList.remove(currentIndex);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText("Information Deleted");
            alert.setContentText("Successfully Delete Your Information");
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

    @FXML
    private void handleEmployeeClickedAction(MouseEvent event) {
        currentIndex = clearkTable.getSelectionModel().getSelectedIndex();
        
        String clearkName = clearkList.get(currentIndex).getClearkName();
        String clearkCity = clearkList.get(currentIndex).getClearkCity();
        String no = clearkList.get(currentIndex).getClearkNo();
        
        clearkNameField.setText(clearkName);
        clearkCityField.setText(clearkCity);
        clearkNoField.setText(no);
    }

    @FXML
    private void handleClearkDelAction(ActionEvent event) {
        int name = clearkList.get(currentIndex).getClearkId();

        try {
            session = SingleFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Cleark cl = (Cleark) session.get(Cleark.class, name);

            session.delete(cl);

            transaction.commit();
            clearkList.remove(currentIndex);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText("Information Deleted");
            alert.setContentText("Successfully Delete Your Information");
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
