package config;

import dao.BookDAO;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import service.LibraryCatalogService;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"controller"})
public class WebTestConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public LibraryCatalogService bookCheckOutService() {
        return Mockito.mock(LibraryCatalogService.class);
    }

    @Bean
    public BookDAO bookTestDAO() {
        return Mockito.mock(BookDAO.class);
    }
}