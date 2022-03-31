package se.ya.videobutik.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import se.ya.videobutik.ui.observer.login.ActionObserver;

public class LogInController {

    @FXML private Button btn_admin;
    @FXML private Button btn_salesman;

    // Getters
    public Button getBtn_admin() {
        return btn_admin;
    }

    public Button getBtn_salesman() {
        return btn_salesman;
    }

    @FXML private void initialize(){
        ActionObserver observer = new ActionObserver(this);
        btn_admin.setOnAction(observer);
        btn_salesman.setOnAction(observer);
    }
}
