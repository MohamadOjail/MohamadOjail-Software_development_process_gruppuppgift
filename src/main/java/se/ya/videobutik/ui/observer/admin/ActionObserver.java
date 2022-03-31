package se.ya.videobutik.ui.observer.admin;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import se.ya.videobutik.ui.Switcher;
import se.ya.videobutik.ui.controller.AdminController;

public class ActionObserver implements EventHandler<MouseEvent> {

    private AdminController ctrlr;

    public ActionObserver(AdminController ctrlr) {
        this.ctrlr = ctrlr;
    }

    @Override
    public void handle(MouseEvent e) {
        if (e.getSource() == ctrlr.getLbl_address()){
            Switcher.get().loadScene(3, ctrlr.getAdmin_pane());
        }
        if (e.getSource() == ctrlr.getLbl_store()){
            Switcher.get().loadScene(5, ctrlr.getAdmin_pane());
        }
        if (e.getSource() == ctrlr.getLbl_staff()){
            Switcher.get().loadScene(4, ctrlr.getAdmin_pane());
        }
        if (e.getSource() == ctrlr.getLbl_home()){
            Switcher.get().backToMain(ctrlr.getLbl_home());
        }
    }
}
