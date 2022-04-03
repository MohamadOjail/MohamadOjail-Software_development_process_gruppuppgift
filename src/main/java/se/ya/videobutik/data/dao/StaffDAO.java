package se.ya.videobutik.data.dao;

import se.ya.videobutik.data.DataManagement;
import se.ya.videobutik.model.Staff;

import java.util.ArrayList;
import java.util.Collection;

public class StaffDAO {

    public void AddStaff(Staff staff){
        DataManagement data = new DataManagement();
        data.setData(staff);
    }
    public Staff findStaff(int id){
        DataManagement data = new DataManagement();
        return (Staff) data.getData(Staff.class, id);
    }
    public void deleteStaff(Staff staff){
        DataManagement data = new DataManagement();
        data.removeData(staff);
    }
    public void updateStaff(Staff staff){
        DataManagement data = new DataManagement();
        data.updateData(staff);
    }
    public Collection<Staff> getStaffList(){

        Collection<Staff> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Staff.class);
        for (Object x : dataList){
            output.add((Staff) x);
        }
        return output;
    }

    public Collection<Staff> getStaffList(String text){

        Collection<Staff> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Staff.class, text);
        for (Object x : dataList){
            output.add((Staff) x);
        }
        return output;
    }
}
