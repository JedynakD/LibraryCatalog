package service;

import model.Book;

/**
 * Created by Damian on 2016-08-09.
 */
public interface BookCheckOutService {
    Book checkout(String name);
}
