package se.ya.videobutik.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import se.ya.videobutik.data.dao.*;
import se.ya.videobutik.model.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RentalController {

    RentalDAO rentalDAO = new RentalDAO();
    CustomerDAO customerDAO = new CustomerDAO();
    FilmDAO filmDAO = new FilmDAO();
    StaffDAO staffDAO = new StaffDAO();
    StoreDAO storeDAO = new StoreDAO();


    @FXML
    private TableColumn<Customer, Address> col_address_cust;

    @FXML
    private TableColumn<Film, Double> col_cost;

    @FXML
    private TableColumn<Customer, String> col_first_name_cust;

    @FXML
    private TableColumn<Customer, Integer> col_id_custo;

    @FXML
    private TableColumn<Customer, String> col_last_name_cust;

    @FXML
    private TableColumn<Rental, LocalDateTime> col_rent_date;

    @FXML
    private TableColumn<Rental, Inventory> col_rent_title;

    @FXML
    private TableColumn<Rental, LocalDateTime> col_return_date;

    @FXML
    private TableColumn<Film, String> col_title;

    @FXML
    private TableColumn<Film, Integer> col_year;

    @FXML
    private TextField tf_find_last_name;

    @FXML
    private TextField tf_find_title;

    @FXML
    private TableView<Customer> tv_customers;

    @FXML
    private TableView<Rental> tv_rental;

    @FXML
    private TableView<Film> tv_film;

    private ObservableList<Rental> rentalList = FXCollections.observableArrayList();
    private ObservableList<Film> filmList = FXCollections.observableArrayList();
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        tv_customers.setItems(customerList);
        tv_film.setItems(filmList);
        tv_rental.setItems(rentalList);

        col_address_cust.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_id_custo.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_first_name_cust.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_last_name_cust.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        col_cost.setCellValueFactory(new PropertyValueFactory<>("rentalRate"));
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_year.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));

        col_rent_date.setCellValueFactory(new PropertyValueFactory<>("rentalDate"));
        col_rent_title.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        col_return_date.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        tf_find_title.textProperty().addListener((observableValue, s, t1) -> {
            filmList.clear();
            searchFilmByTitle(t1);
        });

        tf_find_last_name.textProperty().addListener((observableValue, s, t1) -> {
            if (t1 != null) {
                customerList.clear();
                searchCustomerByLastName(t1);
            }
        });

        tv_customers.getSelectionModel().selectedItemProperty().addListener((observableValue, customer, t1) -> {
            if (t1 != null) {
                rentalList.clear();
                setUpRentTable();
            }
            else {
                rentalList.clear();
            }
        });

    }

    @FXML
    void btn_rent_film(ActionEvent event) {
        Film film = tv_film.getSelectionModel().getSelectedItem();
        Customer customer = tv_customers.getSelectionModel().getSelectedItem();
        Staff staff = staffDAO.findStaff(1);

        Rental rental = new Rental();
        rental.setRentalDate(LocalDateTime.now());
        rental.setCustomer(customer);
        rental.setInventory(film.getInventories().stream().findFirst().get());
        rental.setStaff(staff);
        rental.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        rentalDAO.AddRental(rental);
        System.out.println(tv_customers.getSelectionModel().getSelectedItem());
//        rentalList.clear();
//        rentalList.addAll(tv_customers.getSelectionModel().getSelectedItem().getRentals());
//        setUpRentTable();
    }

    private Film getInventory(String title){
        Object[] object = {title};
        return filmDAO.findFilm(object);
    }

    @FXML
    void btn_return_film(ActionEvent event) {
        Rental rental = tv_rental.getSelectionModel().getSelectedItem();
        System.out.println("hej hej");
        if (rental.getReturnDate() == null) {
            rental.setReturnDate(LocalDateTime.now());
            rentalDAO.updateRental(rental);
            tv_rental.refresh();
        }

    }

    private void searchFilmByTitle(String x) {
        filmList.addAll(filmDAO.getFilmList(x));
    }

    private void searchCustomerByLastName(String x) {
        customerList.addAll(customerDAO.getCustomerList(x));
    }

    private void setUpRentTable() {
        rentalList.addAll(tv_customers.getSelectionModel().getSelectedItem().getRentals());
    }

}
