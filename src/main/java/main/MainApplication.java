package main;

import config.ApplicationConfiguration;
import model.Book;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.BookCheckOutService;

/**
 * Created by Damian on 2016-08-09.
 */
public class MainApplication {
    private final static Logger LOGGER = Logger.getLogger(MainApplication.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        BookCheckOutService bookCheckOutService = applicationContext.getBean(BookCheckOutService.class);
        Book book = bookCheckOutService.checkout("Some book");
        LOGGER.info(book);
    }
}
