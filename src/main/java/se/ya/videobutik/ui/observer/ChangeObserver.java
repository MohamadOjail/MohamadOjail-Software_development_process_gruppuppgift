package se.ya.videobutik.ui.observer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import se.ya.videobutik.data.dao.ActorDAO;
import se.ya.videobutik.ui.controller.LogInController;

public class ChangeObserver implements ChangeListener<String> {

    private LogInController ctrlr;

    public ChangeObserver(LogInController ctrlr) {
        this.ctrlr = ctrlr;
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

        if (t1 != null){
            if (!t1.isBlank()){
                ActorDAO actorDAO = new ActorDAO();
                ctrlr.getActors().clear();
                ctrlr.getActors().addAll(actorDAO.getActorList(t1));
            }else ctrlr.getActors().clear();
        }
    }
}
