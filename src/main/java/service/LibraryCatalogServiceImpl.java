package service;

import dao.BookDAO;
import model.Book;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Damian on 2016-08-16.
 */
@Service
public class LibraryCatalogServiceImpl implements LibraryCatalogService {
    @Autowired
    private BookDAO bookDAO;

    @Transactional
    @Override
    public Book checkOut(String name) {
        Book book = bookDAO.getBookByName(name);
        book.setCheckedOut(true);
        book.setCheckOutTime(new DateTime(DateTimeZone.UTC).getMillis());
        if (!book.equals(new Book("", ""))) {
            bookDAO.update(book);
        }
        return book;
    }

    @Transactional
    @Override
    public void returnBook(Book book) {
        book.setCheckedOut(false);
        book.setCheckOutTime(0L);
        bookDAO.update(book);
    }
}
