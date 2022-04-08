package se.ya.videobutik.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jetbrains.annotations.NotNull;
import se.ya.videobutik.data.dao.AddressDAO;
import se.ya.videobutik.data.dao.CityDAO;
import se.ya.videobutik.data.dao.CountryDAO;
import se.ya.videobutik.model.Address;
import se.ya.videobutik.model.City;
import se.ya.videobutik.model.Country;

public class AddressController {

    @FXML private ComboBox<City> cb_add_city, cb_edit_city;
    @FXML private ComboBox<Country> cb_add_land, cb_edit_land;

    @FXML private TextField tf_add_address, tf_add_district, tf_add_phone,
            tf_add_postal_code, tf_edit_address, tf_edit_district,
            tf_edit_phone, tf_edit_postal_code, tf_find_address;

    @FXML private TableView<Address> tv_addresses;
    @FXML private TableColumn<Address, City> col_city;
    @FXML private TableColumn<Address, Integer> col_id;
    @FXML private TableColumn<Address, String> col_street;
    @FXML private TableColumn<Address, String> col_zip;

    private final ObservableList<Country> countries = FXCollections.observableArrayList();
    private final ObservableList<City> citiesAdd = FXCollections.observableArrayList();
    private final ObservableList<City> citiesEdit = FXCollections.observableArrayList();
    private final ObservableList<Address> addresses = FXCollections.observableArrayList();

    private Address currentAddress;

    private final CountryDAO countryDAO = new CountryDAO();
    private final CityDAO cityDAO = new CityDAO();
    private final AddressDAO addressDAO = new AddressDAO();

    @FXML private void initialize(){

        setupAddressTableView();

        cb_add_city.setItems(citiesAdd);
        cb_edit_city.setItems(citiesEdit);
        tv_addresses.setItems(addresses);

        countries.clear();
        countries.addAll(countryDAO.getCountryList());

        cb_add_land.setItems(countries);
        cb_edit_land.setItems(countries);

        cb_add_land.getSelectionModel().selectedItemProperty().addListener((observableValue, country, t1) -> {
            if (t1 != null){
                citiesAdd.clear();
                citiesAdd.addAll(cityDAO.getCityList(t1.getId() + ""));
            }else citiesAdd.clear();
        });

        cb_edit_land.getSelectionModel().selectedItemProperty().addListener((observableValue, country, t1) -> {
            if (t1 != null){
                citiesEdit.clear();
                citiesEdit.addAll(cityDAO.getCityList(t1.getId() + ""));
            }else citiesEdit.clear();
        });

        tf_find_address.textProperty().addListener((observableValue, s, t1) -> {
            if (t1 != null && !t1.isEmpty()){
                addresses.clear();
                addresses.addAll(addressDAO.getAddressList(t1));
            }else addresses.clear();
        });

        tv_addresses.getSelectionModel().selectedItemProperty().addListener((observableValue, address, t1) -> {
            if (t1 != null){
                currentAddress = t1;
                setEditSectionFields(t1);
            }else clearEditSelection();
        });
    }

    private void clearEditSelection() {
        tf_edit_address.clear();
        tf_edit_district.clear();
        tf_edit_postal_code.clear();
        tf_edit_phone.clear();
        cb_edit_land.getSelectionModel().select(null);
        citiesEdit.clear();
    }

    private void clearAddSelection() {
        tf_add_address.clear();
        tf_add_district.clear();
        tf_add_postal_code.clear();
        tf_add_phone.clear();
        cb_add_land.getSelectionModel().select(null);
        citiesAdd.clear();
    }

    private void setEditSectionFields(@NotNull Address address) {
        tf_edit_address.setText(address.getAddress());
        tf_edit_district.setText(address.getDistrict());
        tf_edit_postal_code.setText(address.getPostalCode());
        tf_edit_phone.setText(address.getPhone());
        City city = address.getCity();
        Country country = countryDAO.findCountry(city.getCountry().getId());
        cb_edit_land.getSelectionModel().select(country);
        cb_edit_city.getSelectionModel().select(city);

    }

    private void setupAddressTableView() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_street.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
        col_zip.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
    }

    @FXML void createNewAddress() {
        //address, district,city_id, postal_code,phone
        Object[] objectData = {
                tf_add_address.getText(),
                tf_add_district.getText(),
                cb_add_city.getSelectionModel().getSelectedItem().getId(),
                tf_add_postal_code.getText(),
                tf_add_phone.getText()
        };
        addressDAO.AddAddress(objectData);
        alerter("Ny adress lagts till i databasen", Alert.AlertType.INFORMATION);
        clearAddSelection();
    }

    @FXML void delete_address() {
        addressDAO.deleteAddress(currentAddress);
        alerter("adress raderades", Alert.AlertType.WARNING);
        currentAddress = null;
        clearEditSelection();
        addresses.clear();
        addresses.addAll(addressDAO.getAddressList(tf_find_address.getText()));
    }

    @FXML void save_address() {

        currentAddress.setAddress(tf_edit_address.getText());
        currentAddress.setDistrict(tf_edit_district.getText());
        currentAddress.setCity(cb_edit_city.getSelectionModel().getSelectedItem());
        currentAddress.setPostalCode(tf_edit_postal_code.getText());
        currentAddress.setPhone(tf_edit_phone.getText());

        addressDAO.updateAddress(currentAddress);
        alerter("adress uppdaterad", Alert.AlertType.INFORMATION);
        currentAddress = null;
        clearEditSelection();
        addresses.clear();
        addresses.addAll(addressDAO.getAddressList(tf_find_address.getText()));
    }


    private void alerter(String text, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setHeaderText(text);
        alert.setContentText(null);
        alert.showAndWait();
    }
}

