module fabtimalex.nonogramlourd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens fabtimalex.nonogramlourd to javafx.fxml;
    exports fabtimalex.nonogramlourd;
}
