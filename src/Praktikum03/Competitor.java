package Praktikum03;

import java.util.*;
import java.text.*;

public class Competitor {
    private String name;
    private String country;
    private long time;
    private int jg;
    private int startNr;
    private int rank;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competitor that = (Competitor) o;
        return Objects.equals(name.toLowerCase(), that.name.toLowerCase());
    }

    public Competitor(int startNr, String name, int jg, String country, String time) {
        this.startNr = startNr;
        this.name = name;
        this.jg = jg;
        this.country = country;
        try {
            this.time = parseTime(time);
        } catch (ParseException e) {
            this.time = 1;
        }
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public int getJg() {
        return jg;
    }

    private static long parseTime(String s) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("HH:mm:ss.S");
        Date date = sdf.parse(s);
        return date.getTime();
    }

    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.S");
        StringBuilder sb = new StringBuilder();
        sb.append(rank);sb.append(" ");
        sb.append(name); sb.append(" ");
        sb.append(Integer.toString(jg)); sb.append(" ");
        sb.append(df.format(new Date(time)));
        return sb.toString();
    }
}
