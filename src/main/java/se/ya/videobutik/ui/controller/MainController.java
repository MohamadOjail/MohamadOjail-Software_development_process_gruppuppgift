package se.ya.videobutik.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import se.ya.videobutik.ui.Switcher;
import se.ya.videobutik.ui.observer.main.ActionObserver;

public class MainController {

    @FXML private Label lbl_address;
    @FXML private Label lbl_customer;
    @FXML private Label lbl_film;
    @FXML private BorderPane main_pane;
    @FXML private Label lbl_uthyrning;
    @FXML private Label lbl_home;

    // getters
    public Label getLbl_address() {
        return lbl_address;
    }

    public Label getLbl_home() {
        return lbl_home;
    }

    public Label getLbl_uthyrning() {
        return lbl_uthyrning;
    }

    public Label getLbl_customer() {
        return lbl_customer;
    }

    public Label getLbl_film() {
        return lbl_film;
    }

    public BorderPane getMain_pane() {
        return main_pane;
    }

    @FXML private void initialize(){
        Switcher.get().loadScene(0, main_pane);

        ActionObserver observer = new ActionObserver(this);
        lbl_customer.setOnMouseClicked(observer);
        lbl_film.setOnMouseClicked(observer);
        lbl_address.setOnMouseClicked(observer);
        lbl_uthyrning.setOnMouseClicked(observer);
        lbl_home.setOnMouseClicked(observer);
    }
}
