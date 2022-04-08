package se.ya.videobutik.ui.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import se.ya.videobutik.data.dao.*;
import se.ya.videobutik.model.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RentalController {

    RentalDAO rentalDAO = new RentalDAO();
    CustomerDAO customerDAO = new CustomerDAO();
    FilmDAO filmDAO = new FilmDAO();
    StaffDAO staffDAO = new StaffDAO();
    StoreDAO storeDAO = new StoreDAO();

    @FXML private TableColumn<Customer, Address> col_address_cust;
    @FXML private TableColumn<Film, Double> col_cost;
    @FXML private TableColumn<Customer, String> col_first_name_cust;
    @FXML private TableColumn<Customer, Integer> col_id_custo;
    @FXML private TableColumn<Customer, String> col_last_name_cust;
    @FXML private TableColumn<Rental, String> col_rent_date;
    @FXML private TableColumn<Rental, Inventory> col_rent_title;
    @FXML private TableColumn<Rental, String> col_return_date;
    @FXML private TableColumn<Film, String> col_title;
    @FXML private TableColumn<Film, Integer> col_year;
    @FXML private TextField tf_find_last_name, tf_find_title;
    @FXML private TableView<Customer> tv_customers;
    @FXML private TableView<Rental> tv_rental;
    @FXML private TableView<Film> tv_film;

    private final ObservableList<Rental> rentalList = FXCollections.observableArrayList();
    private final ObservableList<Film> filmList = FXCollections.observableArrayList();
    private final ObservableList<Customer> customerList = FXCollections.observableArrayList();

    private Customer currentCustomer;


    @FXML private void initialize() {

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

        col_rent_date.setCellValueFactory(rental -> {
            SimpleStringProperty property = new SimpleStringProperty();
            LocalDateTime rentalDate = rental.getValue().getRentalDate();
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("hh:mm");
            property.setValue(rentalDate.format(dtf1) + "  kl: " + rentalDate.format(dtf2));
            return property;
        });
        col_rent_title.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        col_return_date.setCellValueFactory(rental -> {
            SimpleStringProperty property = new SimpleStringProperty();
            LocalDateTime returnlDate = rental.getValue().getReturnDate();
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("hh:mm");
            if (returnlDate != null) {
                property.setValue(returnlDate.format(dtf1) + "  kl: " + returnlDate.format(dtf2));
            }else property.setValue("");
            return property;
        });

        tf_find_title.textProperty().addListener((observableValue, s, t1) -> {
            if (t1 != null && !t1.isEmpty()) {
                filmList.clear();
                filmList.addAll(filmDAO.getFilmList(t1));
            }else filmList.clear();
        });

        tf_find_last_name.textProperty().addListener((observableValue, s, t1) -> {
            if (t1 != null && !t1.isEmpty()) {
                customerList.clear();
                customerList.addAll(customerDAO.getCustomerList(t1));
            } else customerList.clear();
        });

        tv_customers.getSelectionModel().selectedItemProperty().addListener((observableValue, customer, t1) -> {
            if (t1 != null) {
                currentCustomer = t1;
                rentalList.clear();
                rentalList.addAll(tv_customers.getSelectionModel().getSelectedItem().getRentals());
            } else rentalList.clear();
        });

    }

    @FXML void btn_rent_film(ActionEvent event) {
        Film film = tv_film.getSelectionModel().getSelectedItem();
        Staff staff = staffDAO.findStaff(1);

        Rental rental = new Rental();
        rental.setRentalDate(LocalDateTime.now());
        rental.setCustomer(currentCustomer);
        try {
            rental.setInventory(film.getInventories().stream().findFirst().get());
        } catch (Exception e) {
            alerter(e.getMessage(), Alert.AlertType.ERROR);
        }

        rental.setStaff(staff);
        rental.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        rentalDAO.AddRental(rental);

        String message = "Movie: " + film.getTitle() + "\nuthyrd till: " + currentCustomer.getFirstName() + " " + currentCustomer.getLastName();
        alerter(message, Alert.AlertType.INFORMATION);

        rentalList.clear();
        currentCustomer = customerDAO.findCustomer(currentCustomer.getId());
        rentalList.addAll(currentCustomer.getRentals());
    }

    private Film getInventory(String title) {
        Object[] object = {title};
        return filmDAO.findFilm(object);
    }

    @FXML void btn_return_film(ActionEvent event) {
        Rental rental = tv_rental.getSelectionModel().getSelectedItem();
        System.out.println("hej hej");
        if (rental.getReturnDate() == null) {
            rental.setReturnDate(LocalDateTime.now());
            rentalDAO.updateRental(rental);
            tv_rental.refresh();
        }

    }
    private void alerter(String text, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setHeaderText(type.name());
        alert.setContentText(text);
        alert.showAndWait();
    }
}

