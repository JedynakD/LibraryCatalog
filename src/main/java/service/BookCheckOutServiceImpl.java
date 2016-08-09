package service;

import model.Book;

import java.util.*;

/**
 * Created by Damian on 2016-08-09.
 */
public class BookCheckOutServiceImpl implements BookCheckOutService {
    private static Map<String, Book> books = new HashMap<String, Book>();

    static {
        books.put("To Kill a Mockingbird", new Book("To Kill a Mockingbird", "Harper Lee"));
    }

    @Override
    public Book checkout(String name) {
        if (books.containsKey(name)) {
            return books.get(name);
        } else {
            return new Book("", "");
        }
    }
}
