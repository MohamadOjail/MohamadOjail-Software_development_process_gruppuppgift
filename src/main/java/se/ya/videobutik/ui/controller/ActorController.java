package se.ya.videobutik.ui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import se.ya.videobutik.data.dao.ActorDAO;
import se.ya.videobutik.model.Actor;

/*
        TODO: Göra klart add, delete och update metod. Testa.
 */

public class ActorController {

    private ActorDAO actorDAO = new ActorDAO();

    @FXML
    private Button btn_add_actor;
    @FXML
    private Button btn_delete_actor;
    @FXML
    private Button btn_save_actor;
    @FXML
    private TableColumn<Actor, String> column_first_name;
    @FXML
    private TableColumn<Actor, Integer> column_id;
    @FXML
    private TableColumn<Actor, String> column_last_name;
    @FXML
    private TextField tf_add_first_name;
    @FXML
    private TextField tf_add_last_name;
    @FXML
    private TextField tf_edit_first_name;
    @FXML
    private TextField tf_edit_last_name;
    @FXML
    private TextField tf_find_last_name;
    @FXML
    private TableView<Actor> tv_actor;

    private ObservableList<Actor> actorList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        tv_actor.setItems(actorList);
        column_first_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        column_last_name.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        column_id.setCellValueFactory(new PropertyValueFactory<>("actorId"));
        tf_find_last_name.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                actorList.clear();
               findActorByLastName(t1);
            }
        });
    }

    @FXML
    void btn_add_actor(ActionEvent event) {
        Actor actor = new Actor();
        actor.setFirstName(tf_add_first_name.getText());
        actor.setLastName(tf_add_last_name.getText());
        actorDAO.AddActor(actor);
    }

    @FXML
    void btn_delete_actor(ActionEvent event) {
        Actor actor = tv_actor.getSelectionModel().getSelectedItem();
        actorDAO.deleteActor(actor);
    }

    @FXML
    void btn_save_actor(ActionEvent event) {
        Actor actor = tv_actor.getSelectionModel().getSelectedItem();
        actor.setFirstName(tf_edit_first_name.getText());
        actor.setLastName(tf_edit_last_name.getText());
        actorDAO.updateActor(actor);
    }

    public void findActorByLastName(String input) {
        actorList.addAll(actorDAO.getActorList(input));
    }
}

