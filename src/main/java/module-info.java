module org.example.tap_laborator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.example.tap_laborator5 to javafx.fxml;
    exports org.example.tap_laborator5;
}