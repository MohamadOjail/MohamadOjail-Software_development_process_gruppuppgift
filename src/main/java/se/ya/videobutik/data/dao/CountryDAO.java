package se.ya.videobutik.data.dao;

import se.ya.videobutik.data.DataManagement;
import se.ya.videobutik.model.Country;

import java.util.ArrayList;
import java.util.Collection;

public class CountryDAO {

    public void AddCountry(Country country){
        DataManagement data = new DataManagement();
        data.setData(country);
    }
    public Country findCountry(int id){
        DataManagement data = new DataManagement();
        return (Country) data.getData(Country.class, id);
    }
    public void deleteCountry(Country country){
        DataManagement data = new DataManagement();
        data.removeData(country);
    }
    public void updateCountry(Country country){
        DataManagement data = new DataManagement();
        data.updateData(country);
    }
    public Collection<Country> getCountryList(){

        Collection<Country> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Country.class);
        for (Object x : dataList){
            output.add((Country) x);
        }
        return output;
    }

    public Collection<Country> getCountryList(String text){

        Collection<Country> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Country.class, text);
        for (Object x : dataList){
            output.add((Country) x);
        }
        return output;
    }
}
