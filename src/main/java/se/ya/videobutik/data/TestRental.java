package se.ya.videobutik.data;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import se.ya.videobutik.model.*;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

/**
 *  klassen kommer att ersättas med motsvarande metoder i DataManagement klass
 */
public class TestRental {

    private SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Actor.class)
            .addAnnotatedClass(Address.class)
            .addAnnotatedClass(Customer.class)
            .addAnnotatedClass(Film.class)
            .addAnnotatedClass(Staff.class)
            .addAnnotatedClass(Store.class)
            .addAnnotatedClass(City.class)
            .addAnnotatedClass(Country.class)
            .addAnnotatedClass(Language.class)
            .addAnnotatedClass(Payment.class)
            .addAnnotatedClass(Rental.class)
            .addAnnotatedClass(Inventory.class)
            .addAnnotatedClass(FilmActor.class)
            .addAnnotatedClass(FilmCategory.class)
            .addAnnotatedClass(Category.class)
            .addAnnotatedClass(Rental.class)
            .addAnnotatedClass(Inventory.class)
            .buildSessionFactory();
    private Session session = null;

    // hämta film antal kopior som finns i butiken och inte är uthyrda
    public int getAvailableFilmCount(int filmId, int storeId){

        int count = 0;

        try {
            session = factory.openSession();
            session.beginTransaction();

            StoredProcedureQuery query = session.createStoredProcedureQuery("film_in_stock");
            query.registerStoredProcedureParameter("p_film_id", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_store_id", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_film_count", Integer.class, ParameterMode.OUT);
            query.setParameter("p_film_id", filmId);
            query.setParameter("p_store_id", storeId);
            query.execute();

            count = (int) query.getOutputParameterValue("p_film_count");

            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }

        return count;
    }
}
