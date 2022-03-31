package se.ya.videobutik.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import se.ya.videobutik.ui.controller.MainController;

public class BorderPaneLoader {

    private static final BorderPaneLoader instance = new BorderPaneLoader();
    public static BorderPaneLoader get(){return instance;}
    private BorderPaneLoader() {}

    private MainController mainController;
    public void setMainController(MainController controller){this.mainController = controller;}

    public void loadScene(int id){
        System.out.println("called");
        Parent root = null;
        try {
            switch (id){
                case 1 -> {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../customer.fxml"));
                    root = loader.load();
                }
                case 2 ->{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../movie.fxml"));
                    root = loader.load();
                }
                case 3 ->{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Address.fxml"));
                    root = loader.load();
                }
            }
            mainController.getMain_pane().setCenter(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
