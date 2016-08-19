package report;

import overdue.Overdue;
import model.Book;

/**
 * Created by Damian on 2016-08-18.
 */
public class ReportGenerator {
    private ReportGenerator() {
    }

    public static String generateReport(Book book, Overdue overdue) {
        StringBuilder builder = new StringBuilder();
            builder.append(book.getName())
                    .append(", ")
                    .append(book.getAuthorName())
                    .append(" is ")
                    .append(Math.abs(overdue.getDaysOverdue()))
                    .append(" days overdue.")
                    .append(" Days left: ")
                    .append(overdue.getDaysLeftToOverdue())
                    .append("\n");
        return builder.toString();
    }
}
