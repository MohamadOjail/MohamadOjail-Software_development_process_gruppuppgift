package se.ya.videobutik.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import se.ya.videobutik.data.dao.AddressDAO;
import se.ya.videobutik.data.dao.CityDAO;
import se.ya.videobutik.model.Address;
import se.ya.videobutik.model.City;




public class AddressController {

    private Address address;
    private AddressDAO addressDAO;

    private CityDAO cityDAO;

    ObservableList<City> cities = FXCollections.observableArrayList();


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


    public void Address_add_button_push(javafx.scene.input.MouseEvent mouseEvent){


        addressDAO = new AddressDAO();

        String address = String.valueOf(tf_add_address.getText());
        String district = String.valueOf(tf_add_district.getText());
        City city = cb_add_city.getValue();
        String postal_code = String.valueOf(tf_add_postal_code.getText());
        String phone = String.valueOf(tf_add_phone.getText());

       Object[] objectData = {
               address,
               district,
               5,           //TODO tabort när det går att välja city i CB
               //city,    TODO läsa in city till combobox för att kunna välja
                postal_code,
                phone
        };
        addressDAO.AddAddress(objectData);
    }



  /*  @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxCity();
        cb_add_city.setItems(cities);


    }


   public void comboBoxCity(){
        cityDAO = new CityDAO();

       Collection<City> citys = new ArrayList<>();

       citys.addAll(cityDAO.getCityList());

       for (City c: citys
            ) {
           cities.add(c);
       }
    }*/

}
