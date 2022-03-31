package se.ya.videobutik.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CustomerController {

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_select;

    @FXML
    private Button btn_update;

    @FXML
    private ComboBox<?> cb_active;

    @FXML
    private ComboBox<?> cb_address;

    @FXML
    private ListView<?> lv_customers;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_firstname;

    @FXML
    private TextField tf_lastname;

}

