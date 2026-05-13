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
    private Button ExecuteSQL;

    @FXML
    private Button PreviousUserlist;

    @FXML
    private Button NextUserlist;

    private String currentView = "player"; // Tracks if we are viewing "player" or "score"
    private int currentOffset = 0;
    private final int PAGE_SIZE = 10;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initial load
        showPlayerTable(null);
    }

    @FXML
    private void showPlayerTable(ActionEvent event) {
        currentView = "player";
        currentOffset = 0;
        loadPlayerData();
    }

    @FXML
    private void showScoreTable(ActionEvent event) {
        currentView = "score";
        currentOffset = 0;
        loadScoreData();
    }

    @FXML
    private void executeSQL(ActionEvent event) {
        String query = requeteDatabase.getText();
        if (query == null || query.isEmpty()) return;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nonogram", "root", "root");
             Statement stmt = conn.createStatement()) {
            
            boolean isSelect = query.trim().toLowerCase().startsWith("select");
            if (isSelect) {
                stmt.executeQuery(query);
            } else {
                stmt.executeUpdate(query);
            }
            refreshTable();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void previousPage(ActionEvent event) {
        if (currentOffset >= PAGE_SIZE) {
            currentOffset -= PAGE_SIZE;
            refreshTable();
        }
    }

    @FXML
    private void nextPage(ActionEvent event) {
        currentOffset += PAGE_SIZE;
        refreshTable();
    }

    private void refreshTable() {
        if ("player".equals(currentView)) {
            loadPlayerData();
        } else if ("score".equals(currentView)) {
            loadScoreData();
        }
    }

    private void loadPlayerData() {
        table.getColumns().clear();
        TableColumn<Object, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("playerName"));

        TableColumn<Object, String> lastLogCol = new TableColumn<>("Last Log");
        lastLogCol.setCellValueFactory(new PropertyValueFactory<>("lastLog"));

        TableColumn<Object, String> signOutCol = new TableColumn<>("Sign Out");
        signOutCol.setCellValueFactory(new PropertyValueFactory<>("signOut"));
        
        table.getColumns().addAll(nameCol, lastLogCol, signOutCol);

        ObservableList<Object> data = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nonogram", "root", "root")) {
            String query = "SELECT player_name, lastLog, sign_out FROM player LIMIT ? OFFSET ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, PAGE_SIZE);
                pst.setInt(2, currentOffset);
                try (ResultSet rs = pst.executeQuery()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    while (rs.next()) {
                        String name = rs.getString("player_name");
                        java.sql.Date sqlDate = rs.getDate("lastLog");
                        String dateStr = sqlDate != null ? sdf.format(sqlDate) : "N/A";
                        String signOut = rs.getBoolean("sign_out") ? "Yes" : "No";
                        data.add(new PlayerModel(name, dateStr, signOut));
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        table.setItems(data);
    }

    private void loadScoreData() {
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
            String query = "SELECT p.player_name, s.score, s.puzzle " +
                           "FROM score s " +
                           "JOIN player p ON s.player = p.id " +
                           "LIMIT ? OFFSET ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, PAGE_SIZE);
                pst.setInt(2, currentOffset);
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        String name = rs.getString("player_name");
                        String score = String.valueOf(rs.getInt("score"));
                        String puzzle = String.valueOf(rs.getInt("puzzle"));
                        data.add(new ScoreModel(name, score, puzzle));
                    }
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
