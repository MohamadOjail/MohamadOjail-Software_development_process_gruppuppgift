package se.ya.videobutik.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AddressController {
    @FXML private Button btn_add;
    @FXML private Button btn_delete;
    @FXML private Button btn_edit;
    @FXML private Button btn_select;

    @FXML private ComboBox<?> cb_add_city;
    @FXML private ComboBox<?> cb_add_country;
    @FXML private ComboBox<?> cb_edit_city;
    @FXML private ComboBox<?> cb_edit_country;

    @FXML private ListView<?> lv_address;

    @FXML private TextField tf_add_address;
    @FXML private TextField tf_add_district;
    @FXML private TextField tf_add_postal_code;
    @FXML private TextField tf_address_id;
    @FXML private TextField tf_edit_address;
    @FXML private TextField tf_edit_district;
    @FXML private TextField tf_edit_postal_code;
}
