package dao;

import model.Book;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

/**
 * Created by Damian on 2016-08-10.
 */
public class BookCheckOutDAOImpl implements BookCheckoutDAO {
    @Override
    public Book checkout(String bookName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("select name from Book where name=" + "\'" + bookName + "\'");
        Book object = (Book) query.getSingleResult();
        session.getTransaction().commit();
        return object;
    }
}
