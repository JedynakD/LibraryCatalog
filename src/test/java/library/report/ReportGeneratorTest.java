package library.report;

import model.Book;
import org.junit.Before;
import org.junit.Test;
import library.overdue.Overdue;

import static org.junit.Assert.assertEquals;

/**
 * Created by Damian on 2016-08-18.
 */
public class ReportGeneratorTest {
    private static final String REPORT_FIRST_PART = "To Kill a Mockingbird, Harper Lee is ";
    private static final String REPORT_SECOND_PART = " days library.overdue. Days left: ";

    private Book book;

    @Before
    public void setUp() {
        book = new Book("To Kill a Mockingbird", "Harper Lee");
    }

    @Test
    public void shouldReturnReportWithZeroDaysOverdueWhenBookCheckedOutToday() {
        //given
        String expected = returnReport(5, 0);

        //when
        String actual = ReportGenerator.generateReport(book, new Overdue(5, 0));

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnReportWithZeroDayOverdueWhenBookCheckedOutTwoDaysAgo() {
        //given
        String expected = returnReport(4, 0);

        //when
        String actual = ReportGenerator.generateReport(book, new Overdue(4, 0));

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnReportWithZeroDayOverdueWhenBookCheckedFiveDaysAgo() {
        //given
        String expected = returnReport(0, 0);

        //when
        String actual = ReportGenerator.generateReport(book, new Overdue(0, 0));

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnReportWithOneDayOverdueWhenBookCheckedSixDaysAgo() {
        //given
        String expected = returnReport(0, 1);

        //when
        String actual = ReportGenerator.generateReport(book, new Overdue(0, 1));

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnReportWithTenDaysOverdueWhenBookCheckedFifteenDaysAgo() {
        //given
        String expected = returnReport(0, 10);

        //when
        String actual = ReportGenerator.generateReport(book, new Overdue(0, 10));

        //then
        assertEquals(expected, actual);
    }

    private String returnReport(int daysLeft, int daysOverdue) {
        StringBuilder builder = new StringBuilder();
        builder.append(REPORT_FIRST_PART)
                .append(daysOverdue)
                .append(REPORT_SECOND_PART)
                .append(daysLeft)
                .append("\n");
        return builder.toString();
    }
}