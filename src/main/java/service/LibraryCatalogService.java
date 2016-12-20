package service;

import model.Book;


public interface LibraryCatalogService {
    Book checkOut(String name);

    void returnBook(Book book);
}
