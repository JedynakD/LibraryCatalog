package service;

import dao.BookDAO;
import model.Book;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by Damian on 2016-08-16.
 */
public class LibraryCatalogServiceImpl implements LibraryCatalogService {
    @Autowired
    private BookDAO bookDAO;

    @Override
    public Book checkOut(String name) {
        Book book = bookDAO.getBookByName(name);
        book.setCheckedOut(true);
        book.setCheckOutTime(new DateTime(DateTimeZone.UTC).getMillis());
        bookDAO.update(book);
        return book;
    }

    @Override
    public void returnBook(Book book) {
        book.setCheckedOut(false);
        book.setCheckOutTime(0L);
        bookDAO.update(book);
    }
}
