module com.example.cryptoanalyzer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cryptoanalyzer to javafx.fxml;
    exports com.example.cryptoanalyzer;
    exports com.example.cryptoanalyzer.controller;
    opens com.example.cryptoanalyzer.controller to javafx.fxml;
}