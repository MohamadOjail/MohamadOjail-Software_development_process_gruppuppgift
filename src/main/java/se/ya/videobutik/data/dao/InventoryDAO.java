package se.ya.videobutik.data.dao;

import se.ya.videobutik.data.DataManagement;
import se.ya.videobutik.model.Inventory;

import java.util.ArrayList;
import java.util.Collection;

public class InventoryDAO {

    public void AddInventory(Inventory inventory){
        DataManagement data = new DataManagement();
        data.setData(inventory);
    }
    public Inventory findInventory(int id){
        DataManagement data = new DataManagement();
        return (Inventory) data.getData(Inventory.class, id);
    }
    public void deleteInventory(Inventory inventory){
        DataManagement data = new DataManagement();
        data.removeData(inventory);
    }
    public void updateInventory(Inventory inventory){
        DataManagement data = new DataManagement();
        data.updateData(inventory);
    }
    public Collection<Inventory> getInventoryList(){

        Collection<Inventory> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Inventory.class);
        for (Object x : dataList){
            output.add((Inventory) x);
        }
        return output;
    }

    public Collection<Inventory> getInventoryList(String text){

        Collection<Inventory> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Inventory.class, text);
        for (Object x : dataList){
            output.add((Inventory) x);
        }
        return output;
    }
}
