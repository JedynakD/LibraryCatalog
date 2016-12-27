package dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import model.User;
import serializer.LocalDateDeserializer;
import serializer.LocalDateSerializer;

import java.time.LocalDate;

public class BookDto {
    public final long isbn;

    public final String name;

    public final String authorName;

    public final boolean isCheckedOut;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    public final LocalDate checkOutTime;

    public final User user;

    @JsonCreator
    public BookDto(@JsonProperty("isbn") long isbn,
                   @JsonProperty("name") String name,
                   @JsonProperty("authorName") String authorName,
                   @JsonProperty("isCheckedOut") boolean isCheckedOut,
                   @JsonProperty("checkOutTime") LocalDate checkOutTime,
                   @JsonProperty("user") User user) {
        this.isbn = isbn;
        this.name = name;
        this.authorName = authorName;
        this.isCheckedOut = isCheckedOut;
        this.checkOutTime = checkOutTime;
        this.user = user;
    }
}
