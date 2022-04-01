package se.ya.videobutik;

import se.ya.videobutik.data.DataManagement;
import se.ya.videobutik.data.dao.ActorDAO;
import se.ya.videobutik.data.dao.AddressDAO;
import se.ya.videobutik.model.Actor;
import se.ya.videobutik.model.Address;
import se.ya.videobutik.model.City;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

public class MainNoGUI {
    public static void main(String[] args) {

        ActorDAO actorDAO = new ActorDAO();
        AddressDAO addressDAO = new AddressDAO();
        DataManagement dataManagement = new DataManagement();
        City data = (City) dataManagement.getData(City.class, 2);
        Address ad = new Address();
        ad.setAddress("luleåvägen");
        ad.setDistrict("sdsd");
        ad.setCity(data);
        ad.setPhone("dsds");
        //ad.setLocation("0x0000000001010000003E0A325D63345CC0761FDB8D99D94840");
ad.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        Address address = addressDAO.findAddress(2);

       // System.out.println(ad.getLocation());
        addressDAO.AddAddress(ad);



    }
}
