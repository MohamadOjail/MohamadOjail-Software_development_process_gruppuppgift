package se.ya.videobutik;

import se.ya.videobutik.data.DataManagement;
import se.ya.videobutik.data.dao.*;
import se.ya.videobutik.model.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

public class MainNoGUI {
    public static void main(String[] args) {
        DataManagement dataManagement = new DataManagement();
        //address();
        Object address = dataManagement.getAddress(Address.class, 611);
        System.out.println(address);
        //customer();
        //actor2();
       // address();
       // film1();
      // staff1();
       // store1();

       // dataManagement.setAddress();
    }
    private static void customer(){
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = new Customer();

        customer.setStore(new StoreDAO().findStore(1));
        customer.setFirstName("testnamn");
        customer.setLastName("testlastName");
        customer.setEmail("test@mail");
        customer.setAddress(new AddressDAO().findAddress(13));
        customer.setActive(false);
        customer.setCreateDate(LocalDateTime.now());
        customer.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

        customerDAO.AddCustomer(customer);

        Customer customer1 = customerDAO.findCustomer(501);
        System.out.println(customer1.toString());

        customer1.setLastName("nyttlastname");

        customerDAO.updateCustomer(customer1);
        customer1 = customerDAO.findCustomer(501);

        System.out.println(customer1.toString());

    }

    private static void customerDelete(){
        CustomerDAO customerDAO = new CustomerDAO();

        Customer customer = customerDAO.findCustomer(602);


        // customerDAO.deleteCustomer(customer);
        Collection<Customer> customerList = customerDAO.getCustomerList();

        for(Customer c : customerList){
            if (c.getFirstName().equals("testnamn")){
                customerDAO.deleteCustomer(c);
            }
        }
    }

    private static void actor1(){
        Actor actor = new Actor();
        actor.setFirstName("ActorTEst");
        actor.setLastName("actorlastname");
        actor.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

        ActorDAO actorDAO = new ActorDAO();
        actorDAO.AddActor(actor);

        Actor actor1 = actorDAO.findActor(201);
        actor1.setLastName("newName");
        actorDAO.updateActor(actor1);
        System.out.println(actor1);

    }

    private static void actor2(){
        ActorDAO actorDAO = new ActorDAO();
        Collection<Actor> actorsList = actorDAO.getActorList();

        for(Actor a : actorsList){
            if (a.getFirstName().equals("ActorTEst")){
                actorDAO.deleteActor(a);
            }
        }

    }



    private static void address(){
        AddressDAO addressDAO = new AddressDAO();
        DataManagement dataManagement = new DataManagement();

        Object[] objectData = {
                "GatoGatan",
                "distictooo",
                5,
                "123",
                "123"
        };
        dataManagement.createAddress(objectData);
        //addressDAO.AddAddress(objectData);

    //   Address address1 = addressDAO.findAddress(606);
      // address1.setAddress("nyyyaddress");

      // addressDAO.updateAddress(address1);

    }

    private static void updateAddress(){


    }
    private static void film(){
        FilmDAO filmDAO = new FilmDAO();
        Film film1 = new Film();
        LanguageDAO languageDAO = new LanguageDAO();
        Language language = languageDAO.findLanguage(1);

        film1.setTitle("testFilm");
        film1.setDescription("fsdjfnsjn");
        film1.setReleaseYear(1999);
        film1.setLanguage(language);
        film1.setOriginalLanguage(language);
        film1.setRentalDuration(3);
        film1.setRentalRate(4.7);
        film1.setLength(2);
        film1.setReplacementCost(3.4);
        film1.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

        filmDAO.AddFilm(film1);
        Film film = filmDAO.findFilm(1001);

        film.setTitle("newName");
        filmDAO.updateFilm(film);
        System.out.println(film);

    }
    private static void film1(){
        FilmDAO fDAO = new FilmDAO();
        Collection<Film> filmList = fDAO.getFilmList();

        for(Film a : filmList){
            if (a.getTitle().equals("newName")){
                fDAO.deleteFilm(a);
            }
        }
    }

    private static void staff(){
        StaffDAO staffDAO = new StaffDAO();
        Staff staff = new Staff();

        staff.setFirstName("a");
        staff.setLastName("s");
        staff.setAddress(new AddressDAO().findAddress(1));
        staff.setStore(new StoreDAO().findStore(1));
        staff.setActive(true);
        staff.setUsername("sdsd");
        staff.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

        staffDAO.AddStaff(staff);
        Staff staff1 = staffDAO.findStaff(4);

        staff1.setUsername("Poff");
        staffDAO.updateStaff(staff1);
        System.out.println(staff1);
    }

    private static void staff1(){
        StaffDAO sDAO = new StaffDAO();
        Collection<Staff> staffList = sDAO.getStaffList();

        for(Staff a : staffList){
            if (a.getUsername().equals("Poff")){
                sDAO.deleteStaff(a);
            }
        }
    }
    private static void staff2(){
        StaffDAO sDAO = new StaffDAO();
        Collection<Staff> staffList = sDAO.getStaffList();

        for(Staff a : staffList){
            if (a.getUsername().equals("sdsd")){
                sDAO.deleteStaff(a);
            }
        }
    }


    private static void store(){
        StoreDAO storeDAO = new StoreDAO();
        Store store = new Store();

staff();
        store.setAddress(new AddressDAO().findAddress(4));
        store.setManagerStaff(new StaffDAO().findStaff(4));
        store.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

        storeDAO.AddStore(store);
        Store store1 = storeDAO.findStore(4);

        store1.setAddress(new AddressDAO().findAddress(2));
        storeDAO.updateStore(store1);
        System.out.println(store1);
    }
    private static void store1(){
        StoreDAO storeDAO = new StoreDAO();
        Store store = storeDAO.findStore(4);

        storeDAO.deleteStore(store);
    }
}
