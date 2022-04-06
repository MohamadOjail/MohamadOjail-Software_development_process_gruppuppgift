package se.ya.videobutik.data.dao;

import se.ya.videobutik.data.DataManagement;
import se.ya.videobutik.model.Customer;

import java.util.ArrayList;
import java.util.Collection;

public class CustomerDAO {

    public void AddCustomer(Customer customer){
        DataManagement data = new DataManagement();
        data.setData(customer);
    }
    public Customer findCustomer(int id){
        DataManagement data = new DataManagement();
        return (Customer) data.getData(Customer.class, id);
    }
    public void deleteCustomer(Customer customer){
        DataManagement data = new DataManagement();
        data.removeData(customer);
    }
    public void updateCustomer(Customer customer){
        DataManagement data = new DataManagement();
        data.updateData(customer);
    }
    public Collection<Customer> getCustomerList(){

        Collection<Customer> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Customer.class);
        for (Object x : dataList){
            output.add((Customer) x);
        }
        return output;
    }

    public Collection<Customer> getCustomerList(String text){

        Collection<Customer> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Customer.class, text);
        for (Object x : dataList){
            output.add((Customer) x);
        }
        return output;
    }

    public Collection<Customer> getCustomerList(String text, int storeId){

        Collection<Customer> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Customer.class, text, storeId, true);
        for (Object x : dataList){
            output.add((Customer) x);
        }
        return output;
    }
}
