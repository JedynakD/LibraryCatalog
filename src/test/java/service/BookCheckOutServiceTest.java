package service;

import main.MainApplication;
import model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;

/**
 * Created by Damian on 2016-08-09.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainApplication.class, loader = AnnotationConfigContextLoader.class)
public class BookCheckOutServiceTest {

    @Autowired
    BookCheckOutService bookCheckOutService;

    @Test
    public void shouldReturnBookWithEmptyNameAndAuthorWhenEmptyStringPassed() {
        //when
        Book book = bookCheckOutService.checkout("");
        //then
        assertEquals(new Book("", ""), book);
    }

    @Test
    public void shouldReturnWantedBookWhenBookExists() {
        //when
        Book book = bookCheckOutService.checkout("To Kill a Mockingbird");
        //then
        assertEquals("To Kill a Mockingbird", book.getName());
    }

}