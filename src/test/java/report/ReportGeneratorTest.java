package report;

import model.Book;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Damian on 2016-08-18.
 */
public class ReportGeneratorTest {
    private static final List<Book> BOOKS = new ArrayList<>(Arrays.asList(
            new Book("To Kill a Mockingbird", "Harper Lee"),
            new Book("1984", "George Orwell"),
            new Book("Pride and Prejudice ", "Jane Austen"),
            new Book("Gone with the Wind", "Margaret Mitchell"),
            new Book("Romeo and Juliet", "William Shakespeare"),
            new Book("Lord of the Flies", "William Golding")));
    private static final long RESET_TIME = 0L;


    @Test
    public void shouldReturnReportWithZeroDaysOverdueWhenBookCheckedOutToday() {
        //given
        String expected = "To Kill a Mockingbird, Harper Lee is 0 days overdue.";
        BOOKS.get(0).setCheckOutTime(returnTodayTime().getMillis());

        //when
        String actual = ReportGenerator.generateReport(new HashSet<Book>((Arrays.asList(BOOKS.get(0)))));

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnReportWithOneDayOverdueWhenBookCheckedOutToday() {
        //given
        String expected = "To Kill a Mockingbird, Harper Lee is 1 days overdue.";
        BOOKS.get(0).setCheckOutTime(returnTodayTime().plusDays(1).getMillis());

        //when
        String actual = ReportGenerator.generateReport(new HashSet<Book>((Arrays.asList(BOOKS.get(0)))));

        //then
        assertEquals(expected, actual);
    }

    private DateTime returnTodayTime() {
        return new DateTime(DateTimeZone.UTC);
    }

}