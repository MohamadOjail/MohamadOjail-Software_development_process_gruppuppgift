package se.ya.videobutik.data.dao;

import se.ya.videobutik.data.DataManagement;
import se.ya.videobutik.model.Actor;

import java.util.ArrayList;
import java.util.Collection;

public class ActorDAO {

    public void AddActor(Actor actor){
        DataManagement data = new DataManagement();
        data.setData(Actor.class);
    }
    public Actor findActor(int id){
        DataManagement data = new DataManagement();
        return (Actor) data.getData(Actor.class, id);
    }
    public void deleteActor(Actor actor){
        DataManagement data = new DataManagement();
        data.removeData(actor);
    }
    public void updateActor(Actor actor){
        DataManagement data = new DataManagement();
        data.updateData(Actor.class);
    }
    public Collection<Actor> getActorList(){

        Collection<Actor> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Actor.class);
        for (Object x : dataList){
            output.add((Actor) x);
        }
        return output;
    }

    public Collection<Actor> getActorList(String text){

        Collection<Actor> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Actor.class, text);
        for (Object x : dataList){
            output.add((Actor) x);
        }
        return output;
    }
}
