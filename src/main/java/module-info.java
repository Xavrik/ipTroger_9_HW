module com.itproger_9_hw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.itproger_9_hw to javafx.fxml;
    exports com.itproger_9_hw;
}