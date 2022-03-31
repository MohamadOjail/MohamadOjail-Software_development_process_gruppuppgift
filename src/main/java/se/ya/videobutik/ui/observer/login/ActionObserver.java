package se.ya.videobutik.ui.observer.login;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import se.ya.videobutik.ui.controller.LogInController;
import se.ya.videobutik.ui.SceneSwitcher;

public class ActionObserver implements EventHandler<ActionEvent> {

    private LogInController ctrlr;

    public ActionObserver(LogInController ctrlr) {
        this.ctrlr = ctrlr;
    }

    @Override
    public void handle(ActionEvent e) {

        if (e.getSource() == ctrlr.getBtn_admin()){
            SceneSwitcher.get().switchScene((Node) ctrlr.getBtn_salesman(), 2);
        }

        if (e.getSource() == ctrlr.getBtn_salesman()){
            SceneSwitcher.get().switchScene((Node) ctrlr.getBtn_salesman(), 1);
        }
    }
}
