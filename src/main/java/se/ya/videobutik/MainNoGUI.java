package se.ya.videobutik;

import se.ya.videobutik.data.dao.AddressDAO;
import se.ya.videobutik.model.Address;

public class MainNoGUI {
    public static void main(String[] args) {

        AddressDAO addressDAO = new AddressDAO();
        Address address = addressDAO.findAddress(2);
        System.out.println(address.getLocation());
    }
}
