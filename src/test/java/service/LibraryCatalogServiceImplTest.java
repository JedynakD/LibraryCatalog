package service;

import dao.BookDAO;
import model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Damian on 2016-08-16.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LibraryCatalogServiceImplTest {

    private static final long DEFAULT_DATE = 0L;
    private static final String BOOK_NAME_EXAMPLE = "Some book";

    private Book book;

    @Before
    public void setUp() {
        book = new Book();
    }

    @Autowired
    private LibraryCatalogService libraryCatalogService;

    @Autowired
    private BookDAO bookTestDAO;

    @Test
    public void shouldReturnTrueWhenBookIsCheckedOut() {
        //given
        Mockito.when(bookTestDAO.getBookByName(BOOK_NAME_EXAMPLE)).thenReturn(book);

        //when
        book = libraryCatalogService.checkOut(BOOK_NAME_EXAMPLE);

        //then
        assertTrue(book.isCheckedOut());
    }

    @Test
    public void shouldReturnFalseWhenBookIsReturned() {
        //when
        libraryCatalogService.returnBook(book);

        //then
        assertFalse(book.isCheckedOut());
    }

    @Test
    public void shouldReturnBookWithZeroCheckoutDateWhenBookIsReturned() {
        //when
        libraryCatalogService.returnBook(book);

        //then
        assertEquals(DEFAULT_DATE, book.getCheckOutDate());
    }

    @Test
    public void shouldReturnBookWithDifferentThenZeroCheckoutDateWhenBookIsCheckedOut() {
        //given
        Mockito.when(bookTestDAO.getBookByName(BOOK_NAME_EXAMPLE)).thenReturn(book);

        //when
        Book book = libraryCatalogService.checkOut(BOOK_NAME_EXAMPLE);

        //then
        assertNotEquals(DEFAULT_DATE, book.getCheckOutDate());
    }

    @Configuration
    static class LibraryCatalogServiceContextConfiguration {

        @Bean
        public LibraryCatalogService bookCheckOutService() {
            return new LibraryCatalogServiceImpl();
        }

        @Bean
        public BookDAO bookTestDAO() {
            return Mockito.mock(BookDAO.class);
        }

    }
}
