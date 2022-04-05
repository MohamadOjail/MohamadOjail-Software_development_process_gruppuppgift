package se.ya.videobutik.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import se.ya.videobutik.data.dao.AddressDAO;
import se.ya.videobutik.model.Address;
import se.ya.videobutik.model.City;
import se.ya.videobutik.model.Country;

public class AddressController {

    private Address address;
    private AddressDAO addressDAO;

    @FXML private Button btn_add;
    @FXML private Button btn_delete;
    @FXML private Button btn_edit;
    @FXML private Button btn_select;

    @FXML private ComboBox<City> cb_add_city;
    @FXML private ComboBox<Country> cb_add_country;
    @FXML private ComboBox<?> cb_edit_city;

    @FXML private ListView<?> lv_address;

    @FXML private TextField tf_add_address;
    @FXML private TextField tf_add_district;
    @FXML private TextField tf_add_postal_code;
    @FXML private TextField tf_address_id;
    @FXML private TextField tf_edit_address;
    @FXML private TextField tf_edit_district;
    @FXML private TextField tf_edit_postal_code;

    public void Address_add_button_push(javafx.scene.input.MouseEvent mouseEvent){

        address = new Address();
        addressDAO = new AddressDAO();

        address.setAddress(String.valueOf(tf_add_address.getText()));
        address.setDistrict(String.valueOf(tf_add_district.getText()));
        address.setCity(cb_add_city.getValue());
        address.setPostalCode(String.valueOf(tf_add_postal_code.getText()));
        address.setPhone(String.valueOf(tf_add_phone.getText()));

        addressDAO.AddAddress(address);


    }



}
