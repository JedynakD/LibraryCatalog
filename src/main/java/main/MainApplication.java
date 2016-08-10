package main;

import model.Book;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 * Created by Damian on 2016-08-09.
 */
public class MainApplication {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Book book = new Book("To Kill a Mockingbird", "Harper Lee");

        session.save(book);
        session.getTransaction().commit();
    }
}
