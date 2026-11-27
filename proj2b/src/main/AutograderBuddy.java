package main;

import browser.NgordnetQueryHandler;
import clas.myData;
import clas.myGraph;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymsHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {
        myData data = new myData(synsetFile);
        myGraph graph = new myGraph(hyponymFile);
        return new HyponymsHandler(data,graph);
    }
}
