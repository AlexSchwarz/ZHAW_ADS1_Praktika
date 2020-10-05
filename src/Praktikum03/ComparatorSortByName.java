package Praktikum03;

import java.util.Comparator;

public class ComparatorSortByName implements Comparator<Competitor> {

    @Override
    public int compare(Competitor o1, Competitor o2) {
        int value;
        value = o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
        if (value == 0) {
            if (o1.getJg() < o2.getJg()) value = -1;
            else if (o1.getJg() > o2.getJg()) value = 1;
        }
        return value;
    }
}
