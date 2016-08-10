package model;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * Created by Damian on 2016-08-09.
 */
@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name = "";

    @Column(name="authorName")
    private String authorName = "";

    public Book(String name, String authorName) {
        this.name = name;
        this.authorName = authorName;
    }

    public String getName() {
        return name;
    }

    public String getAuthorName() {
        return authorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        return authorName != null ? authorName.equals(book.authorName) : book.authorName == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
