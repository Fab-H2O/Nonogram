package fabtimalex.nonogramlourd;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DatatableController implements Initializable {
    // element graphic de la page, ce sont les ID que j'ai attribuer
    @FXML
    private Button tablePlayer;

    @FXML
    private Button tableNonogram;

    @FXML
    private Button tableScore;

    @FXML
    private TableView<Object> table;

    @FXML
    private TextField requeteDatabase;

    @FXML
    private Button suppDatabase;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Init default view or just wait for user action
    }

    /**
     * Affiche les joueurs dans la table
     */
    @FXML
    private void showPlayerTable(ActionEvent event) {
        table.getColumns().clear();
        // definie le nom des colonnes pour la "table"
        TableColumn<Object, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("playerName"));

        TableColumn<Object, String> lastLogCol = new TableColumn<>("Last Log");
        lastLogCol.setCellValueFactory(new PropertyValueFactory<>("lastLog"));

        TableColumn<Object, String> signOutCol = new TableColumn<>("Sign Out");
        signOutCol.setCellValueFactory(new PropertyValueFactory<>("signOut"));
        
        // on les definie pour l'affichage
        table.getColumns().addAll(nameCol, lastLogCol, signOutCol);

        // creer une list pour contenir les donnes de la bdd dans la table visuel
        ObservableList<Object> data = FXCollections.observableArrayList();

        // bon la c'est ecrit en dure pour l instant car on valider sa connexion c'est mauvais je sais, je ferais un fichier properties
        //On fait une requete SQL 
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nonogram", "root", "root")) {
            String query = "SELECT player_name, lastLog, sign_out FROM player";
            try (PreparedStatement pst = conn.prepareStatement(query);
                 ResultSet rs = pst.executeQuery()) {
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                while (rs.next()) {
                    String name = rs.getString("player_name");
                    java.sql.Date sqlDate = rs.getDate("lastLog");
                    String dateStr = sqlDate != null ? sdf.format(sqlDate) : "N/A";
                    String signOut = rs.getBoolean("sign_out") ? "Yes" : "No";

                    data.add(new PlayerModel(name, dateStr, signOut));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        table.setItems(data);
    }

    @FXML
    private void showScoreTable(ActionEvent event) {
        table.getColumns().clear();

        TableColumn<Object, String> nameCol = new TableColumn<>("Player");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("playerName"));

        TableColumn<Object, String> scoreCol = new TableColumn<>("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));

        TableColumn<Object, String> puzzleCol = new TableColumn<>("Puzzle");
        puzzleCol.setCellValueFactory(new PropertyValueFactory<>("puzzleId"));

        table.getColumns().addAll(nameCol, scoreCol, puzzleCol);

        ObservableList<Object> data = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nonogram", "root", "root")) {
            // Join with player table to get the name instead of just the ID 
            String query = "SELECT p.player_name, s.score, s.puzzle " +
                           "FROM score s " +
                           "JOIN player p ON s.player = p.id";
                           
            try (PreparedStatement pst = conn.prepareStatement(query);
                 ResultSet rs = pst.executeQuery()) {
                 
                while (rs.next()) {
                    String name = rs.getString("player_name");
                    String score = String.valueOf(rs.getInt("score"));
                    String puzzle = String.valueOf(rs.getInt("puzzle"));

                    data.add(new ScoreModel(name, score, puzzle));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        table.setItems(data);
    }

    @FXML
    private void switchDisplay(ActionEvent event) {
        try {
            App.setRoot("display");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
