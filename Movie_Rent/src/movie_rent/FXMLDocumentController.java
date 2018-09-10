/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_rent;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
/**
 *
 * @author User
 */

import Entity.Login;
import Entity.StoreDetails;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import static movie_rent.MijansStoreController.loggedStoreUser;

public class FXMLDocumentController implements Initializable {

    @FXML
    private JFXTextField userName;
    @FXML
    private JFXPasswordField password;

    public static String loggedUsername;

    @FXML
    private JFXButton handleLoginAction;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleLoginAction(ActionEvent event) {
        String username = userName.getText();
        String passWord = password.getText();
        Session session = null;
        Transaction transaction = null;

        try {
            session = SingleFactory.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Login.class);
            criteria.add(Restrictions.eq("userName", username)).add(Restrictions.eq("password", passWord));
            List<Login> log = (List<Login>) criteria.list();
            if (log.size() == 1) {
                String role = "";
                for (Login login : log) {
                    role = login.getUserType();
                }

                loggedUsername = username;
                switch (role) {
                    case "mv":
                        changeScene("MainView.fxml");
                        break;
                    case "store":

                        Criteria criteria2 = session.createCriteria(StoreDetails.class);
                        criteria2.add(Restrictions.eq("storeUserName", loggedUsername));
                        Object result = criteria2.uniqueResult();
                        if (result != null) {
                            loggedStoreUser = (StoreDetails) result;

                        }
                        changeScene("Mijans Store.fxml");
                        break;

                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error Dialog");
                alert.setHeaderText("Please Give an Right Password And User Name");
                alert.setContentText("Ooops, there was an error!");

                alert.showAndWait();

            }
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

    private void changeScene(String fxml) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Scene scene = new Scene(root);

            Movie_Rent.getMainStage().setScene(scene);
            Movie_Rent.getMainStage().show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
