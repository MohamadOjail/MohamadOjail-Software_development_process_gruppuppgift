package se.ya.videobutik.ui.observer.login;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import se.ya.videobutik.ui.controller.LogInController;

import java.io.IOException;

public class ActionObserver implements EventHandler<ActionEvent> {

    private LogInController ctrlr;
    private FXMLLoader loader;
    private Stage stage;
    private Scene scene;
    private Parent root = null;

    public ActionObserver(LogInController ctrlr) {
        this.ctrlr = ctrlr;
    }

    @Override
    public void handle(ActionEvent e) {

        if (e.getSource() == ctrlr.getBtn_admin()){
            loader = new FXMLLoader(getClass().getResource("../../../Admin.fxml"));
            try {
                root = loader.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            setCenter();
        }

        if (e.getSource() == ctrlr.getBtn_salesman()){
            loader = new FXMLLoader(getClass().getResource("../../../main.fxml"));
            try {
                root = loader.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            setCenter();
        }
    }
    private void setCenter(){
        scene = new Scene(root);
        stage = (Stage) ctrlr.getBtn_admin().getScene().getWindow();
        stage.setScene(scene);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }
}
