package se.ya.videobutik.ui;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import se.ya.videobutik.ui.controller.CustomerController;
import se.ya.videobutik.ui.controller.MainController;

import java.io.IOException;

public class Switcher {

    private static final Switcher instance = new Switcher();
    public static Switcher get(){return instance;}
    private Switcher() {}

    public void backToMain(Node node){

        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../login.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MainController mainController;
    private int storeId;

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
//        this.storeId = mainController.getStore();
    }

    public void loadScene(int id, BorderPane borderPane){
        Parent root = null;
        try {
            switch (id){
                case 0 -> {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../rental.fxml"));
                    root = loader.load();
                }
                case 1 -> {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../customer.fxml"));
                    root = loader.load();
                    CustomerController customerController = loader.getController();
                    customerController.setStoreId(this.storeId);
                }
                case 2 ->{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../movie.fxml"));
                    root = loader.load();
                }
                case 3 ->{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Address.fxml"));
                    root = loader.load();
                }
                case 4 ->{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Staff.fxml"));
                    root = loader.load();
                }
                case 5 ->{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Store.fxml"));
                    root = loader.load();
                }
                case 6 ->{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../actor.fxml"));
                    root = loader.load();
                }
            }
            borderPane.setCenter(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
