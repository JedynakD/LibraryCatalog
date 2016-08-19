package overdue;

/**
 * Created by Damian on 2016-08-19.
 */
public class Overdue {
    private int daysLeftToOverdue;
    private int daysOverdue;

    public Overdue(int daysLeftToOverdue, int daysOverdue) {
        this.daysLeftToOverdue = daysLeftToOverdue;
        this.daysOverdue = daysOverdue;
    }

    public int getDaysLeftToOverdue() {
        return daysLeftToOverdue;
    }

    public int getDaysOverdue() {
        return daysOverdue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Overdue overdue = (Overdue) o;

        if (daysLeftToOverdue != overdue.daysLeftToOverdue) return false;
        return daysOverdue == overdue.daysOverdue;

    }

    @Override
    public int hashCode() {
        int result = daysLeftToOverdue;
        result = 31 * result + daysOverdue;
        return result;
    }

    @Override
    public String toString() {
        return "Overdue{" +
                "daysLeftToOverdue=" + daysLeftToOverdue +
                ", daysOverdue=" + daysOverdue +
                '}';
    }
}
