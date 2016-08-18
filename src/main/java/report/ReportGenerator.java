package report;

import model.Book;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;

import java.util.Set;

/**
 * Created by Damian on 2016-08-18.
 */
public class ReportGenerator {
    private static final int MAX_LOAN_PERIOD_IN_DAYS = 5;
    private static final long BOOK_RETURNED = 0L;

    private static int daysLeft;


    private ReportGenerator() {
    }

    public static String generateReport(Set<Book> books) {
        StringBuilder builder = new StringBuilder();
        for (Book book : books) {
            builder.append(book.getName())
                    .append(", ")
                    .append(book.getAuthorName())
                    .append(" is ")
                    .append(Math.abs(calculateOverdueDays(book)))
                    .append(" days overdue.")
                    .append(" Days left: ")
                    .append(returnDaysLeftBeforeOverdue())
                    .append("\n");
        }
        return builder.toString();
    }

    private static int calculateOverdueDays(Book book) {
        long checkOutDateInMillis = book.getCheckOutTime();
        DateTime checkOutDate = new DateTime(checkOutDateInMillis);
        Days days = Days.daysBetween(checkOutDate, returnTodayTime());
        int daysLeft = returnDaysLeft(days);
        if (checkOutDateInMillis == BOOK_RETURNED || daysLeft >= 0) {
            return 0;
        } else {
            return daysLeft;
        }
    }

    private static int returnDaysLeftBeforeOverdue() {
        if (daysLeft < 0) {
            return 0;
        }
        return daysLeft;
    }

    private static int returnDaysLeft(Days days) {
        daysLeft = MAX_LOAN_PERIOD_IN_DAYS - days.getDays();
        return daysLeft;
    }

    private static DateTime returnTodayTime() {
        return new DateTime(DateTimeZone.UTC);
    }
}
