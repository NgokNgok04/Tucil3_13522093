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

    public boolean foundEnd(List<String> wordNextMove, String target){
        for(int i = 0; i < wordNextMove.size(); i++){
            if (wordNextMove.get(i).equals(target)){
                return true;
            }
        }
        
        return false;
    }

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
    // PriorityQueue -> Pair -> Node -> String
    public List<String> algorithmGreedy(String start, String end, PriorityQueue wordQueue, Map<String,Boolean> visitedWord,int count){
        List<String> wordNextMove = new ArrayList<>();
        count++;
        System.out.println("Iterasi " + count);
        if (wordQueue.isEmpty()){
            Node startNode = new Node(start);
            wordQueue.insertPair(new Pair(startNode, calculateCost(start, end)));
            
            System.out.print("\nWORDNEXTMOVE : ");
            displayListString(wordNextMove);
            System.out.print("START WORDQUEUE : ");
            wordQueue.displayWordQueue();
            visitedWord.put(start, true);

        }
        wordNextMove = Dictionary.findAllPossibleWord(wordQueue.getPair(0).getNode().getValue());
        
        if (foundEnd(wordNextMove, end)){
            System.out.println("\nPreference : " + wordQueue.getPair(0).getNode().getValue());
            wordQueue.getPair(0).getNode().displayNodeBackward();
            List<String> solution = wordQueue.getPair(0).getNode().convertNodeToArrayFromBackward();
            solution.add(0,end);
            return solution; 
        }

        Pair pairTemplate = wordQueue.getPair(0);
        Node newNode;
        Node nodeToConnect;
        for(int i = 0; i < wordNextMove.size();i++){
            if (visitedWord.get(wordNextMove.get(i)) == null){
                nodeToConnect = pairTemplate.getNode();
                newNode = new Node(wordNextMove.get(i));
                nodeToConnect.concatNode(newNode);
                wordQueue.insertPair(new Pair(newNode, calculateCost(newNode.getValue(), end)));
                visitedWord.put(wordNextMove.get(i), true);
            }
        }
        // System.out.println("\nPreference : " + pairTemplate.getNode().getValue());
        System.out.print("\nWORDNEXTMOVE : ");
        displayListString(wordNextMove);
        System.out.print("PROCESS WORDQUEUE : ");
        
        wordQueue.displayWordQueue();
        // wordQueue.deletePair(pairTemplate);
        return algorithmGreedy(start, end, wordQueue,visitedWord,count);
        
    }

}