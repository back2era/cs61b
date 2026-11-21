package ngrams;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        // TODO: Fill in this constructor.
        for (Integer year:ts.keySet()){
            if (year >= startYear && year <= endYear){
                this.put(year,ts.get(year));
            }
        }

    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        // TODO: Fill in this method.
        List<Integer> year = new ArrayList<>(this.keySet());
        return year;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        List<Double> datas = new ArrayList<>();
        for (Integer year:years()){
            datas.add(this.get(year));
        }
        return datas;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries T = new TimeSeries();
        if (this.isEmpty() && ts.isEmpty()){
            return T;
        }
        List<Integer> year1 = this.years();
        List<Integer> year2 = ts.years();
        List<Double> data1 = this.data();
        List<Double> data2 = ts.data();
        for (int i = 0; i < this.size(); i++) {
            T.put(year1.get(i),data1.get(i));
        }
        for (int j = 0; j < ts.size(); j++) {
            if (this.containsKey(year2.get(j))){
                T.put(year2.get(j),T.get(year2.get(j)) + data2.get(j));
            }else {
                T.put(year2.get(j),data2.get(j));
            }
        }
        return T;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries T = new TimeSeries();
        List<Integer> year1 = this.years();
        for (int i = 0; i < this.size(); i++) {
            int Y = year1.get(i);
            if (!ts.containsKey(Y)){
                throw new IllegalArgumentException();
            }
            T.put(Y,this.get(Y)/ts.get(Y));
        }

        return T;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
