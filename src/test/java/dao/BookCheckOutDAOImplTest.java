package dao;

import config.ApplicationTestConfiguration;
import model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Damian on 2016-08-11.
 */

@ContextConfiguration(classes = ApplicationTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
@EnableTransactionManagement
public class BookCheckOutDAOImplTest {

    private static final List<Book> BOOKS = new ArrayList<>(Arrays.asList(
            new Book("To Kill a Mockingbird", "Harper Lee"),
            new Book("1984", "George Orwell")));

    @Autowired
    private BookCheckOutDAO bookCheckoutDAO;

    @Test
    @Rollback(true)
    @Transactional
    public void shouldReturnAddedBook() {
        Book expected = BOOKS.get(0);
        bookCheckoutDAO.save(expected);

        Book actual = bookCheckoutDAO.checkout("To Kill a Mockingbird");
        assertEquals("To Kill a Mockingbird", actual.getName());
        assertEquals("Harper Lee", actual.getAuthorName());
    }

    @Test
    @Rollback(true)
    @Transactional
    public void shouldNotReturnAddedBookWhenWrongBookNameInserted() {
        Book expected = BOOKS.get(1);
        bookCheckoutDAO.save(expected);

        Book actual = bookCheckoutDAO.checkout("Some title");
        assertEquals("", actual.getName());
        assertEquals("", actual.getAuthorName());
    }
}