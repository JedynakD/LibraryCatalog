package dao;

import model.Book;

/**
 * Created by Damian on 2016-08-11.
 */
public interface BookCheckOutDAO {
    Book checkout(String bookName);

    void save(Book book);
}
