package se.ya.videobutik.data.dao;

import se.ya.videobutik.data.DataManagement;
import se.ya.videobutik.model.Rental;

import java.util.ArrayList;
import java.util.Collection;

public class RentalDAO {

    public void AddRental(Rental rental){
        DataManagement data = new DataManagement();
        data.setData(rental);
    }
    public Rental findRental(int id){
        DataManagement data = new DataManagement();
        return (Rental) data.getData(Rental.class, id);
    }
    public void deleteRental(Rental rental){
        DataManagement data = new DataManagement();
        data.removeData(rental);
    }
    public void updateRental(Rental rental){
        DataManagement data = new DataManagement();
        data.updateData(rental);
    }
    public Collection<Rental> getRentalList(){

        Collection<Rental> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Rental.class);
        for (Object x : dataList){
            output.add((Rental) x);
        }
        return output;
    }

    public Collection<Rental> getRentalList(String text){

        Collection<Rental> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Rental.class, text);
        for (Object x : dataList){
            output.add((Rental) x);
        }
        return output;
    }
}
