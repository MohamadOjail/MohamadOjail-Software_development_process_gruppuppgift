package se.ya.videobutik.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import se.ya.videobutik.data.dao.StoreDAO;
import se.ya.videobutik.model.LoginStoreSelector;
import se.ya.videobutik.model.Store;
import se.ya.videobutik.ui.observer.login.ActionObserver;

import java.util.ArrayList;
import java.util.Collection;

public class LogInController {

    @FXML private TextField tf_admin_password;
    @FXML private Button btn_admin;
    @FXML private Button btn_salesman;
    @FXML private ComboBox<LoginStoreSelector> cb_store;

    private int storeId;

    public int getStoreId() {
        return cb_store.getSelectionModel().getSelectedItem().getId();
    }

    // Getters
    public Button getBtn_admin() {
        return btn_admin;
    }

    public ComboBox<?> getCb_store() {
        return cb_store;
    }

    public Button getBtn_salesman() {
        return btn_salesman;
    }

    @FXML private void initialize(){
        ActionObserver observer = new ActionObserver(this);
        btn_admin.setOnAction(observer);
        btn_salesman.setOnAction(observer);
        StoreDAO storeDAO = new StoreDAO();
        Collection<Store> storeList = storeDAO.getStoreList();
        Collection<LoginStoreSelector> stores = new ArrayList<>();
        for (Store store : storeList){
            LoginStoreSelector loginStoreSelector = new LoginStoreSelector();
            loginStoreSelector.setId(store.getId());
            loginStoreSelector.setDescription(store.getAddress().getCity().getCity());
            stores.add(loginStoreSelector);
        }

        cb_store.getItems().clear();
        cb_store.getItems().addAll(stores);
    }
}
