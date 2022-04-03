package se.ya.videobutik.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import se.ya.videobutik.model.Actor;
import se.ya.videobutik.ui.observer.ChangeObserver;

public class LogInController {

    @FXML private TableColumn<Actor, String> col_first_name;
    @FXML private TableColumn<Actor, Integer> col_id;
    @FXML private TableColumn<Actor, String> col_last_name;
    @FXML private TextField tf_actor;
    @FXML private TableView<Actor> tv_actors;

    ObservableList<Actor> actors = FXCollections.observableArrayList();

    // Getters
    public TableColumn<?, ?> getCol_first_name() {
        return col_first_name;
    }

    public ObservableList<Actor> getActors() {
        return actors;
    }

    public TableColumn<?, ?> getCol_id() {
        return col_id;
    }

    public TableColumn<?, ?> getCol_last_name() {
        return col_last_name;
    }

    public TextField getTf_actor() {
        return tf_actor;
    }

    public TableView<?> getTv_actors() {
        return tv_actors;
    }

    @FXML private void initialize(){

        tv_actors.setItems(actors);
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_first_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_last_name.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        ChangeObserver observer = new ChangeObserver(this);
        tf_actor.textProperty().addListener(observer);
    }
}
