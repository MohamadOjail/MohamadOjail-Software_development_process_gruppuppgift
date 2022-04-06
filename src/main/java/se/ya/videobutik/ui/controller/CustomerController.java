package se.ya.videobutik.ui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import se.ya.videobutik.data.dao.*;
import se.ya.videobutik.model.*;

import java.time.LocalDateTime;
import java.util.Collection;

public class CustomerController {

    private CustomerDAO customerDAO = new CustomerDAO();
    private AddressDAO addressDAO = new AddressDAO();
    private CityDAO cityDAO = new CityDAO();
    private StoreDAO storeDAO = new StoreDAO();
    private Customer currentCustomer;

    private int storeId;

    // FXML komponenter
    @FXML private Button btn_add_costumer;
    @FXML private Button btn_delete;
    @FXML private Button btn_edit_costumer;
    @FXML private ComboBox<City> cb_add_city;
    @FXML private ComboBox<Country> cb_add_country;
    @FXML private ComboBox<City> cb_edit_city;
    @FXML private CheckBox checkb_add_active;
    @FXML private CheckBox checkb_edit_active;
    @FXML private TableColumn<Customer, Address> col_address;
    @FXML private TableColumn<Customer, City> col_city;
    @FXML private TableColumn<Customer, String> col_first_name;
    @FXML private TableColumn<Customer, Integer> col_id;
    @FXML private TableColumn<Customer, String> col_last_name;
    @FXML private ComboBox<Country> cb_edit_country;
    @FXML private TextField tf_add_address;
    @FXML private TextField tf_add_district;
    @FXML private TextField tf_add_email;
    @FXML private TextField tf_add_first_name;
    @FXML private TextField tf_add_last_name;
    @FXML private TextField tf_add_phone;
    @FXML private TextField tf_add_postal_code;
    @FXML private TextField tf_edit_address;
    @FXML private TextField tf_edit_district;
    @FXML private TextField tf_edit_email;
    @FXML private TextField tf_edit_first_name;
    @FXML private TextField tf_edit_last_name;
    @FXML private TextField tf_edit_phone;
    @FXML private TextField tf_edit_postal_code;
    @FXML private TextField tf_find_last_name;
    @FXML private TableView<Customer> tv_customers;

    private ObservableList<Customer> customers = FXCollections.observableArrayList();

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @FXML private void initialize(){
        tv_customers.setItems(customers);

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_first_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_last_name.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
//        col_city.setCellValueFactory(new PropertyValueFactory<>("id"));

        CountryDAO countryDAO = new CountryDAO();
        Collection<Country> countryList = countryDAO.getCountryList();

        cb_add_country.getItems().clear();
        cb_add_country.getItems().addAll(countryList);

        cb_edit_country.getItems().clear();
        cb_edit_country.getItems().addAll(countryList);

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
                if (t1 != null) {
                    setCustomerList(t1);
                }
            }
        });

        tv_customers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
            @Override
            public void changed(ObservableValue<? extends Customer> observableValue, Customer customer, Customer t1) {
                if (t1 != null){
                    currentCustomer = t1;
                    setEditCustomerSection();
                }
            }
        });
    }

    private void setCustomerList(String input){
        this.customers.clear();
        this.customers.addAll(customerDAO.getCustomerList(input, storeId));
    }

    private void setCityComboItems(Country country, ComboBox<City> cityComboBox){
        Collection<City> cityList = cityDAO.getCityList(country.getId() + "");
        cityComboBox.getItems().clear();
        cityComboBox.getItems().addAll(cityList);
    }

    @FXML void delete(ActionEvent event) {
        System.out.println(storeId);
    }

    @FXML public void Customer_add_button_push(ActionEvent event){
        //address, district,city_id, postal_code,phone
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

        Customer customer = new Customer();
        customer.setFirstName(tf_add_first_name.getText());
        customer.setLastName(tf_add_last_name.getText());
        customer.setEmail(tf_add_email.getText());
        customer.setAddress(address);
        customer.setCreateDate(LocalDateTime.now());
        customer.setActive(checkb_add_active.isSelected());
        Store store = storeDAO.findStore(this.storeId);
        customer.setStore(store);
        customerDAO.AddCustomer(customer);
    }

    private void setEditCustomerSection(){
        cb_edit_country.getSelectionModel().select(currentCustomer.getAddress().getCity().getCountry());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cb_edit_city.getSelectionModel().select(currentCustomer.getAddress().getCity());
        tf_edit_first_name.setText(currentCustomer.getFirstName());
        tf_edit_last_name.setText(currentCustomer.getLastName());
        tf_edit_email.setText(currentCustomer.getEmail());
        tf_edit_address.setText(currentCustomer.getAddress().getAddress());
        tf_edit_district.setText(currentCustomer.getAddress().getDistrict());
        tf_edit_postal_code.setText(currentCustomer.getAddress().getPostalCode());
        tf_edit_phone.setText(currentCustomer.getAddress().getPhone());
        checkb_edit_active.setSelected(currentCustomer.getActive());
    }

    private void clearEditCustomerSection(){
        cb_edit_city.getItems().clear();
        cb_edit_country.getSelectionModel().select(null);

        tf_edit_first_name.clear();
        tf_edit_last_name.clear();
        tf_edit_email.clear();
        tf_edit_address.clear();
        tf_edit_district.clear();
        tf_edit_postal_code.clear();
        tf_edit_phone.clear();
        checkb_edit_active.setSelected(false);
    }

    @FXML public void deleteCustomer(ActionEvent e){
        customerDAO.deleteCustomer(tv_customers.getSelectionModel().getSelectedItem());
        clearEditCustomerSection();
        setCustomerList(tf_find_last_name.getText());
    }

    @FXML public void updateCustomer(ActionEvent e){
        updateAddress();
        currentCustomer.setFirstName(tf_edit_first_name.getText());
        currentCustomer.setLastName(tf_edit_last_name.getText());
        currentCustomer.setEmail(tf_edit_email.getText());
        currentCustomer.setActive(checkb_edit_active.isSelected());
        customerDAO.updateCustomer(currentCustomer);
        clearEditCustomerSection();
        setCustomerList(tf_find_last_name.getText());
    }

    private void updateAddress(){
        currentCustomer.getAddress().setAddress(tf_edit_address.getText());
        currentCustomer.getAddress().setDistrict(tf_edit_district.getText());
        currentCustomer.getAddress().setCity(cb_edit_city.getValue());
        currentCustomer.getAddress().setPostalCode(tf_edit_postal_code.getText());
        currentCustomer.getAddress().setPhone(tf_edit_phone.getText());
        addressDAO.updateAddress(currentCustomer.getAddress());
    }
}

