package overdue;

import model.Book;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;

import java.util.Set;

/**
 * Created by Damian on 2016-08-19.
 */
public class OverdueCalculator {
    private static final long BOOK_RETURNED = 0L;

    private static OverdueCalculator defaultCalculator = new OverdueCalculator();

    private int maxLoanPeriodInDays = 5;
    private int oneDayOverdueFee = 10;

    private OverdueCalculator() {
    }

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
        return new Overdue(returnDaysLeftBeforeOverdue(daysSinceCheckOut), returnDaysOverdue(daysSinceCheckOut));
    }

    private int returnDaysOverdue(int daysSinceCheckOut) {
        int daysOverdue = daysSinceCheckOut - maxLoanPeriodInDays;
        return returnDays(daysOverdue);
    }

    private int returnDaysLeftBeforeOverdue(int daysSinceCheckOut) {
        int daysLeftBeforeOverdue = maxLoanPeriodInDays - daysSinceCheckOut;
        return returnDays(daysLeftBeforeOverdue);
    }

    private int returnDays(int daysOverdue) {
        if (daysOverdue <= 0) {
            return 0;
        } else {
            return daysOverdue;
        }
    }

    private int returnDaysSinceCheckOut(Book book) {
        long checkOutTimeInMillis = book.getCheckOutTime();
        DateTime checkOutTime = new DateTime(checkOutTimeInMillis);
        Days days = Days.daysBetween(checkOutTime, returnTodayTime());
        return days.getDays();
    }

    private DateTime returnTodayTime() {
        return new DateTime(DateTimeZone.UTC);
    }

    public int calculateFeeForAll(Set<Book> books) {
        int overdueFee = 0;
        for (Book book : books) {
            overdueFee = overdueFee + calculateFeeForOne(book);
        }
        return overdueFee;
    }

    public int calculateFeeForOne(Book book) {
        return returnDaysOverdue(returnDaysSinceCheckOut(book)) * oneDayOverdueFee;
    }
}
