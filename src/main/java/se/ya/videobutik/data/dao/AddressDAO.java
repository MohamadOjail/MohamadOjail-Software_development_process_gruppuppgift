package se.ya.videobutik.data.dao;

import org.hibernate.SessionFactory;
import se.ya.videobutik.data.DataManagement;
import se.ya.videobutik.model.Address;

import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.Collection;

public class AddressDAO {

    public void AddAddress(Address address){
        DataManagement data = new DataManagement();
        data.setData(address);
    }

    public void AddAddress(Object[] objectData){
        DataManagement data = new DataManagement();
        data.setData(Address.class, objectData);
    }

    public Address findAddress(int id){
        DataManagement data = new DataManagement();
        return (Address) data.getData(Address.class, id);
    }
    public void deleteAddress(Address address){
        DataManagement data = new DataManagement();
        data.removeData(address);
    }
    public void updateAddress(Address address){
        DataManagement data = new DataManagement();
        data.updateData(address);
    }
    public Collection<Address> getAddressList(){

        Collection<Address> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Address.class);
        for (Object x : dataList){
            output.add((Address) x);
        }
        return output;
    }

    public Collection<Address> getAddressList(String text){

        Collection<Address> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Address.class, text);
        for (Object x : dataList){
            output.add((Address) x);
        }
        return output;
    }
}
