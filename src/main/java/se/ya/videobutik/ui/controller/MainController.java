package se.ya.videobutik.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import se.ya.videobutik.ui.Switcher;
import se.ya.videobutik.ui.observer.main.ActionObserver;

public class MainController {

    public Label lbl_head_titel;
    @FXML private Label lbl_address;
    @FXML private Label lbl_customer;
    @FXML private Label lbl_film;
    @FXML private BorderPane main_pane;
    @FXML private Label lbl_rental;
    @FXML private Label lbl_home;
    @FXML private Label lbl_actor;

    private int storeId;

    public int getStoreId() {
        return storeId;
    }

    // getters

    public Label getLbl_actor() {
        return lbl_actor;
    }
    public Label getLbl_address() {
        return lbl_address;
    }

    public Label getLbl_home() {
        return lbl_home;
    }

    public Label getLbl_rental() {
        return lbl_rental;
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
        lbl_rental.setStyle("-fx-background-color: rgba(13,235,83,0.2);");

        ActionObserver observer = new ActionObserver(this);
        lbl_customer.setOnMouseClicked(observer);
        lbl_film.setOnMouseClicked(observer);
        lbl_address.setOnMouseClicked(observer);
        lbl_rental.setOnMouseClicked(observer);
        lbl_home.setOnMouseClicked(observer);
        lbl_actor.setOnMouseClicked(observer);
        System.out.println(getStoreId());

    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
