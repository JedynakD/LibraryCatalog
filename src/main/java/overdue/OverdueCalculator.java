package overdue;

import model.Book;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;

import java.util.Set;

/**
 * Created by Damian on 2016-08-19.
 */
public final class OverdueCalculator {
    private static final long BOOK_RETURNED = 0L;
    public static final int DEFAULT_MAX_LOAN_PERIOD_IN_DAYS = 5;
    public static final int DEFAULT_ONE_DAY_OVERDUE_FEE = 10;

    private static OverdueCalculator defaultCalculator = new OverdueCalculator(DEFAULT_MAX_LOAN_PERIOD_IN_DAYS, DEFAULT_ONE_DAY_OVERDUE_FEE);

    private final int maxLoanPeriodInDays;
    private final int oneDayOverdueFee;

    private OverdueCalculator(int maxLoanPeriodInDays, int oneDayOverdueFee) {
        this.maxLoanPeriodInDays = maxLoanPeriodInDays;
        this.oneDayOverdueFee = oneDayOverdueFee;
    }

    public static OverdueCalculator createDefault() {
        return defaultCalculator;
    }

    public static OverdueCalculator createCustom(int maxLoanPeriodInDays, int oneDayOverDueFee) {
        return new OverdueCalculator(maxLoanPeriodInDays, oneDayOverDueFee);
    }

    public Overdue returnOverdueForBook(Book book) {
        if (book.getCheckOutTime() == BOOK_RETURNED) {
            return new Overdue(maxLoanPeriodInDays, 0);
        }
        int daysSinceCheckOut = returnDaysSinceCheckOut(book);
        return new Overdue(calculateDaysLeftBeforeOverdue(daysSinceCheckOut), calculateDaysOverdue(daysSinceCheckOut));
    }

    private int calculateDaysOverdue(int daysSinceCheckOut) {
        int daysOverdue = daysSinceCheckOut - maxLoanPeriodInDays;
        return returnDays(daysOverdue);
    }

    private int calculateDaysLeftBeforeOverdue(int daysSinceCheckOut) {
        int daysLeftBeforeOverdue = maxLoanPeriodInDays - daysSinceCheckOut;
        return returnDays(daysLeftBeforeOverdue);
    }

    private int returnDays(int days) {
        return days <= 0 ? 0 : days;
    }

    private int returnDaysSinceCheckOut(Book book) {
        DateTime checkOutTime = new DateTime(book.getCheckOutTime());
        Days days = Days.daysBetween(checkOutTime, returnTodayTime());
        return days.getDays();
    }

    private DateTime returnTodayTime() {
        return new DateTime(DateTimeZone.UTC);
    }

    public int calculateFeeForOne(Book book) {
        return calculateDaysOverdue(returnDaysSinceCheckOut(book)) * oneDayOverdueFee;
    }

    public int calculateFeeForAll(Set<Book> books) {
        return books.stream().mapToInt(book -> calculateFeeForOne(book)).sum();
    }
}
