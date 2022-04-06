package se.ya.videobutik.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import se.ya.videobutik.data.dao.AddressDAO;
import se.ya.videobutik.data.dao.CityDAO;
import se.ya.videobutik.data.dao.CustomerDAO;
import se.ya.videobutik.model.Address;
import se.ya.videobutik.model.City;
import se.ya.videobutik.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {

    private CustomerDAO customerDAO;
    private AddressDAO addressDAO;
    @FXML private Button btn_add_costumer;
    @FXML private Button btn_delete_costumer;
    @FXML private Button btn_edit_costumer;

    @FXML private ComboBox<?> cb_add_city;
    @FXML private ComboBox<?> cb_add_country;
    @FXML private ComboBox<?> cb_edit_city;

    @FXML private CheckBox checkb_add_active;
    @FXML private CheckBox checkb_edit_active;

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

    @FXML private TextField tf_edit_last_name;
    @FXML private TextField tf_edit_phone;
    @FXML private TextField tf_edit_postal_code;
    @FXML private TextField tf_find_last_name;

    @FXML private TableView<?> tv_customers;

    @FXML
    public void Customer_add_button_push(ActionEvent event){


        customerDAO = new CustomerDAO();

        String firstName = String.valueOf(tf_add_first_name.getText());
        String lastName = String.valueOf(tf_add_last_name.getText());
        String email = String.valueOf(tf_add_email.getText());
        String street  = String.valueOf(tf_add_address.getText());
        String district = String.valueOf(tf_add_district.getText());
        // ------------------- > Lägg till comboboxen för POSTORT<--------------
        // ------------------- > Lägg till comboboxen för Country<--------------
        City city = (City) cb_add_city.getValue();
        String postal_code = String.valueOf(tf_add_postal_code.getText());
        String phone = String.valueOf(tf_add_phone.getText());
        boolean active = checkb_add_active.isSelected();

        addressDAO = new AddressDAO();
        Address address = new Address();
        address.setAddress(street);
        address.setPhone(phone);
      //  address.setCity(city);
        address.setPostalCode(postal_code);
        CityDAO cityDAO = new CityDAO();

        address.setCity(cityDAO.findCity(1));
        addressDAO.AddAddress(address);
        System.out.println(address.getId());

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setAddress(address);
        customerDAO.AddCustomer(customer);
    }

}

