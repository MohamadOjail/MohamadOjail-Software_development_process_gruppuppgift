package se.ya.videobutik.ui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import se.ya.videobutik.data.dao.AddressDAO;
import se.ya.videobutik.data.dao.CityDAO;
import se.ya.videobutik.model.Address;
import se.ya.videobutik.model.City;


public class AddressController {


    private AddressDAO addressDAO;

    private ObservableList<City> cities = FXCollections.observableArrayList();
    private ObservableList<Address> addressList = FXCollections.observableArrayList();

    @FXML private Button btn_add_address;
    @FXML private Button btn_delete_address;
    @FXML private Button btn_save_address;

    @FXML private ComboBox<City> cb_add_city;
    @FXML private ComboBox<City> cb_edit_city;


    @FXML private TextField tf_add_address;
    @FXML private TextField tf_add_district;
    @FXML private TextField tf_add_phone;
    @FXML private TextField tf_add_postal_code;

    @FXML private TextField tf_find_address;

    @FXML private TableView<Address> tv_addresses;

    @FXML private TableColumn<Address, Integer> tc_address_id;
    @FXML private TableColumn<Address,String > tc_address;
    @FXML private TableColumn<Address, String> tc_city;
    @FXML private TableColumn<Address, String> tc_country;
    @FXML private TableColumn<Address, String> tc_postal_code;

    @FXML private TextField tf_edit_address;
    @FXML private TextField tf_edit_district;
    @FXML private TextField tf_edit_phone;
    @FXML private TextField tf_edit_postal_code;

    @FXML public void initialize() {
        setCb_add_city();
        cb_add_city.setItems(cities);
        cb_edit_city.setItems(cities);

        tv_addresses.setItems(addressList);
//        tc_address_id.setCellValueFactory(new PropertyValueFactory<>("id"));
//        tc_address.setCellValueFactory(new PropertyValueFactory<>("address"));
//
//        tc_postal_code.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        tf_find_address.textProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> observableValue, String s, String t1)
            {addressList.clear();
                findaddress(t1);}
        }
        );
    }

   @FXML void Address_add_button_push(ActionEvent event){

        addressDAO = new AddressDAO();

        String address = String.valueOf(tf_add_address.getText());
        String district = String.valueOf(tf_add_district.getText());
        City city = cb_add_city.getValue();
        String postal_code = String.valueOf(tf_add_postal_code.getText());
        String phone = String.valueOf(tf_add_phone.getText());

       Object[] objectData = {
                address,
                district,
                city,
                postal_code,
                phone
        };
        addressDAO.AddAddress(objectData);
    }


    @FXML void findaddress(String x){
        addressList.addAll(addressDAO.getAddressList(x));
    }

    @FXML void setCb_add_city () {
        CityDAO cityDAO = new CityDAO();
        cities.addAll(cityDAO.getCityList());
    }

    @FXML public void save_address(ActionEvent event){
            Address address = tv_addresses.getSelectionModel().getSelectedItem();
            address.setAddress(tf_edit_address.getText());
            address.setDistrict(tf_edit_district.getText());
            address.setCity(cb_edit_city.getValue());
            address.setPostalCode(tf_edit_postal_code.getText());
            address.setPhone(tf_edit_phone.getText());
            addressDAO.updateAddress(address);
        }

     @FXML public void delete_address(){
        Address address = tv_addresses.getSelectionModel().getSelectedItem();
        addressDAO.deleteAddress(address);

     }
}

