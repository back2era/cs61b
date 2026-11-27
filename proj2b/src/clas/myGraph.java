package clas;

import edu.princeton.cs.algs4.In;
import org.apache.hc.core5.annotation.Internal;

import java.util.*;

public class myGraph {
    Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
    Set<Integer> allnum = new HashSet<>();
    public myGraph(String hyponymFile){
        In hyponym = new In(hyponymFile);
        while(hyponym.hasNextLine()){
            String line = hyponym.readLine();
            String[] parts = line.split(",");
            int parent = Integer.parseInt(parts[0]);
            Set<Integer> set = new HashSet<>();

            for (int i = 1;i < parts.length; i++){
                set.add(Integer.parseInt(parts[i]));
            }
            if (!adjacencyList.containsKey(parent)) {
                adjacencyList.put(parent, set);
            } else {
                adjacencyList.get(parent).addAll(set);
            }
        }
    }

    public Set<Integer> find(Integer i) {
        return adjacencyList.getOrDefault(i, Collections.emptySet());
    }

    public Set<Integer> findall(Integer i){
        Set<Integer> allnum = new HashSet<>();
        dfs(i, allnum);
        return allnum;
    }

    private void dfs(Integer i,Set<Integer> allnum){
        if (allnum.contains(i)){
            return;
        }

        allnum.add(i);

        for (Integer num:find(i)){
            dfs(num,allnum);
        }
    }
}
