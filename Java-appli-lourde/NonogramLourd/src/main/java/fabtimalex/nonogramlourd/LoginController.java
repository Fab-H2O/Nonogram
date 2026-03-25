/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fabtimalex.nonogramlourd;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author fbnhe
 */
public class LoginController implements Initializable {
    // J'ai deja mis de ID tag dans mon scene builder
    // On recupere l'ID
    @FXML
    private TextField username;
    // On recupere le mot de passe
    @FXML
    private PasswordField password;
    
    @FXML
    private Button confirm;
    // permettra de renvoier le msg d erreur. 
    @FXML
    private Label wrongLogin;
    
    static final String DB_URL = "jdbc:mysql://localhost/nonogram";
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleLogin(ActionEvent event) {

//        Statement stmt = null;
        // recupere element de scene builder.
        String identifiant = username.getText();
        String mdp = password.getText();
        // si vide, cad si tu valide la connexion sans ID ou mdp, il renvoie un message d erreur
        if (identifiant.isEmpty() || mdp.isEmpty()) {
            wrongLogin.setText("Please enter username and password.");
            return;
        }
        
        try {
            wrongLogin.setText("Connexion à la base...\n"); 
            
            // tentative de connexion
            Connection conn = DriverManager.getConnection(DB_URL, identifiant, mdp);
            // on paint le text en vert, connexion avec success
            wrongLogin.setTextFill(javafx.scene.paint.Color.GREEN);
            wrongLogin.setText("Login successful!");
            // fonction try catch pour changer de page, avec une option si ca rate.
            try {
                App.setRoot("datatable");
            } catch (IOException ex) {
                wrongLogin.setTextFill(javafx.scene.paint.Color.RED);
                wrongLogin.setText("Failed to load next view.");
                ex.printStackTrace();
            }
            //paint un message en rouge, message d erreur de la connexion a la bdd
        } catch (SQLException ex) {
            wrongLogin.setTextFill(javafx.scene.paint.Color.RED);
            wrongLogin.setText("Database error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
