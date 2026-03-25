package fabtimalex.nonogramlourd;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DisplayController {

    @FXML
    private void returnToDatatable(ActionEvent event) {
        try {
            App.setRoot("datatable");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
