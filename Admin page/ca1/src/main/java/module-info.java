module com.example.ca1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires okhttp3;
    exports controller;
    exports model;
    opens controller to javafx.fxml;
}