package se.ya.videobutik.ui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import se.ya.videobutik.data.DataManagement;
import se.ya.videobutik.data.dao.*;
import se.ya.videobutik.model.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

public class StoreController {

    @FXML
    private Button btn_add_store;

    @FXML
    private Button btn_delete_store;

    @FXML
    private Button btn_save_store;

    @FXML
    private ComboBox<City> cb_add_city;

    @FXML
    private ComboBox<Staff> cb_add_manager;

    @FXML
    private ComboBox<Country> cb_add_country;

    @FXML
    private ComboBox<City> cb_edit_city;

    @FXML
    private ComboBox<Staff> cb_edit_manager;

    @FXML
    private ComboBox<Country> cb_edit_country;

    @FXML
    private TextField tf_add_address;

    @FXML
    private TextField tf_add_district;

    @FXML
    private TextField tf_add_phone;

    @FXML
    private TextField tf_add_postal_code;

    @FXML
    private TextField tf_edit_address;

    @FXML
    private TextField tf_edit_district;

    @FXML
    private TextField tf_edit_phone;

    @FXML
    private TextField tf_edit_postal_code;

    @FXML
    private TextField tf_find_store;

    @FXML
    private TableColumn<Store, Address> tv_column_address;

    @FXML
    private TableColumn<Store, Staff> tv_column_chef;

    @FXML
    private TableColumn<Store, Integer> tv_column_id;

    @FXML
    private TableView<Store> tv_stores;

    StoreDAO storeDAO = new StoreDAO();
    AddressDAO addressDAO = new AddressDAO();
    StaffDAO staffDAO = new StaffDAO();
    CityDAO cityDAO = new CityDAO();
    CountryDAO countryDAO = new CountryDAO();
    Address address;
    Store store = new Store();

    @FXML
    void btn_add_store(ActionEvent event) {

        City city = cityDAO.findCity(cb_add_city.getSelectionModel().getSelectedItem().getId());
        Object[] addressData = {
                tf_add_address.getText(),
                tf_add_district.getText(),
                city.getId(),
                tf_add_postal_code.getText(),
                tf_add_phone.getText()
        };
        addressDAO.AddAddress(addressData);

        Object[] addressSearchData = {
                tf_add_address.getText(),
                tf_add_district.getText(),
                city.getId()
        };

        Address address = addressDAO.findAddress(addressSearchData);

        Staff staff = staffDAO.findStaff(cb_add_manager.getSelectionModel().getSelectedItem().getId());
        store.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        store.setManagerStaff(staff);
        store.setAddress(address);
        storeDAO.AddStore(store);
        clearAddSection();

    }

    private ObservableList <Staff> staffList = FXCollections.observableArrayList();
    private ObservableList<Store> stores = FXCollections.observableArrayList();

    @FXML
    private void initialize(){
        Collection<Country> countries = countryDAO.getCountryList();
        staffList.addAll(staffDAO.getStaffList());


        cb_add_manager.getItems().clear();
        cb_add_manager.getItems().addAll(staffList);

        cb_add_country.getItems().clear();
        cb_add_country.getItems().addAll(countries);


        cb_edit_country.getItems().clear();
        cb_edit_country.getItems().addAll(countries);

        cb_edit_manager.getItems().clear();
        cb_edit_manager.getItems().addAll(staffList);

        tv_stores.setItems(stores);

      //  tv_column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        //tv_column_chef.setCellValueFactory(new PropertyValueFactory<>("managerStaff"));
        //tv_column_address.setCellValueFactory(new PropertyValueFactory<>("address"));

        cb_add_country.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Country>() {
            @Override
            public void changed(ObservableValue<? extends Country> observableValue, Country country, Country t1) {
                if (t1 != null) {
                    setCityComboItems(t1, cb_add_city);
                }
            }
        });

        cb_edit_country.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Country>() {
            @Override
            public void changed(ObservableValue<? extends Country> observableValue, Country country, Country t1) {
                if (t1 != null) {
                    setCityComboItems(t1, cb_edit_city);
                }
            }
        });


        tf_find_store.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(t1 != null){
                    staffList.clear();
                    setStaffList(t1);
                }else{
                    staffList.clear();
                }
            }
        });

    }

    private void setStaffList(String t1) {
        this.staffList.clear();
        this.staffList.addAll(staffDAO.getStaffList(t1));
    }

    private void setCityComboItems(Country country, ComboBox<City> cb_add_city) {
        Collection<City> cities = cityDAO.getCityList(country.getId() + "");
        cb_add_city.getItems().clear();
        cb_add_city.getItems().addAll(cities);
    }


    private void clearAddSection() {

        cb_add_manager.getItems().clear();
        cb_add_city.getItems().clear();
        cb_add_country.getItems().clear();

        tf_edit_address.clear();
        tf_edit_district.clear();
        tf_edit_postal_code.clear();
        tf_edit_phone.clear();

    }

    @FXML
    void btn_delete_store(ActionEvent event) {

    }

    @FXML
    void btn_save_store(ActionEvent event) {

    }


}

