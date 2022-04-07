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
import se.ya.videobutik.data.dao.*;
import se.ya.videobutik.model.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

public class StaffController {
    private CityDAO cityDAO = new CityDAO();
    private StoreDAO storeDAO = new StoreDAO();
    private StaffDAO staffDAO = new StaffDAO();
    private Staff staff = new Staff();


    @FXML
    private Button btn_add_employee;

    @FXML
    private Button btn_delete_employee;

    @FXML
    private Button btn_save_employee;

    @FXML
    private ComboBox<City> cb_add_city;

    @FXML
    private ComboBox<City> cb_edit_city;

    @FXML
    private TextField tf_add_address;

    @FXML
    private TextField tf_add_district;

    @FXML
    private TextField tf_add_email;

    @FXML
    private TextField tf_add_first_name;

    @FXML
    private TextField tf_add_last_name;

    @FXML
    private TextField tf_add_phone;

    @FXML
    private ComboBox<Country> cb_add_country;

    @FXML
    private TextField tf_add_postal_code;

    @FXML
    private TextField tf_add_user_name;

    @FXML
    private TextField tf_edit_address;

    @FXML
    private TextField tf_edit_district;

    @FXML
    private TextField tf_edit_email;

    @FXML
    private TextField tf_edit_first_name;

    @FXML
    private TextField tf_edit_last_name;

    @FXML
    private TextField tf_edit_phone;

    @FXML
    private ComboBox<Country> cb_edit_country;

    @FXML
    private TextField tf_edit_postal_code;

    @FXML
    private TextField tf_edit_user_name;

    @FXML
    private TextField tf_find_last_name;

    @FXML
    private TableColumn<Staff, Address> tv_column_address;

    @FXML
    private TableColumn<Staff, String> tv_column_first_name;

    @FXML
    private TableColumn<Staff, Integer> tv_column_id;

    @FXML
    private TableColumn<Staff, String> tv_column_last_name;

    @FXML
    private TableView<Staff> tv_employees;

    @FXML
    void btn_add_employee(ActionEvent event) {

        StaffDAO staffDAO = new StaffDAO();
        AddressDAO addressDAO = new AddressDAO();
        CityDAO cityDAO = new CityDAO();
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


        staff.setFirstName(tf_add_first_name.getText());
        staff.setLastName(tf_add_last_name.getText());
        staff.setEmail(tf_add_email.getText());
        staff.setAddress(address);
        staff.setUsername(tf_add_user_name.getText());
        staff.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        staff.setStore(storeDAO.findStore(1));
        staffDAO.AddStaff(staff);
        clearAddSection();


    }
    private ObservableList <Country> countries = FXCollections.observableArrayList();
    private ObservableList <City> cities = FXCollections.observableArrayList();
    private ObservableList <Staff> staffList = FXCollections.observableArrayList();
    @FXML
    private void initialize(){
        CountryDAO countryDAO = new CountryDAO();

        countries.addAll(countryDAO.getCountryList());

        cb_add_country.setItems((countries));
        cb_add_city.setItems(cities);
        cb_edit_country.setItems(countries);
        cb_edit_city.setItems(cities);
        tv_employees.setItems(staffList);

        tv_column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tv_column_first_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tv_column_last_name.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tv_column_address.setCellValueFactory(new PropertyValueFactory<>("address"));

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

        tf_find_last_name.textProperty().addListener(new ChangeListener<String>() {
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

        tv_employees.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Staff>() {
            @Override
            public void changed(ObservableValue<? extends Staff> observableValue, Staff staff, Staff t1) {
                if(t1!=null){
                    setEditSection(t1);

                }
            }
        });

    }

    private void setEditSection(Staff t1) {
        tf_edit_first_name.setText(t1.getFirstName());
        tf_edit_last_name.setText(t1.getLastName());
        tf_edit_email.setText(t1.getEmail());
        tf_edit_address.setText(t1.getAddress().getAddress());
        tf_edit_district.setText(t1.getAddress().getDistrict());
        cb_edit_country.getSelectionModel().select(t1.getAddress().getCity().getCountry());
        cb_edit_city.getSelectionModel().select(t1.getAddress().getCity());
        tf_edit_postal_code.setText(t1.getAddress().getPostalCode());
        tf_edit_phone.setText(t1.getAddress().getPhone());
        tf_edit_user_name.setText(t1.getUsername());
        this.staff=t1;
    }

    private void setCityComboItems(Country country, ComboBox<City> cityComboBox){
        Collection<City> cityList = cityDAO.getCityList(country.getId() + "");
        cityComboBox.getItems().clear();
        cityComboBox.getItems().addAll(cityList);
    }

    @FXML
    void btn_delete(ActionEvent event) {
        staffDAO.deleteStaff(tv_employees.getSelectionModel().getSelectedItem());
        clearEditSection();
        setStaffList(tf_find_last_name.getText());

    }

    private void setStaffList(String text) {
        this.staffList.clear();
        this.staffList.addAll(staffDAO.getStaffList(text));
    }

    private void clearEditSection() {
        cb_edit_city.getItems().clear();
        cb_edit_country.getSelectionModel().select(null);

        tf_edit_first_name.clear();
        tf_edit_last_name.clear();
        tf_edit_email.clear();
        tf_edit_address.clear();
        tf_edit_district.clear();
        tf_edit_postal_code.clear();
        tf_edit_phone.clear();
        tf_edit_user_name.clear();
        
    }

    private void clearAddSection() {
        cb_add_city.getItems().clear();
        cb_add_country.getSelectionModel().select(null);

        tf_add_first_name.clear();
        tf_add_last_name.clear();
        tf_add_email.clear();
        tf_add_address.clear();
        tf_add_district.clear();
        tf_add_postal_code.clear();
        tf_add_phone.clear();
        tf_add_user_name.clear();
        
    }

    @FXML
    void btn_save_changes(ActionEvent event) {
        AddressDAO addressDAO = new AddressDAO();
        Address address = new Address();
        updateAddress();
        staff.setFirstName(tf_edit_first_name.getText());
        staff.setLastName(tf_edit_last_name.getText());
        staff.setEmail(tf_edit_email.getText());
        staff.setAddress(address);
        staff.setUsername(tf_edit_user_name.getText());
        staff.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        staff.setStore(storeDAO.findStore(1));
        staffDAO.updateStaff(staff);

        clearEditSection();
        setStaffList(tf_find_last_name.getText());


    }

    private void updateAddress(){
        AddressDAO addressDAO = new AddressDAO();

        staff.getAddress().setAddress(tf_edit_address.getText());
        staff.getAddress().setDistrict(tf_edit_district.getText());
        staff.getAddress().setCity(cb_edit_city.getValue());
        staff.getAddress().setPostalCode(tf_edit_postal_code.getText());
        staff.getAddress().setPhone(tf_edit_phone.getText());
      //  addressDAO.updateAddress(staff.getAddress());
    }

}

