package se.ya.videobutik.ui.observer.main;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import se.ya.videobutik.ui.Switcher;
import se.ya.videobutik.ui.controller.MainController;

public class ActionObserver implements EventHandler<MouseEvent> {

    private MainController ctrlr;

    public ActionObserver(MainController ctrlr) {
        this.ctrlr = ctrlr;
    }

    @Override
    public void handle(MouseEvent e) {

        if (e.getSource() == ctrlr.getLbl_uthyrning()){
            Switcher.get().loadScene(0, ctrlr.getMain_pane());
        }

        if (e.getSource() == ctrlr.getLbl_film()){
            Switcher.get().loadScene(2, ctrlr.getMain_pane());
        }

        if (e.getSource() == ctrlr.getLbl_customer()){
            Switcher.get().loadScene(1, ctrlr.getMain_pane());
        }

        if (e.getSource() == ctrlr.getLbl_address()){
            Switcher.get().loadScene(3, ctrlr.getMain_pane());
        }

        if (e.getSource() == ctrlr.getLbl_home()){
            Switcher.get().backToMain(ctrlr.getLbl_home());
        }
    }
}
