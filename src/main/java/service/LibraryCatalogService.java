package service;

import model.Book;

/**
 * Created by Damian on 2016-08-09.
 */
public interface LibraryCatalogService {
    Book checkOut(String name);
}
