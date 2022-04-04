package se.ya.videobutik;

import se.ya.videobutik.data.dao.*;
import se.ya.videobutik.model.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

public class MainNoGUI {
    public static void main(String[] args) {

        //customer();
        //actor2();
       // address();
        film();
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

        Object[] objectData = {
                "GatoGatan",
                "distictooo",
                5,
                "123",
                "123"
        };

        //addressDAO.AddAddress(objectData);

       Address address1 = addressDAO.findAddress(606);
       address1.setAddress("nyyyaddress");

       addressDAO.updateAddress(address1);



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



    }
    private void staff(){

    }
}
