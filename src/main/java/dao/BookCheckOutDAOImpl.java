package dao;

import model.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Damian on 2016-08-10.
 */

public class BookCheckOutDAOImpl implements BookCheckOutDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    @Override
    public Book checkOut(String bookName) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Book.class);
        criteria.add(Restrictions.eq("name", bookName));
        List<Book> books = criteria.list();

        if (!books.isEmpty()) {
            Book object = books.get(0);
            return object;
        }
        return new Book("", "");
    }

    @Transactional
    @Override
    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
    }
}
