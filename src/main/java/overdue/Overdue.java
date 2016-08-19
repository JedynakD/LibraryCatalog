package overdue;

/**
 * Created by Damian on 2016-08-19.
 */
public class Overdue {
    private int daysLeft;
    private int daysOverdue;

    public Overdue(int daysLeft, int daysOverdue) {
        this.daysLeft = daysLeft;
        this.daysOverdue = daysOverdue;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public int getDaysOverdue() {
        return daysOverdue;
    }
}
