package dao;

import config.ApplicationTestConfiguration;
import model.Book;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by Damian on 2016-08-17.
 */
@ContextConfiguration(classes = ApplicationTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
@EnableTransactionManagement
public class UserDAOImplTest {

    public static final String EXAMPLE_USER_NAME = "Example name";
    @Autowired
    private UserDAO userDAO;

    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    @Rollback(true)
    @Transactional
    public void shouldReturnEmptyUserWhenNoUserPresent() {
        //given
        User expected = new User();

        //when
        User actual = userDAO.getUserById(100);

        //then
        assertEquals(expected, actual);
    }

    @Test
    @Rollback(true)
    @Transactional
    public void shouldReturnSavedUser() {
        //given
        user = new User(EXAMPLE_USER_NAME);

        //when
        userDAO.save(user);
        User actual = userDAO.getUserByName(EXAMPLE_USER_NAME);

        //then
        assertEquals(user, actual);
    }

    @Test
    @Rollback(true)
    @Transactional
    public void shouldReturnSavedUserWithBooks() {
        //given
        user = new User(EXAMPLE_USER_NAME);
        user.setBooks(new HashSet<Book>(Arrays.asList(new Book("", ""))));

        //when
        userDAO.save(user);
        User actual = userDAO.getUserByName(EXAMPLE_USER_NAME);

        //then
        assertEquals(user.getBooks(), actual.getBooks());
    }

    @After
    public void tearDown() {
        userDAO.delete(user);
    }
}