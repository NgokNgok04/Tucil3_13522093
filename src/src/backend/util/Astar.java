package src.backend.util;

import java.util.*;
public class Astar {
    public Astar(){};

    public int calculateCost(Node word, String end){
        int cost = 0;
        char[] charWord = word.getValue().toCharArray();
        char[] charTarget = end.toCharArray();

        for(int j = 0; j < charWord.length; j++){
            if(charWord[j] != charTarget[j]){
                cost++;
            }
        }

        cost += word.getNodeLength();
        return cost;
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

    public boolean foundEnd(List<String> wordNextMove, String target){
        for(int i = 0; i < wordNextMove.size(); i++){
            if (wordNextMove.get(i).equals(target)){
                return true;
            }
        }
        
        return false;
    }

    public List<String> algorithmAstar(String start, String end, PriorityQueue wordQueue, Map<String,Boolean> visitedWord, MyDictionary dictionary){
        List<String> wordNextMove = new ArrayList<>();
        
        if (wordQueue.isEmpty()){
            Node startNode = new Node(start);
            wordQueue.insertPair(new Pair(startNode, calculateCost(startNode, end)));
            visitedWord.put(start, true);
    
        }
        wordNextMove = dictionary.findAllPossibleWord(wordQueue.getPair(0).getNode().getValue(), end, visitedWord);

        if (foundEnd(wordNextMove, end)){
            Node nodeSolution = wordQueue.getPair(0).getNode();
            nodeSolution.concatNode(new Node(end));
            
            List<String> solution = nodeSolution.getNextNode().convertNodeToArrayFromBackward();
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
                wordQueue.insertPair(new Pair(newNode, calculateCost(newNode, end)) );
                visitedWord.put(wordNextMove.get(i), true);
            }
        }
        wordQueue.deletePair(pairTemplate);
        if(wordQueue.getLength() == 0){
            return new ArrayList<>();
        }
        return algorithmAstar(start, end, wordQueue,visitedWord, dictionary);
        
    }
}