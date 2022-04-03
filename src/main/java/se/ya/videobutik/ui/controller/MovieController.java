package se.ya.videobutik.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MovieController {
    @FXML private Button btn_add;
    @FXML private Button btn_delete;
    @FXML private Button btn_edit;
    @FXML private Button btn_select;

    @FXML private ComboBox<?> cb_add_language;
    @FXML private ComboBox<?> cb_edit_language;

    @FXML private ListView<?> lv_films;

    @FXML private TextField tf_add_rental_duration;
    @FXML private TextField tf_add_rental_rate;
    @FXML private TextField tf_add_replacement_cost;
    @FXML private TextField tf_add_title;
    @FXML private TextField tf_edit_rental_duration;
    @FXML private TextField tf_edit_rental_rate;
    @FXML private TextField tf_edit_title;
    @FXML private TextField tf_film_id;
    @FXML private TextField tf_replacement_cost;


}
