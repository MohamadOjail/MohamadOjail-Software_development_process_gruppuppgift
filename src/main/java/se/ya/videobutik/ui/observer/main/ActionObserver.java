package se.ya.videobutik.ui.observer.main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import se.ya.videobutik.ui.BorderPaneLoader;
import se.ya.videobutik.ui.controller.MainController;

public class ActionObserver implements EventHandler<MouseEvent> {

    private MainController ctrlr;

    public ActionObserver(MainController ctrlr) {
        this.ctrlr = ctrlr;
    }

    @Override
    public void handle(MouseEvent e) {
        if (e.getSource() == ctrlr.getLbl_film()){
            BorderPaneLoader.get().loadScene(2);
        }

        if (e.getSource() == ctrlr.getLbl_customer()){
            BorderPaneLoader.get().loadScene(1);
        }

        if (e.getSource() == ctrlr.getLbl_address()){
            BorderPaneLoader.get().loadScene(3);
        }
    }
}
