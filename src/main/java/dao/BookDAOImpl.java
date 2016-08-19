package dao;

import model.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Damian on 2016-08-10.
 */
@Repository
public class BookDAOImpl implements BookDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Book getBookByName(String bookName) {
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

    @Override
    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
    }

    @Override
    public Book getBookByID(long id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = (Book) session.get(Book.class, id);
        if (book == null) {
            return new Book("", "");
        }
        return book;
    }

    @Override
    public void update(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.update(book);
    }

    @Override
    public void delete(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(book);
    }
}
