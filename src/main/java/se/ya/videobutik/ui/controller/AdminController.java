package se.ya.videobutik.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import se.ya.videobutik.ui.Switcher;
import se.ya.videobutik.ui.observer.admin.ActionObserver;

public class AdminController {

    @FXML private Label lbl_staff;
    @FXML private Label lbl_store;
    @FXML private BorderPane admin_pane;
    @FXML private Label lbl_home;

    // Getters
    public Label getLbl_home() {
        return lbl_home;
    }
    public Label getLbl_staff() {
        return lbl_staff;
    }
    public Label getLbl_store() {
        return lbl_store;
    }
    public BorderPane getAdmin_pane() {
        return admin_pane;
    }

    @FXML private void initialize(){

        Switcher.get().loadScene(4, admin_pane);
        ActionObserver observer = new ActionObserver(this);
        lbl_staff.setOnMouseClicked(observer);
        lbl_store.setOnMouseClicked(observer);
        lbl_home.setOnMouseClicked(observer);
    }
}
