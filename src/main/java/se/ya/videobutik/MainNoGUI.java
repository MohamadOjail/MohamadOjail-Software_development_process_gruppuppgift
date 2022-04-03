package se.ya.videobutik;

import se.ya.videobutik.data.dao.AddressDAO;
import se.ya.videobutik.model.Address;

public class MainNoGUI {
    public static void main(String[] args) {

        AddressDAO addressDAO = new AddressDAO();
        Object[] objectData = {
                "GatoGatan",
                "distictooo",
                5,
                "123",
                "123"
        };
        addressDAO.AddAddress(objectData);
    }
}
