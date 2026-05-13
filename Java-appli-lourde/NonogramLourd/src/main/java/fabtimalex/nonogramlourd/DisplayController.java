package fabtimalex.nonogramlourd;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class DisplayController implements Initializable {

    @FXML
    private Canvas canvasNonogram;

    @FXML
    private Button deleteNonogram;

    @FXML
    private Button previousbtn;

    @FXML
    private Button nextbtn;

    @FXML
    private Label puzzleID;

    @FXML
    private Label CreatorName;

    @FXML
    private Button returndatable;

    private int currentPuzzleOffset = 0;
    private int currentPuzzleIdVal = -1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPuzzle(0);
    }

    /**
     * Charge un puzzle depuis la base de données selon l'offset (pas de 1)
     */
    private void loadPuzzle(int offset) {
        if (offset < 0) return;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nonogram", "root", "root")) {
            // Requete pour recuperer le puzzle et le nom du createur
            String query = "SELECT p.id, p.matrice, pl.player_name " +
                           "FROM puzzle p " +
                           "JOIN player pl ON p.creator = pl.id " +
                           "ORDER BY p.id ASC " +
                           "LIMIT 1 OFFSET ?";
            
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, offset);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        currentPuzzleIdVal = rs.getInt("id");
                        String matrice = rs.getString("matrice");
                        String creator = rs.getString("player_name");

                        puzzleID.setText(String.valueOf(currentPuzzleIdVal));
                        CreatorName.setText(creator);
                        drawNonogram(matrice);
                        currentPuzzleOffset = offset;
                    } else {
                        // Si on est a la fin de la liste, on ne fait rien ou on boucle?
                        // L'utilisateur demande juste de naviguer.
                        if (offset == 0) {
                            // Pas de puzzles du tout
                            puzzleID.setText("N/A");
                            CreatorName.setText("N/A");
                            GraphicsContext gc = canvasNonogram.getGraphicsContext2D();
                            gc.clearRect(0, 0, canvasNonogram.getWidth(), canvasNonogram.getHeight());
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleNext(ActionEvent event) {
        loadPuzzle(currentPuzzleOffset + 1);
    }

    @FXML
    private void handlePrevious(ActionEvent event) {
        if (currentPuzzleOffset > 0) {
            loadPuzzle(currentPuzzleOffset - 1);
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        if (currentPuzzleIdVal == -1) return;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nonogram", "root", "root")) {
            String query = "DELETE FROM puzzle WHERE id = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, currentPuzzleIdVal);
                pst.executeUpdate();
                // On recharge le puzzle à l'offset actuel (qui sera le suivant dans la liste)
                loadPuzzle(currentPuzzleOffset);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Dessine la matrice sur le Canvas
     */
    private void drawNonogram(String matrice) {
        GraphicsContext gc = canvasNonogram.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasNonogram.getWidth(), canvasNonogram.getHeight());

        if (matrice == null || matrice.isEmpty()) return;

        // On nettoie la chaine pour ne garder que les 0 et 1 (au cas ou il y aurait des separateurs)
        String cleanMatrice = matrice.replaceAll("[^01]", "");
        int totalCells = cleanMatrice.length();
        if (totalCells == 0) return;

        int size = (int) Math.sqrt(totalCells);
        // On s'assure que c'est bien une matrice carree
        if (size * size != totalCells) {
            // Optionnel: gerer les matrices non carrees
        }

        double canvasWidth = canvasNonogram.getWidth();
        double canvasHeight = canvasNonogram.getHeight();
        double cellSize = Math.min(canvasWidth, canvasHeight) / size;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int index = i * size + j;
                if (index < totalCells) {
                    char cell = cleanMatrice.charAt(index);
                    if (cell == '1') {
                        gc.setFill(Color.BLACK);
                    } else {
                        gc.setFill(Color.WHITE);
                    }
                    gc.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                    gc.setStroke(Color.GRAY);
                    gc.strokeRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    @FXML
    private void returnToDatatable(ActionEvent event) {
        try {
            App.setRoot("datatable");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
