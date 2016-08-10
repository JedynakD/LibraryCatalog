package dao;

import model.Book;

/**
 * Created by Damian on 2016-08-10.
 */
public interface BookCheckoutDAO {
    Book checkout(String bookName);
}
