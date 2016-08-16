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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Damian on 2016-08-11.
 */

@ContextConfiguration(classes = ApplicationTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
@EnableTransactionManagement
public class BookCheckOutDAOImplTest {

    private static final List<Book> BOOKS = new ArrayList<>(Arrays.asList(
            new Book("To Kill a Mockingbird", "Harper Lee"),
            new Book("1984", "George Orwell"),
            new Book("Pride and Prejudice ", "Jane Austen"),
            new Book("Gone with the Wind ", "Margaret Mitchell")));

    private static final Book EMPTY_BOOK = new Book("", "");

    @Autowired
    private BookCheckOutDAO bookCheckoutDAO;

    @Test
    @Rollback(true)
    @Transactional
    public void shouldReturnAddedBook() {
        //given
        Book expected = BOOKS.get(0);
        bookCheckoutDAO.save(expected);

        //when
        Book actual = bookCheckoutDAO.getBookByName(expected.getName());

        //then
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getAuthorName(), actual.getAuthorName());
    }

    @Test
    @Rollback(true)
    @Transactional
    public void shouldReturnEmptyBookWhenWantedBookIsNotPresent() {
        //given
        Book expected = BOOKS.get(1);
        bookCheckoutDAO.save(expected);

        //when
        Book actual = bookCheckoutDAO.getBookByName("Some name");

        //then
        assertEquals(EMPTY_BOOK.getName(), actual.getName());
        assertEquals(EMPTY_BOOK.getAuthorName(), actual.getAuthorName());
    }

    @Test
    @Rollback(true)
    @Transactional
    public void shouldReturnBookByIdWhenBookWithThatIdExists() {
        //given
        Book expected = BOOKS.get(2);
        bookCheckoutDAO.save(expected);

        //when
        Book actual = bookCheckoutDAO.getBookByID(BOOKS.get(2).getId());

        //then
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    @Rollback(true)
    @Transactional
    public void shouldReturnEmptyBookWhenBookWithInputIdDoesNotExist() {
        //given
        Book expected = BOOKS.get(3);
        bookCheckoutDAO.save(expected);

        //when
        Book actual = bookCheckoutDAO.getBookByID(5);

        //then
        assertEquals(EMPTY_BOOK, actual);
    }
}