package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import org.knowm.xchart.XYChart;
import plotting.Plotter;

import java.util.ArrayList;
import java.util.List;

public class HistoryHandler extends NgordnetQueryHandler {
    private NGramMap map;
    public HistoryHandler(NGramMap map){
        this.map = map;
    }


    @Override
    public String handle(NgordnetQuery q) {
        TimeSeries T;
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        ArrayList<TimeSeries> lts = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        for(String w:words){
            T = map.weightHistory(w,startYear,endYear);
            labels.add(w);
            lts.add(T);
        }
        XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);
        String encodedImage = Plotter.encodeChartAsString(chart);
        return encodedImage;
    }
}

//for (int i = 1400; i < 1500; i += 1) {
//        parabola.put(i, (i - 50.0) * (i - 50.0) + 3);
//        }
//
//TimeSeries sinWave = new TimeSeries();
//        for (int i = 1400; i < 1500; i += 1) {
//        sinWave.put(i, 1000 + 500 * Math.sin(i/100.0*2*Math.PI));
//        }
//
//ArrayList<TimeSeries> lts = new ArrayList<>();
//ArrayList<String> labels = new ArrayList<>();
//
//        labels.add("parabola");
//        labels.add("sine wave");
//
//        lts.add(parabola);
//        lts.add(sinWave);
//
//XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);
//String encodedImage = Plotter.encodeChartAsString(chart);
//
//        return encodedImage;