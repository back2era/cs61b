package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import browser.NgordnetQueryType;
import clas.myData;
import clas.myGraph;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.*;

import static browser.NgordnetQueryType.HYPONYMS;

public class HyponymsHandler extends NgordnetQueryHandler {
    private myData data;
    private myGraph graph;
    private NGramMap map;

    public HyponymsHandler(myData data, myGraph graph, NGramMap map){
        this.data = data;
        this.graph = graph;
        this.map = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        NgordnetQueryType ngordnetQueryType = q.ngordnetQueryType();
        int k = q.k();
        int startyear = q.startYear();
        int endyear = q.endYear();
        Set<String> result = new HashSet<>();

        if (ngordnetQueryType.equals(HYPONYMS)) {


            boolean first = true;
            for (String w : q.words()) {
                Set<String> words = new HashSet<>();

                Set<Integer> head = data.wordfind(w);
                if (head == null) {
                    return "[]";
                }
                for (Integer i : head) {
                    Set<Integer> finder = graph.findall(i);
                    for (Integer j : finder) {
                        Set<String> term = data.idfind(j);
                        words.addAll(term);
                    }


                }
                if (first) {
                    result.addAll(words);
                    first = false;
                } else {
                    result.retainAll(words);
                }
            }


            PriorityQueue<Map.Entry<String, Double>> pq = new PriorityQueue<>((a, b) -> Double.compare(b.getValue(), a.getValue()));

            if (k != 0) {
                for (String w : result) {
                    TimeSeries T = map.countHistory(w, startyear, endyear);

                    Double n = T.sum();
                    if (n > 0) {
                        pq.add(Map.entry(w, n));
                    }
                }
                Set<String> resultnew = new HashSet<>();

                for (int i = 0; i < k && !pq.isEmpty(); i++) {
                    resultnew.add(pq.poll().getKey());
                }
                result = resultnew;
            }
        }else {
            boolean first = true;
            for (String w : q.words()) {
                Set<String> words = new HashSet<>();

                Set<Integer> head = data.wordfind(w);
                if (head == null) {
                    return "[]";
                }
                result.addAll(ansfinder(w));
                if (first) {
                    result.addAll(words);
                    first = false;
                } else {
                    result.retainAll(words);
                }
            }

        }


        List<String> sorted = new ArrayList<>(result);
        Collections.sort(sorted);

        return sorted.toString();
    }

    private Set<String> ansfinder(String word){
        Set<String> result = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> head = data.wordfind(word);
        if (head == null) {
            return result;
        }

        for (int i : head) {
            dfs(i, visited, result);
        }




        return result;
    }

    private void dfs(int id, Set<Integer> visited,
                     Set<String> result) {

        if (!visited.add(id)) {
            return;
        }

        result.addAll(data.idfind(id));

        Set<Integer> parents = graph.findDadId(id);

        if (parents == null) {
            return;
        }

        for (int parent : parents) {
            dfs(parent, visited, result);
        }
    }
}
