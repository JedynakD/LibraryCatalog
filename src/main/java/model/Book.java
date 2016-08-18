package model;

import javax.persistence.*;

/**
 * Created by Damian on 2016-08-09.
 */
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue
    @Column(name = "isbn")
    private long isbn;

    @Column(name = "name")
    private String name = "";

    @Column(name = "authorName")
    private String authorName = "";

    @Column(name = "isCheckedOut")
    private boolean isCheckedOut = false;

    @Column(name = "checkOutDate")
    private long checkOutDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Book(String name, String authorName) {
        this.name = name;
        this.authorName = authorName;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(long checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (isbn != book.isbn) return false;
        if (isCheckedOut != book.isCheckedOut) return false;
        if (checkOutDate != book.checkOutDate) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (authorName != null ? !authorName.equals(book.authorName) : book.authorName != null) return false;
        return user != null ? user.equals(book.user) : book.user == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (isbn ^ (isbn >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        result = 31 * result + (isCheckedOut ? 1 : 0);
        result = 31 * result + (int) (checkOutDate ^ (checkOutDate >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", name='" + name + '\'' +
                ", authorName='" + authorName + '\'' +
                ", isCheckedOut=" + isCheckedOut +
                ", checkOutDate=" + checkOutDate +
                ", user=" + user +
                '}';
    }
}
