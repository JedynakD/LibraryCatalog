package report;

import model.Book;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;

import java.util.Date;
import java.util.Set;

/**
 * Created by Damian on 2016-08-18.
 */
public class ReportGenerator {

    public static String generateReport(Set<Book> books) {
        StringBuilder builder = new StringBuilder();
        for (Book book : books) {
            builder.append(book.getName())
                    .append(", ")
                    .append(book.getAuthorName())
                    .append(" is ")
                    .append(calculateOverdueDays(book))
                    .append(" days overdue.");
        }
        return builder.toString();
    }

    private static int calculateOverdueDays(Book book) {
        long checkOutDateInMillis = book.getCheckOutTime();
        if (checkOutDateInMillis == 0L) {
            return 0;
        } else {
            DateTime checkOutDate = new DateTime(checkOutDateInMillis);
            Days days = Days.daysBetween(returnTodayTime(),checkOutDate);
            return days.getDays();
        }
    }

    private static DateTime returnTodayTime() {
        return new DateTime(DateTimeZone.UTC);
    }
}
