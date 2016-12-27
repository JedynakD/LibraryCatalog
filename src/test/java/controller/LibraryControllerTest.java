package controller;

import config.AppTestInitializer;
import config.WebTestConfiguration;
import dao.BookDAO;
import model.Book;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import service.LibraryCatalogService;

import java.nio.charset.Charset;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {AppTestInitializer.class, WebTestConfiguration.class})
@WebAppConfiguration
public class LibraryControllerTest {
    @Autowired
    private BookDAO bookTestDAO;

    @Autowired
    private LibraryCatalogService libraryCatalogService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Mockito.when(bookTestDAO.getBookByName("LOTR")).thenReturn(new Book("LOTR", "LOTR"));
        Mockito.when(libraryCatalogService.checkOut("LOTR")).thenReturn(new Book("LOTR", "LOTR"));

    }

    @Test
    public void should_return_status_ok_when_send_request_for_existing_book() throws Exception {
        mockMvc.perform(get("/LOTR")
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_status_ok_and_correct_json_response_when_send_request_for_existing_book() throws Exception {
        mockMvc.perform(get("/LOTR")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"isbn\":0," +
                                "\"name\":\"LOTR\"," +
                                "\"authorName\":\"LOTR\"," +
                                "\"checkOutTime\":{\"year\":-999999999,\"month\":\"JANUARY\",\"era\":\"BCE\",\"dayOfYear\":1,\"dayOfWeek\":\"MONDAY\",\"leapYear\":false,\"dayOfMonth\":1,\"monthValue\":1,\"chronology\":{\"calendarType\":\"iso8601\"," +
                                "\"id\":\"ISO\"}}," +
                                "\"user\":null," +
                                "\"checkedOut\":false}"));
    }
}