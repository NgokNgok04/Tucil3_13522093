package src.backend.util;
import java.util.*;
public class Greedy {
    public Greedy(){};
    public void displayListString(List<String> wordNextMove){
        System.out.print("[");
        for(int i = 0; i < wordNextMove.size(); i++){
            System.out.print(wordNextMove.get(i));
            if (i != wordNextMove.size() - 1){
                System.out.print(", ");
            }
            
        }
        System.out.println("]");
    }

    public String findCheapestCost(String currentWord, String end, Map<String,Boolean> visitedWord, MyDictionary dictionary){
        List<String> word = dictionary.findAllPossibleWord(currentWord, visitedWord);
        if (word.size() == 0){
            return "Not Found";
        }

        char[] charTarget = end.toCharArray();
        int cheapestCost = 100;
        String cheapestWord = word.get(0);
        for(int i = 0; i < word.size(); i++){
            int cost = 0;
            char[] charWord = word.get(i).toCharArray();
            
            for(int j = 0; j < charWord.length; j++){
                cost += Math.abs(charTarget[j] - charWord[j]);
            }

            if (cost <= cheapestCost){
                cheapestCost = (int) cost;
                cheapestWord = new String(word.get(i));
            }

        }
        
        return cheapestWord;
    }

    
    // PriorityQueue -> Pair -> Node -> String
    public List<String> algorithmGreedy(String start, String end, List<String> wordQueue,Map<String,Boolean> visitedWord, MyDictionary dictionary){

        if(wordQueue.isEmpty()){
            wordQueue.add(start);
            visitedWord.put(start, true);
            return algorithmGreedy(start, end, wordQueue, visitedWord, dictionary);
        } else {
            String nextWord = new String(wordQueue.get(wordQueue.size() - 1));
            String cheapestWord = findCheapestCost(nextWord, end, visitedWord,dictionary);
            if (cheapestWord.equals("Not Found")){
                return new ArrayList<>();
            }

            wordQueue.add(cheapestWord);
            visitedWord.put(cheapestWord, true);

            if (cheapestWord.equals(end)){
                visitedWord.put(cheapestWord, true);
                return wordQueue;
            }

            return algorithmGreedy(start, end, wordQueue, visitedWord, dictionary);
        }
    }
}