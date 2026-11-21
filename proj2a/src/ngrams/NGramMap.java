package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    private Map<String,TimeSeries> wordData;
    private TimeSeries totalCounts;


    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        In words = new In(wordsFilename);
        wordData = new HashMap<>();
        while(words.hasNextLine()){
            String line = words.readLine();
            String[] parts = line.split("\t");
            if (!wordData.containsKey(parts[0])){
                TimeSeries T = new TimeSeries();
                T.put(Integer.parseInt(parts[1]),Double.parseDouble(parts[2]));
                wordData.put(parts[0],T);
            }else {
                TimeSeries T1 = wordData.get(parts[0]);
                T1.put(Integer.parseInt(parts[1]),Double.parseDouble(parts[2]));
                wordData.put(parts[0],T1);
            }

        }
        words.close();


        In counts = new In(countsFilename);
        totalCounts = new TimeSeries();
        while(counts.hasNextLine()){
            String line = counts.readLine();
            String[] parts = line.split(",");
            int year = Integer.parseInt(parts[0]);
            double c = Double.parseDouble(parts[1]);
            totalCounts.put(year,c);
        }
        counts.close();
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries T = new TimeSeries();
        if (wordData.get(word) == null){
            return T;
        }
        T = wordData.get(word);
        return new TimeSeries(T,startYear,endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        return countHistory(word,MIN_YEAR,MAX_YEAR);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        TimeSeries T = new TimeSeries(totalCounts,MIN_YEAR,MAX_YEAR);
        return T;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries count = countHistory(word,startYear,endYear);
        TimeSeries total = totalCountHistory();
        return count.dividedBy(total);

    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        return weightHistory(word,MIN_YEAR,MAX_YEAR);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries T = new TimeSeries();
        for (String i:words) {
            TimeSeries T1 = weightHistory(i,startYear,endYear);
            T = T.plus(T1);
        }
        return T;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        return summedWeightHistory(words,MIN_YEAR,MAX_YEAR);
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.

}
