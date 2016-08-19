package overdue;

import model.Book;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * Created by Damian on 2016-08-19.
 */
public class OverdueCalculatorTest {

    private OverdueCalculator overdueCalculator;
    private Book book;

    @Before
    public void setUp() {
        overdueCalculator = OverdueCalculator.createCustom(5, 10);
        book = Mockito.mock(Book.class);
    }

    @Test
    public void shouldReturnOverdueWithDaysLeftFiveAndOverdueZeroWhenBookCheckedOutToday() {
        long yesterday = returnTodayTime().getMillis();
        Mockito.when(book.getCheckOutTime()).thenReturn(yesterday);

        Overdue actual = overdueCalculator.returnOverdueForBook(book);

        assertEquals(new Overdue(5, 0), actual);
    }

    @Test
    public void shouldReturnOverdueWithDaysLeftZeroAndOverdueZeroWhenBookCheckedFiveDaysAgo() {
        long fiveDaysAgo = returnTodayTime().minusDays(5).getMillis();
        Mockito.when(book.getCheckOutTime()).thenReturn(fiveDaysAgo);

        Overdue actual = overdueCalculator.returnOverdueForBook(book);

        assertEquals(new Overdue(0, 0), actual);
    }

    @Test
    public void shouldReturnOverdueWithDaysLeftZeroAndOverdueOneWhenBookCheckedSixDaysAgo() {
        long sixDaysAgo = returnTodayTime().minusDays(6).getMillis();
        Mockito.when(book.getCheckOutTime()).thenReturn(sixDaysAgo);

        Overdue actual = overdueCalculator.returnOverdueForBook(book);

        assertEquals(new Overdue(0, 1), actual);
    }

    @Test
    public void shouldReturnOverdueWithDaysLeftZeroAndOverdueTenWhenBookCheckedFifteenDaysAgo() {
        long fifteenDaysAgo = returnTodayTime().minusDays(15).getMillis();
        Mockito.when(book.getCheckOutTime()).thenReturn(fifteenDaysAgo);

        Overdue actual = overdueCalculator.returnOverdueForBook(book);

        assertEquals(new Overdue(0, 10), actual);
    }

    @Test
    public void shouldReturnFeeZeroWhenBookIsNotOverdue() {
        long yesterday = returnTodayTime().minusDays(1).getMillis();
        Mockito.when(book.getCheckOutTime()).thenReturn(yesterday);

        int actual = overdueCalculator.calculateFeeForOne(book);

        assertEquals(0, actual);
    }

    @Test
    public void shouldReturnFeeTenWhenBookIsOneDayOverdue() {
        long sixDaysAgo = returnTodayTime().minusDays(6).getMillis();
        Mockito.when(book.getCheckOutTime()).thenReturn(sixDaysAgo);

        int actual = overdueCalculator.calculateFeeForOne(book);

        assertEquals(10, actual);
    }

    private DateTime returnTodayTime() {
        return new DateTime(DateTimeZone.UTC);
    }
}