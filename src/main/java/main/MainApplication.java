package main;

import model.Book;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import service.BookCheckOutService;
import service.BookCheckOutServiceImpl;

/**
 * Created by Damian on 2016-08-09.
 */
@Configuration
@ComponentScan
public class MainApplication {
    private final static Logger LOGGER = Logger.getLogger(MainApplication.class);

    @Bean
    public BookCheckOutService getBookCheckoutService() {
        return new BookCheckOutServiceImpl();
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainApplication.class);
        BookCheckOutService bookCheckOutService = applicationContext.getBean(BookCheckOutService.class);
        Book book = bookCheckOutService.checkout("Some book");
        LOGGER.info(book);
    }
}
