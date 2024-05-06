package src.backend.util;

import java.time.*;
import java.util.*;

public class Process {
    public int timeExecution;
    public int nodeVisited;
    public int len;
    public String[] solution;

    private static Instant startTime;

    public Process(String startWord, String endWord, String algorithmType){

        startTime = Instant.now();
        List<String> solution;
        PriorityQueue wordQueue = new PriorityQueue(100000);
        Map<String,Boolean> visitedWord = new HashMap<>(100000);

        if (algorithmType.equals("GBFS")){
            Greedy greedyObject = new Greedy();
            List<String> wordListQueue = new ArrayList<>();
            solution = greedyObject.algorithmGreedy(startWord, endWord, wordListQueue, visitedWord);
        } else {
            UCS ucsObject = new UCS();
            solution = ucsObject.algorithmUCS(startWord, endWord, wordQueue, visitedWord);
        }
        Instant endTime = Instant.now();
        Duration duration = Duration.between(startTime, endTime);
        this.timeExecution = (int) duration.toMillis();
        this.len = solution.size();
        this.solution = solution.toArray(new String[0]);
        this.nodeVisited = 10;
    }
}
