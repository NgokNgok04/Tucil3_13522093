package src.backend.util;
import java.util.*;
public class UCS {
    public UCS(){};

    public int calculateCost(String word, String end){
        int cost = 0;
        char[] charWord = word.toCharArray();
        char[] charTarget = end.toCharArray();
        for(int i = 0; i < charWord.length; i++){
            cost += Math.abs(charTarget[i] - charWord[i]);
        }
        
        return cost;
    }

    public Pair convertSequenceToPair(List<String> sequenceWord, String end){
        int value = calculateCost(sequenceWord.get(sequenceWord.size() - 1), end);
        Pair newPair = new Pair(sequenceWord, value);
        return newPair;
    }

    public int foundEnd(String[] wordNextMove, String target){
        for(int i = 0; i < wordNextMove.length; i++){
            if (wordNextMove[i].equals(target)){
                return i;
            }
        }
        
        return -1;
    }


    public String[] algorithmUCS(String start, String end, PriorityQueue wordQueue){
        String[] wordNextMove = new String[0];
        if (wordQueue.isEmpty()){
            wordNextMove = Dictionary.findAllPossibleWord(start);

            for(int i = 0; i < wordNextMove.length; i++){
                List<String> sequenceWordToConvert = Arrays.asList(wordNextMove[i]);
                wordQueue.insertPair(convertSequenceToPair(sequenceWordToConvert, end));
            }
            return algorithmUCS(start, end, wordQueue);
        }

        wordNextMove = Dictionary.findAllPossibleWord(wordQueue.getPair(0).getWord());

        
        int index = foundEnd(wordNextMove, end);
        if (index != -1){
            List<String> solution = new ArrayList<>();
            for(int i = 0; i < wordQueue.getPair(0).getSequenceWord().size(); i++){
                solution.add(wordQueue.getPair(0).getSequenceWord().get(i));
            }
            // List<String> solution = wordQueue.getPair(0).getSequenceWord();
            solution.add(0,start);
            solution.add(solution.size(),end);
            return solution.toArray(new String[0]); 
        }
        
        for(int i = 0; i < wordNextMove.length; i++){
            List<String> sequenceWordToConvert = new ArrayList<>();

            for(int j = 0; j < wordQueue.getPair(0).getSequenceWord().size(); i++){
                sequenceWordToConvert.add(sequenceWordToConvert.size(),wordQueue.getPair(0).getSequenceWord().get(j));

            }
            sequenceWordToConvert.add(sequenceWordToConvert.size(),wordNextMove[i]);
            wordQueue.insertPair(convertSequenceToPair(sequenceWordToConvert, end));
            // System.out.println("Insert");
        }
        
        wordQueue.deletePair(wordQueue.getPair(0));
        return algorithmUCS(start, end, wordQueue);
        
    }

}

// A-1 B-2 C-
//  Sing [19 -> 8,0,0, 7 -> 4]
//  King [0,0,0, 7 -> 4]
//  Kind [0,0,0,0]
//  
//  
//  