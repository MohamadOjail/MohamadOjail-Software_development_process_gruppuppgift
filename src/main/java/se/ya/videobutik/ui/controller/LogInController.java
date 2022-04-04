package se.ya.videobutik.ui.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import se.ya.videobutik.ui.observer.login.ActionObserver;

public class LogInController {

    public TextField tf_admin_password;
    @FXML private Button btn_admin;
    @FXML private Button btn_salesman;
    @FXML private ComboBox<?> cb_store;


    // Getters
    public Button getBtn_admin() {
        return btn_admin;
    }

    public ComboBox<?> getCb_store() {
        return cb_store;
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
