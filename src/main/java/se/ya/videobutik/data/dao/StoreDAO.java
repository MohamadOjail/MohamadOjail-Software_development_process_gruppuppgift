package se.ya.videobutik.data.dao;

import se.ya.videobutik.data.DataManagement;
import se.ya.videobutik.model.Store;

import java.util.ArrayList;
import java.util.Collection;

public class StoreDAO {

    public void AddStore(Store store){
        DataManagement data = new DataManagement();
        data.setData(Store.class);
    }
    public Store findStore(int id){
        DataManagement data = new DataManagement();
        return (Store) data.getData(Store.class, id);
    }
    public void deleteStore(Store store){
        DataManagement data = new DataManagement();
        data.removeData(store);
    }
    public void updateStore(Store store){
        DataManagement data = new DataManagement();
        data.updateData(Store.class);
    }
    public Collection<Store> getStoreList(){

        Collection<Store> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Store.class);
        for (Object x : dataList){
            output.add((Store) x);
        }
        return output;
    }

    public Collection<Store> getStoreList(String text){

        Collection<Store> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Store.class, text);
        for (Object x : dataList){
            output.add((Store) x);
        }
        return output;
    }
}
