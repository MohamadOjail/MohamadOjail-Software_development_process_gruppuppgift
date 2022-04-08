package se.ya.videobutik.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import se.ya.videobutik.data.dao.FilmDAO;
import se.ya.videobutik.data.dao.InventoryDAO;
import se.ya.videobutik.data.dao.LanguageDAO;
import se.ya.videobutik.data.dao.StoreDAO;
import se.ya.videobutik.model.Film;
import se.ya.videobutik.model.Inventory;
import se.ya.videobutik.model.Language;
import se.ya.videobutik.model.Store;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class MovieController {

    private FilmDAO filmDAO = new FilmDAO();
    private LanguageDAO languageDAO = new LanguageDAO();
    private InventoryDAO inventoryDAO = new InventoryDAO();
    private StoreDAO storeDAO = new StoreDAO();

    @FXML private Button btn_add_movie;
    @FXML private Button btn_delete_movie;
    @FXML private Button btn_edit_movie;
    @FXML private ComboBox<Language> cb_add_language;
    @FXML private ComboBox<Language> cb_edit_language;
    @FXML private TableColumn<Film, Integer> column_id;
    @FXML private TableColumn<Film, Integer> column_release;
    @FXML private TableColumn<Film, String> column_title;
    @FXML private TextField tf_add_rental_duration;
    @FXML private TextField tf_add_rental_rate;
    @FXML private TextField tf_add_replacement_cost;
    @FXML private TextField tf_add_title;
    @FXML private TextField tf_edit_rental_duration;
    @FXML private TextField tf_edit_rental_rate;
    @FXML private TextField tf_edit_replacement_cost;
    @FXML private TextField tf_edit_title;
    @FXML private TextField tf_find_title;
    @FXML private TableView<Film> tv_film;

    private ObservableList<Film> filmList = FXCollections.observableArrayList();
    private ObservableList<Language> languageList = FXCollections.observableArrayList();

    @FXML public void initialize() {

        languageList.addAll(languageDAO.getLanguageList());
        tv_film.setItems(filmList);
        column_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        column_release.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));

        cb_add_language.setItems(languageList);
        cb_edit_language.setItems(languageList);

        tf_find_title.textProperty().addListener((observableValue, s, t1) -> {
            filmList.clear();
            searchFilmByTitle(t1);
        });

        tv_film.getSelectionModel().selectedItemProperty().addListener((observableValue, film, t1) -> {
            if (t1 != null) {
                film = t1;
                setEditFilmFields();
            }
        });
    }

    @FXML public void btn_add_movie(ActionEvent event) {
        Film film = new Film();
        film.setTitle(tf_add_title.getText());
        film.setLanguage(cb_add_language.getValue());
        film.setRentalDuration(Integer.valueOf(tf_add_rental_duration.getText()));
        film.setRentalRate(Double.parseDouble(tf_add_rental_rate.getText()));
        film.setReplacementCost(Double.parseDouble(tf_add_replacement_cost.getText()));
        film.setReleaseYear(1922);
        film.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        filmDAO.AddFilm(film);
        Film movie = getMovie(film.getTitle());
        createInventory(movie,1);
        clearAddFilmFields();


        // popup info
        alerter("Kund lagts till databasen", Alert.AlertType.INFORMATION);

    }

    private Film getMovie(String title){
        Object[] object = {title};
        return filmDAO.findFilm(object);
    }

    private void createInventory(Film film, int storeId) {
        Inventory inventory = new Inventory();
        Store store = storeDAO.findStore(storeId);
        inventory.setFilm(film);
        inventory.setStore(store);
        inventory.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        inventoryDAO.AddInventory(inventory);
    }

    @FXML public void btn_delete_movie(ActionEvent event) {
        Film film = tv_film.getSelectionModel().getSelectedItem();
        filmDAO.deleteFilm(film);
        tf_find_title.clear();
        tv_film.getItems().clear();
        clearEditFilmFields();

        // popup info
        alerter("Kund raderades", Alert.AlertType.INFORMATION);

    }

    @FXML public void btn_edit_movie(ActionEvent event) {
        Film film = tv_film.getSelectionModel().getSelectedItem();
        film.setTitle(tf_edit_title.getText());
        film.setLanguage(cb_edit_language.getValue());
        film.setRentalDuration(Integer.valueOf(tf_edit_rental_duration.getText()));
        film.setRentalRate(Double.parseDouble(tf_edit_rental_rate.getText()));
        film.setReplacementCost(Double.parseDouble(tf_edit_replacement_cost.getText()));
        filmDAO.updateFilm(film);

        // popup info
        alerter("Film uppdaterades", Alert.AlertType.INFORMATION);

    }

    private void searchFilmByTitle(String x) {
        filmList.addAll(filmDAO.getFilmList(x));
    }

    private void setEditFilmFields() {
        Film film = tv_film.getSelectionModel().getSelectedItem();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tf_edit_title.setText(film.getTitle());
        cb_edit_language.getSelectionModel().select(film.getLanguage().getId()-1);
        String rentalDuration = String.valueOf(film.getRentalDuration());
        String rentalRate = String.valueOf(film.getRentalRate());
        String replacementCost = String.valueOf(film.getReplacementCost());
        tf_edit_rental_duration.setText(rentalDuration);
        tf_edit_rental_rate.setText(rentalRate);
        tf_edit_replacement_cost.setText(replacementCost);
    }

    private void clearEditFilmFields() {
        tf_edit_title.clear();
        cb_edit_language.getSelectionModel().select(null);
        tf_edit_rental_duration.clear();
        tf_edit_rental_rate.clear();
        tf_edit_replacement_cost.clear();
    }

    private void clearAddFilmFields() {
        tf_add_title.clear();
        cb_add_language.getSelectionModel().select(null);
        tf_add_rental_duration.clear();
        tf_add_rental_rate.clear();
        tf_add_replacement_cost.clear();
    }


    private void alerter(String text, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setHeaderText(type.name());
        alert.setContentText(text);
        alert.showAndWait();
    }


}