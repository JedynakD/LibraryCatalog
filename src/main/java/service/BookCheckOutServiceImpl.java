package service;

import dao.BookCheckoutDAO;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Damian on 2016-08-09.
 */
public class BookCheckOutServiceImpl implements BookCheckOutService {
    @Autowired
    private BookCheckoutDAO bookCheckoutDAO;


    @Override
    public Book checkout(String name) {
        Book book = bookCheckoutDAO.checkout(name);
        return book == null ? new Book("", "") : book;
    }
}
