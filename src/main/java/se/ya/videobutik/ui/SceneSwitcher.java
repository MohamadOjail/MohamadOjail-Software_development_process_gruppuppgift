package se.ya.videobutik.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {

    private static SceneSwitcher instance = new SceneSwitcher();
    public static SceneSwitcher get(){return instance;}
    private SceneSwitcher() {}

    public void switchScene(Node node, int id){

        FXMLLoader loader = null;
        Stage stage = null;
        Scene scene = null;
        Parent root = null;
        try {
            switch (id){
                case 1 ->{
                    loader = new FXMLLoader(getClass().getResource("../main.fxml"));
                    root = loader.load();
                }
                case 2 ->{
                    loader = new FXMLLoader(getClass().getResource("../Admin.fxml"));
                    root = loader.load();
                }
                case 3 ->{
                    //TODO load Customer
                }
                case 4 ->{
                    //TODO load Address
                }

            }
            scene = new Scene(root);
            stage = (Stage) node.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
