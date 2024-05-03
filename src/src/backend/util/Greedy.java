package src.backend.util;
import java.util.*;
public class Greedy {
    public Greedy(){};

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


    public String[] algorithmGreedy(String start, String end, PriorityQueue wordQueue, Map<String,Boolean> visitedWord,int count){
        String[] wordNextMove = new String[0];
        count++;
        if (wordQueue.isEmpty()){
            wordNextMove = Dictionary.findAllPossibleWord(start);

            // for(int i = 0; i < wordNextMove.length; i++){
            //     if (visitedWord.get(wordNextMove[i]) == null){
            //         List<String> sequenceWordToConvert = Arrays.asList(wordNextMove[i]);
            //         wordQueue.insertPair(convertSequenceToPair(sequenceWordToConvert, end));
            //         visitedWord.put(wordNextMove[i], true);
            //     }
            // }
            // return algorithmGreedy(start, end, wordQueue, visitedWord,count);
        } else {
            wordNextMove = Dictionary.findAllPossibleWord(wordQueue.getPair(0).getWord());
        }

        int index = foundEnd(wordNextMove, end);
        if (index != -1){
            List<String> solution = new ArrayList<>();

            if (!wordQueue.isEmpty()){
                for(int i = 0; i < wordQueue.getPair(0).getSequenceWord().size(); i++){
                    solution.add(wordQueue.getPair(0).getSequenceWord().get(i));
                }
            }
            
            solution.add(0,start);
            solution.add(solution.size(),end);
            System.out.println("COUNT : " +count);
            return solution.toArray(new String[0]); 
        }

        List<String> sequenceWordToConvert;
        if (wordQueue.isEmpty()){
            wordQueue.insertPair(new Pair());
        }
        Pair pairToDelete = wordQueue.getPair(0);
        
        for(int i = 0; i < wordNextMove.length; i++){
            sequenceWordToConvert = new ArrayList<>();

            if (visitedWord.get(wordNextMove[i]) == null){
                if (!wordQueue.isEmpty()){
                    for(int j = 0; j < pairToDelete.getSequenceWord().size(); j++){
                        sequenceWordToConvert.add(pairToDelete.getSequenceWord().get(j));
                    }
                    sequenceWordToConvert.add(sequenceWordToConvert.size(),wordNextMove[i]);
                    wordQueue.insertPair(convertSequenceToPair(sequenceWordToConvert, end));
                    
                } else {
                    sequenceWordToConvert.add(wordNextMove[i]);
                    wordQueue.insertPair(convertSequenceToPair(sequenceWordToConvert, end));
                }
                visitedWord.put(wordNextMove[i], true);

            }
        }
        
        wordQueue.deletePair(pairToDelete);
        return algorithmGreedy(start, end, wordQueue,visitedWord,count);
        
    }

}