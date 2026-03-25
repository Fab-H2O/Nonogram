package fabtimalex.nonogramlourd;

import javafx.beans.property.SimpleStringProperty;

public class PlayerModel {
    private final SimpleStringProperty playerName;
    private final SimpleStringProperty lastLog;
    private final SimpleStringProperty signOut;

    public PlayerModel(String playerName, String lastLog, String signOut) {
        this.playerName = new SimpleStringProperty(playerName);
        this.lastLog = new SimpleStringProperty(lastLog);
        this.signOut = new SimpleStringProperty(signOut);
    }

    public String getPlayerName() {
        return playerName.get();
    }

    public String getLastLog() {
        return lastLog.get();
    }

    public String getSignOut() {
        return signOut.get();
    }
}
