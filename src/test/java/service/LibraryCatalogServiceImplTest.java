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

import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LibraryCatalogServiceImplTest {
    private static final LocalDate DEFAULT_DATE = LocalDate.MIN;
    private static final String BOOK_NAME_EXAMPLE = "Some book";
    private static final Book EMPTY_BOOK = new Book("", "");
    private static final Book BOOK_EXAMPLE = new Book(BOOK_NAME_EXAMPLE, "");

    private Book book;

    @Before
    public void setUp() {
        book = new Book();
    }

    @Autowired
    private BookDAO bookTestDAO;

    @Autowired
    private LibraryCatalogService libraryCatalogService;

    @Test
    public void should_return_empty_book_when_book_is_not_returned_to_catalog() {
        //given
        Mockito.when(bookTestDAO.getBookByName(BOOK_NAME_EXAMPLE)).thenReturn(EMPTY_BOOK);

        //when
        book = libraryCatalogService.checkOutFromCatalog(BOOK_NAME_EXAMPLE);

        //then
        assertEquals(EMPTY_BOOK, book);
    }

    @Test
    public void should_return_true_when_book_is_checkedOut() {
        //given
        Mockito.when(bookTestDAO.getBookByName(BOOK_NAME_EXAMPLE)).thenReturn(BOOK_EXAMPLE);

        //when
        book = libraryCatalogService.checkOutFromCatalog(BOOK_NAME_EXAMPLE);

        //then
        assertTrue(book.isCheckedOut());
    }

    @Test
    public void should_return_false_when_book_is_returned() {
        //when
        libraryCatalogService.returnBookToCatalog(book);

        //then
        assertFalse(book.isCheckedOut());
    }

    @Test
    public void should_return_book_with_zero_checkout_date_when_book_is_returned() {
        //when
        libraryCatalogService.returnBookToCatalog(book);

        //then
        assertEquals(DEFAULT_DATE, book.getCheckOutTime());
    }

    @Test
    public void should_return_book_with_different_then_zero_checkout_date_when_book_is_checkedOut() {
        //given
        Mockito.when(bookTestDAO.getBookByName(BOOK_NAME_EXAMPLE)).thenReturn(BOOK_EXAMPLE);

        //when
        Book book = libraryCatalogService.checkOutFromCatalog(BOOK_NAME_EXAMPLE);

        //then
        assertNotEquals(DEFAULT_DATE, book.getCheckOutTime());
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
