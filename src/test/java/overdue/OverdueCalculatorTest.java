package overdue;

import model.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class OverdueCalculatorTest {

    private OverdueCalculator overdueCalculator;
    private Book firstBook;
    private Book secondBook;

    @Before
    public void setUp() {
        overdueCalculator = OverdueCalculator.createCustom(5, 10);
        firstBook = Mockito.mock(Book.class);
        secondBook = Mockito.mock(Book.class);
    }

    @Test
    public void shouldReturnOverdueWithDaysLeftFiveAndOverdueZeroWhenBookCheckedOutToday() {
        LocalDate yesterday = returnTodayTime();
        Mockito.when(firstBook.getCheckOutTime()).thenReturn(yesterday);

        Overdue actual = overdueCalculator.returnOverdueForBook(firstBook);

        assertEquals(new Overdue(5, 0), actual);
    }

    @Test
    public void shouldReturnOverdueWithDaysLeftZeroAndOverdueZeroWhenBookCheckedFiveDaysAgo() {
        LocalDate fiveDaysAgo = returnTodayTime().minusDays(5);
        Mockito.when(firstBook.getCheckOutTime()).thenReturn(fiveDaysAgo);

        Overdue actual = overdueCalculator.returnOverdueForBook(firstBook);

        assertEquals(new Overdue(0, 0), actual);
    }

    @Test
    public void shouldReturnOverdueWithDaysLeftZeroAndOverdueOneWhenBookCheckedSixDaysAgo() {
        LocalDate sixDaysAgo = returnTodayTime().minusDays(6);
        Mockito.when(firstBook.getCheckOutTime()).thenReturn(sixDaysAgo);

        Overdue actual = overdueCalculator.returnOverdueForBook(firstBook);

        assertEquals(new Overdue(0, 1), actual);
    }

    @Test
    public void shouldReturnOverdueWithDaysLeftZeroAndOverdueTenWhenBookCheckedFifteenDaysAgo() {
        LocalDate fifteenDaysAgo = returnTodayTime().minusDays(15);
        Mockito.when(firstBook.getCheckOutTime()).thenReturn(fifteenDaysAgo);

        Overdue actual = overdueCalculator.returnOverdueForBook(firstBook);

        assertEquals(new Overdue(0, 10), actual);
    }

    @Test
    public void shouldReturnFeeZeroWhenBookIsNotOverdue() {
        LocalDate yesterday = returnTodayTime().minusDays(1);
        Mockito.when(firstBook.getCheckOutTime()).thenReturn(yesterday);

        int actual = overdueCalculator.calculateFeeForOne(firstBook);

        assertEquals(0, actual);
    }

    @Test
    public void shouldReturnFeeTenWhenBookIsOneDayOverdue() {
        LocalDate sixDaysAgo = returnTodayTime().minusDays(6);
        Mockito.when(firstBook.getCheckOutTime()).thenReturn(sixDaysAgo);

        int actual = overdueCalculator.calculateFeeForOne(firstBook);

        assertEquals(10, actual);
    }


    @Test
    public void shouldReturnFeeTwentyWhenTwoBooksAreOneDayOverdue() {
        LocalDate sixDaysAgo = returnTodayTime().minusDays(6);
        Mockito.when(firstBook.getCheckOutTime()).thenReturn(sixDaysAgo);
        Mockito.when(secondBook.getCheckOutTime()).thenReturn(sixDaysAgo);

        int actual = overdueCalculator.calculateFeeForAll(new HashSet<>(Arrays.asList(firstBook, secondBook)));

        assertEquals(20, actual);
    }

    private LocalDate returnTodayTime() {
        return LocalDate.now();
    }
}