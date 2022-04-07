package se.ya.videobutik.ui.observer.main;

import javafx.event.EventHandler;
import javafx.scene.Node;
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

        if (e.getSource() == ctrlr.getLbl_rental()){

            Switcher.get().loadScene(0, ctrlr.getMain_pane());
        }

        if (e.getSource() == ctrlr.getLbl_film()){
            Switcher.get().loadScene(2, ctrlr.getMain_pane());
        }

        if (e.getSource() == ctrlr.getLbl_customer()){
            Switcher.get().setStoreId(ctrlr.getStoreId());
            System.out.println(ctrlr.getStoreId());
            Switcher.get().loadScene(1, ctrlr.getMain_pane());
        }

        if (e.getSource() == ctrlr.getLbl_address()){
            Switcher.get().loadScene(3, ctrlr.getMain_pane());
        }

        if (e.getSource() == ctrlr.getLbl_home()){
            Switcher.get().backToMain(ctrlr.getLbl_home());
        }

        if(e.getSource() == ctrlr.getLbl_actor()) {
            Switcher.get().loadScene(6,ctrlr.getMain_pane());
        }

        setStyle(e);
    }

    private void setStyle(MouseEvent e){
        styleIt(ctrlr.getLbl_address(), e.getSource() == ctrlr.getLbl_address());
        styleIt(ctrlr.getLbl_customer(), e.getSource() == ctrlr.getLbl_customer());
        styleIt(ctrlr.getLbl_film(), e.getSource() == ctrlr.getLbl_film());
        styleIt(ctrlr.getLbl_rental(), e.getSource() == ctrlr.getLbl_rental());
    }
    private void styleIt(Node node, boolean selected){
        if (selected) node.setStyle("-fx-background-color: rgba(13,235,83,0.2);");
        else node.setStyle("-fx-background-color: transparent;");
    }
}
