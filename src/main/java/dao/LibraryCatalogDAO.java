package dao;

import model.Book;

/**
 * Created by Damian on 2016-08-11.
 */
public interface LibraryCatalogDAO {
    Book getBookByName(String bookName);

    void save(Book book);

    Book getBookByID(long id);

    void update(Book book);

    void delete(Book book);
}
