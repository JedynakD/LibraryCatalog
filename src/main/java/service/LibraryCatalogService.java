package service;

import model.Book;


public interface LibraryCatalogService {
    Book checkOutFromCatalog(String name);

    void returnBookToCatalog(Book book);
}
