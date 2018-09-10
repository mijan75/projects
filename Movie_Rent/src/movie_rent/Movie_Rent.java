/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_rent;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class Movie_Rent extends Application {
    private static Stage mainStage;

    public static Stage getMainStage() {
        return mainStage;

    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        mainStage = stage;
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static  Session session;
    public static  Transaction transaction;
     
     public static void changeScene(String FXML) {
     
            Parent root;
        try {
            root = FXMLLoader.load(Movie_Rent.class.getResource(FXML));
       
            Scene scene = new Scene(root);
            
            Movie_Rent.getMainStage().setScene(scene);
            Movie_Rent.getMainStage().show();
        } catch (IOException ex) {
            Logger.getLogger(Movie_Rent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @param args the command line arguments
     */
     public void stop() throws Exception {
        SingleFactory.getSessionFactory().close();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
