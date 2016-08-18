package report;

import model.Book;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Damian on 2016-08-18.
 */
public class ReportGeneratorTest {
    private static final String REPORT_FIRST_PART = "To Kill a Mockingbird, Harper Lee is ";
    private static final String REPORT_SECOND_PART = " days overdue. Days left: ";

    private Book book;

    @Before
    public void setUp() {
        book = new Book("To Kill a Mockingbird", "Harper Lee");
    }

    @Test
    public void shouldReturnReportWithZeroDaysOverdueWhenBookCheckedOutToday() {
        //given
        String expected = returnReport(0, 5);
        book.setCheckOutTime(returnTodayTime().getMillis());

        //when
        String actual = ReportGenerator.generateReport(new HashSet<Book>((Arrays.asList(book))));

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnReportWithZeroDayOverdueWhenBookCheckedOutTwoDaysAgo() {
        //given
        String expected = returnReport(0, 4);
        book.setCheckOutTime(returnTodayTime().minusDays(1).getMillis());

        //when
        String actual = ReportGenerator.generateReport(new HashSet<Book>((Arrays.asList(book))));

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnReportWithZeroDayOverdueWhenBookCheckedFiveDaysAgo() {
        //given
        String expected = returnReport(0, 0);
        book.setCheckOutTime(returnTodayTime().minusDays(5).getMillis());

        //when
        String actual = ReportGenerator.generateReport(new HashSet<Book>((Arrays.asList(book))));

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnReportWithOneDayOverdueWhenBookCheckedSixDaysAgo() {
        //given
        String expected = returnReport(1, 0);
        book.setCheckOutTime(returnTodayTime().minusDays(6).getMillis());

        //when
        String actual = ReportGenerator.generateReport(new HashSet<Book>((Arrays.asList(book))));

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnReportWithTenDaysOverdueWhenBookCheckedFifteenDaysAgo() {
        //given
        String expected = returnReport(10, 0);
        book.setCheckOutTime(returnTodayTime().minusDays(15).getMillis());

        //when
        String actual = ReportGenerator.generateReport(new HashSet<Book>((Arrays.asList(book))));

        //then
        assertEquals(expected, actual);
    }

    private String returnReport(int daysOverdue, int daysLeft) {
        return REPORT_FIRST_PART + daysOverdue + REPORT_SECOND_PART + daysLeft + "\n";
    }

    private DateTime returnTodayTime() {
        return new DateTime(DateTimeZone.UTC);
    }

}