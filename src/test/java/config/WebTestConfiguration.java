package config;

import dao.BookDAO;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import service.LibraryCatalogService;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"controller"})
public class WebTestConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }

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