package se.ya.videobutik.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ActorController {

    @FXML
    private Button btn_add_actor;

    @FXML
    private Button btn_delete_actor;

    @FXML
    private Button btn_save_actor;

    @FXML
    private Button btn_select_actor;

    @FXML
    private TableColumn<?, ?> column_first_name;

    @FXML
    private TableColumn<?, ?> column_id;

    @FXML
    private TableColumn<?, ?> column_last_name;

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
    private TableView<?> tv_actor;

    @FXML
    void btn_add_actor(ActionEvent event) {

    }

    @FXML
    void btn_delete_actor(ActionEvent event) {

    }

    @FXML
    void btn_select_actor(ActionEvent event) {

    }

}

