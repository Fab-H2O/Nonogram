/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fabtimalex.nonogramlourd;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author fbnhe
 */
public class LoginController implements Initializable {
    public static boolean isAdmin = false;

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
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/nonogram";
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        // recupere element de scene builder.
        String identifiant = username.getText();
        String mdp = password.getText();
        
        // si vide, cad si tu valide la connexion sans ID ou mdp, il renvoie un message d erreur
        if (identifiant.isEmpty() || mdp.isEmpty()) {
            wrongLogin.setTextFill(javafx.scene.paint.Color.RED);
            wrongLogin.setText("Veuillez rentrer le nom d'utilisateur et mot de passe");
            return;
        }
        
        try {
            wrongLogin.setTextFill(javafx.scene.paint.Color.BLACK);
            wrongLogin.setText("Connexion à la base...\n"); 
            
            // tentative de connexion using default database credentials
            try (Connection conn = DriverManager.getConnection(DB_URL, "root", "root")) {
                String query = "SELECT * FROM player WHERE player_name = ?";
                try (PreparedStatement pst = conn.prepareStatement(query)) {
                    pst.setString(1, identifiant);
                    try (ResultSet rs = pst.executeQuery()) {
                        if (rs.next()) {
                            int id = rs.getInt("id");
                            boolean isBlocked = rs.getBoolean("is_blocked");
                            boolean isAdmin = rs.getBoolean("isAdmin");
                            String hashedPwd = rs.getString("player_pwd");
                            int tentativeConnexion = rs.getInt("tentative_connexion");
                            
                            // verif si l'utilisateur est bloquer
                            if (isBlocked) {
                                wrongLogin.setTextFill(javafx.scene.paint.Color.RED);
                                wrongLogin.setText("This account is blocked.");
                                return;
                            }
                            // verif Bcrypt
                            boolean isPasswordCorrect = false;
                            try {
                                String verifyHashed = hashedPwd;
                                if (verifyHashed != null && verifyHashed.startsWith("$2y$")) {
                                    verifyHashed = "$2a$" + verifyHashed.substring(4);
                                }
                                isPasswordCorrect = BCrypt.checkpw(mdp, verifyHashed);
                            } catch (IllegalArgumentException e) {
                                // si le mot de passe n'est pas hasher on revient en text claire
                                isPasswordCorrect = mdp.equals(hashedPwd);
                            }
                            
                            if (isPasswordCorrect) {
                                LoginController.isAdmin = isAdmin;

                                // remet a zero la tentative de connexion et met a jour la date de connexion
                                String updateSuccess = "UPDATE player SET tentative_connexion = 0, lastLog = CURDATE() WHERE id = ?";
                                try (PreparedStatement updatePst = conn.prepareStatement(updateSuccess)) {
                                    updatePst.setInt(1, id);
                                    updatePst.executeUpdate();
                                }
                                
                                // connexion avec succes
                                wrongLogin.setTextFill(javafx.scene.paint.Color.GREEN);
                                wrongLogin.setText("Connexion avec succes!");
                                
                                try {
                                    if (isAdmin) {
                                        // si admin charge la page admin des tables
                                        App.setRoot("datatable_admin");
                                    } else {
                                        // si pas admin charge la page datatable
                                        App.setRoot("datatable");
                                    }
                                } catch (IOException ex) {
                                    wrongLogin.setTextFill(javafx.scene.paint.Color.RED);
                                    wrongLogin.setText("Echec de chargement de la page");
                                    ex.printStackTrace();
                                }
                            } else {
                                // si la connexion a echoué incremente
                                int newTentative = tentativeConnexion + 1;
                                if (newTentative >= 3) {
                                    // si le nombre de tentative est egal ou sup a 3 alors block l'utilisateur
                                    String updateBlock = "UPDATE player SET tentative_connexion = ?, is_blocked = 1 WHERE id = ?";
                                    try (PreparedStatement updatePst = conn.prepareStatement(updateBlock)) {
                                        updatePst.setInt(1, newTentative);
                                        updatePst.setInt(2, id);
                                        updatePst.executeUpdate();
                                    }
                                    wrongLogin.setTextFill(javafx.scene.paint.Color.RED);
                                    wrongLogin.setText("Trop de tentative, compte bloquer");
                                } else {
                                    String updateTentative = "UPDATE player SET tentative_connexion = ? WHERE id = ?";
                                    try (PreparedStatement updatePst = conn.prepareStatement(updateTentative)) {
                                        updatePst.setInt(1, newTentative);
                                        updatePst.setInt(2, id);
                                        updatePst.executeUpdate();
                                    }
                                    wrongLogin.setTextFill(javafx.scene.paint.Color.RED);
                                    wrongLogin.setText("Tentative invalide " + newTentative + " of 3.");
                                }
                            }
                        } else {
                            wrongLogin.setTextFill(javafx.scene.paint.Color.RED);
                            wrongLogin.setText("Mot de passe ou utilisateur invalide.");
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            wrongLogin.setTextFill(javafx.scene.paint.Color.RED);
            wrongLogin.setText("Erreur de base de donnée: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
