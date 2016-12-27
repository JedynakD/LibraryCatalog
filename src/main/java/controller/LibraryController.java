package controller;

import dto.BookDto;
import model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public BookDto getBookByName(@PathVariable String name) {
        Book book = libraryCatalogService.checkOutFromCatalog(name);
        return toDTO(book);
    }

    @RequestMapping(value = "/insertBook", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void insertBook(@RequestBody BookDto bookDto) {
        libraryCatalogService.returnBookToCatalog(toBook(bookDto));
    }

    private BookDto toDTO(Book book) {
        return new BookDto(book.getIsbn(), book.getName(), book.getAuthorName(),
                book.isCheckedOut(), book.getCheckOutTime(), book.getUser());
    }

    private Book toBook(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }
}