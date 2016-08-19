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
    @Column(name = "isbn", nullable = false)
    private long isbn;

    @Column(name = "name", nullable = false)
    private String name = "";

    @Column(name = "authorName", nullable = false)
    private String authorName = "";

    @Column(name = "isCheckedOut", nullable = false)
    private boolean isCheckedOut = false;

    @Column(name = "checkOutTime", nullable = false)
    private long checkOutTime;

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

    public long getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(long checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (isbn != book.isbn) return false;
        if (isCheckedOut != book.isCheckedOut) return false;
        if (checkOutTime != book.checkOutTime) return false;
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
        result = 31 * result + (int) (checkOutTime ^ (checkOutTime >>> 32));
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
                ", checkOutTime=" + checkOutTime +
                ", user=" + user +
                '}';
    }
}
