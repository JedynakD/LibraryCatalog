package controller;

import dto.BookDto;
import model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.LibraryCatalogService;

@RestController
public class LibraryController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LibraryCatalogService libraryCatalogService;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BookDto getBook(@PathVariable String name) {
        Book book = libraryCatalogService.checkOut(name);
        BookDto bookDto = toDTO(book);
        return bookDto;
    }

    private BookDto toDTO(Book book) {
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        return bookDto;
    }
}