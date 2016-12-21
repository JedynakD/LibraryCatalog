package dto;

import model.Book;

import java.util.HashSet;
import java.util.Set;

public class UserDto {
    private Long userId;

    private String name;

    private Set<Book> books = new HashSet<Book>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
