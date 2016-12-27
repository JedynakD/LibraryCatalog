package service;

import dao.BookDAO;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class LibraryCatalogServiceImpl implements LibraryCatalogService {
    @Autowired
    private BookDAO bookDAO;

    @Transactional
    @Override
    public Book checkOutFromCatalog(String name) {
        Book book = bookDAO.getBookByName(name);
        book.setCheckedOut(true);
        book.setCheckOutTime(LocalDate.now());
        if (!book.equals(new Book("", ""))) {
            bookDAO.update(book);
        }
        return book;
    }

    @Transactional
    @Override
    public void returnBookToCatalog(Book book) {
        book.setCheckedOut(false);
        book.setCheckOutTime(LocalDate.MIN);
        bookDAO.update(book);
    }
}
