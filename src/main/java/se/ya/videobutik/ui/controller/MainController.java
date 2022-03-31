package se.ya.videobutik.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import se.ya.videobutik.ui.BorderPaneLoader;
import se.ya.videobutik.ui.observer.main.ActionObserver;

public class MainController {

    @FXML private Label lbl_address;
    @FXML private Label lbl_customer;
    @FXML private Label lbl_film;
    @FXML private BorderPane main_pane;

    // getters
    public Label getLbl_address() {
        return lbl_address;
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
        BorderPaneLoader.get().setMainController(this);
        BorderPaneLoader.get().loadScene(1);

        ActionObserver observer = new ActionObserver(this);
        lbl_customer.setOnMouseClicked(observer);
        lbl_film.setOnMouseClicked(observer);
        lbl_address.setOnMouseClicked(observer);
    }
}
