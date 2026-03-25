package fabtimalex.nonogramlourd;

import javafx.beans.property.SimpleStringProperty;

public class ScoreModel {
    private final SimpleStringProperty playerName;
    private final SimpleStringProperty score;
    private final SimpleStringProperty puzzleId;

    public ScoreModel(String playerName, String score, String puzzleId) {
        this.playerName = new SimpleStringProperty(playerName);
        this.score = new SimpleStringProperty(score);
        this.puzzleId = new SimpleStringProperty(puzzleId);
    }

    public String getPlayerName() {
        return playerName.get();
    }

    public String getScore() {
        return score.get();
    }

    public String getPuzzleId() {
        return puzzleId.get();
    }
}
