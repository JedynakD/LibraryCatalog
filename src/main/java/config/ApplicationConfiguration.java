package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.BookCheckOutService;
import service.BookCheckOutServiceImpl;

/**
 * Created by Damian on 2016-08-10.
 */
@Configuration
public class ApplicationConfiguration {
    @Bean
    public BookCheckOutService getBookCheckoutService() {
        return new BookCheckOutServiceImpl();
    }
}
