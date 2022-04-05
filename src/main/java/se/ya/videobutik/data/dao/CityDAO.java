package se.ya.videobutik.data.dao;

import se.ya.videobutik.data.DataManagement;
import se.ya.videobutik.model.City;

import java.util.ArrayList;
import java.util.Collection;

public class CityDAO {

    public void AddCity(City city){
        DataManagement data = new DataManagement();
        data.setData(city);
    }
    public City findCity(int id){
        DataManagement data = new DataManagement();
        return (City) data.getData(City.class, id);
    }
    public void deleteCity(City city){
        DataManagement data = new DataManagement();
        data.removeData(city);
    }
    public void updateCity(City city){
        DataManagement data = new DataManagement();
        data.updateData(city);
    }
    public Collection<City> getCityList(){

        Collection<City> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(City.class);
        for (Object x : dataList){
            output.add((City) x);
        }
        return output;
    }

    public Collection<City> getCityList(String text){

        Collection<City> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(City.class, text);
        for (Object x : dataList){
            output.add((City) x);
        }
        return output;
    }
}
