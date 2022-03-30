module se.ya.videobutik {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.ya.videobutik to javafx.fxml;
    exports se.ya.videobutik;
    exports se.ya.videobutik.ui.controller;
    opens se.ya.videobutik.ui.controller to javafx.fxml;
}