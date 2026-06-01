package fabtimalex.nonogramlourd;

import javafx.beans.property.SimpleStringProperty;

public class PlayerModel {
    private final int id;
    private final SimpleStringProperty playerName;
    private final SimpleStringProperty lastLog;
    private final SimpleStringProperty signOut;
    private final SimpleStringProperty blocked;

    public PlayerModel(int id, String playerName, String lastLog, String signOut, String blocked) {
        this.id = id;
        this.playerName = new SimpleStringProperty(playerName);
        this.lastLog = new SimpleStringProperty(lastLog);
        this.signOut = new SimpleStringProperty(signOut);
        this.blocked = new SimpleStringProperty(blocked);
    }

    public int getId() {
        return id;
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

    public String getBlocked() {
        return blocked.get();
    }
}
