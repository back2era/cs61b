package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap M;
    public HistoryTextHandler(NGramMap map){
        this.M = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        String response = "";
        for (String w:words){
            TimeSeries T = M.weightHistory(w,startYear,endYear);
            response += w + ": " + T.toString() + "\n";
        }

        return response;
    }
}

//String response = "You entered the following info into the browser:\n";
//response += "Words: " + q.words() + "\n";
//response += "Start Year: " + q.startYear() + "\n";
//response += "End Year: " + q.endYear() + "\n";
