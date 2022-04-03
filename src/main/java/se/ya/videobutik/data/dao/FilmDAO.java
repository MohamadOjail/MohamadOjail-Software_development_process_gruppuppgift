package se.ya.videobutik.data.dao;

import se.ya.videobutik.data.DataManagement;
import se.ya.videobutik.model.Film;

import java.util.ArrayList;
import java.util.Collection;

public class FilmDAO {

    public void AddFilm(Film film){
        DataManagement data = new DataManagement();
        data.setData(film);
    }
    public Film findFilm(int id){
        DataManagement data = new DataManagement();
        return (Film) data.getData(Film.class, id);
    }
    public void deleteFilm(Film film){
        DataManagement data = new DataManagement();
        data.removeData(film);
    }
    public void updateFilm(Film film){
        DataManagement data = new DataManagement();
        data.updateData(film);
    }
    public Collection<Film> getFilmList(){

        Collection<Film> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Film.class);
        for (Object x : dataList){
            output.add((Film) x);
        }
        return output;
    }

    public Collection<Film> getFilmList(String text){

        Collection<Film> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Film.class, text);
        for (Object x : dataList){
            output.add((Film) x);
        }
        return output;
    }
}
