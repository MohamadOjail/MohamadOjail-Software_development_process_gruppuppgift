package se.ya.videobutik;

import se.ya.videobutik.data.dao.ActorDAO;
import se.ya.videobutik.model.Actor;

public class MainNoGUI {
    public static void main(String[] args) {

        ActorDAO actorDAO = new ActorDAO();
        Actor actor = actorDAO.findActor(2);
        System.out.println(actor.getFirstName());
    }
}
