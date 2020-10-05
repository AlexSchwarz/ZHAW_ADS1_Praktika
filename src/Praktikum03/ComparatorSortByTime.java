package Praktikum03;

import java.util.Comparator;

public class ComparatorSortByTime implements Comparator<Competitor> {

    @Override
    public int compare(Competitor o1, Competitor o2) {
        if (o1.getTime() < o2.getTime()) return -1;
        else if (o1.getTime() > o2.getTime()) return 1;
        else return 0;
    }
}
