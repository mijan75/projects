/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computer.store;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * FXML Controller class
 *
 * @author nazmul
 */
public class BuyProductController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleLoginAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene = new Scene(root);

        ComputerStore.getMainStage().setScene(scene);
        ComputerStore.getMainStage().show();
    }

    @FXML
    private void handleSigninAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));

        Scene scene = new Scene(root);

        ComputerStore.getMainStage().setScene(scene);
        ComputerStore.getMainStage().show();
    }

}
