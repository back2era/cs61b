package clas;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class myData {
    Map<String,Set<Integer>> wordToIds = new HashMap<>();
    Map<Integer, Set<String>> idToWords = new HashMap<>();
    public myData(String synsetFile){
        In synset = new In(synsetFile);
        while(synset.hasNextLine()){
            String line = synset.readLine();
            String[] parts = line.split(",",3);
            int id = Integer.parseInt(parts[0]);

            Set<String> words = new HashSet<>(
                    Arrays.asList(parts[1].split(" "))
            );

            for (String word:words){
                if (!wordToIds.containsKey(word)){
                    wordToIds.put(word,new HashSet<>());
                }

                wordToIds.get(word).add(id);
            }

            idToWords.put(id, words);
        }

        synset.close();

    }
    public Set<String> idfind(int id){
        return idToWords.get(id);

    }

    public Set<Integer> wordfind(String word){
        return wordToIds.get(word);
    }
}
