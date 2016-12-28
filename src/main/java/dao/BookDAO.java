package dao;

import model.Book;

public interface BookDAO {
    Book getBookByName(String bookName);

    void save(Book book);

    Book getBookByID(long id);

    void update(Book book);

    void delete(Book book);
}
