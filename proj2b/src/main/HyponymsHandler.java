package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import clas.myData;
import clas.myGraph;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    private myData data;
    private myGraph graph;

    public HyponymsHandler(myData data,myGraph graph){
        this.data = data;
        this.graph = graph;
    }

    @Override
    public String handle(NgordnetQuery q) {
        Set<String> result = new HashSet<>();
        boolean first = true;
        for (String w:q.words()){
            Set<String> words = new HashSet<>();

            Set<Integer> head = data.wordfind(w);
            if (head == null){
                return "[]";
            }
            for (Integer i:head){
                Set<Integer> finder = graph.findall(i);
                for (Integer j:finder){
                    Set<String> term = data.idfind(j);
                    words.addAll(term);
                }
                if (first){
                    result.addAll(words);
                    first= false;
                }else {
                    result.retainAll(words);
                }

            }
        }
        List<String> sorted = new ArrayList<>(result);
        Collections.sort(sorted);

        return sorted.toString();
    }
}
