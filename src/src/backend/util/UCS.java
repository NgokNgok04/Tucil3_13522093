package src.backend.util;
import java.util.*;
public class UCS {
    public UCS(){}

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

    public List<String> invertListString(List<String> solution){
        List<String> newSolution = new ArrayList<>(100);
        for(int i = 0; i < solution.size(); i++){
            newSolution.add(0,solution.get(i));
        }

        return newSolution;
    }

    public List<String> algorithmUCS(String start, String end, PriorityQueue wordQueue, Map<String,Boolean> visitedWord, MyDictionary dictionary, int nodeVisited){
        List<String> wordNextMove = new ArrayList<>();
        
        if (wordQueue.isEmpty()){
            Node startNode = new Node(start);
            wordQueue.insertPair(new Pair(startNode, startNode.getNodeLength()));
            visitedWord.put(start, true);
    
        }
        wordNextMove = dictionary.findAllPossibleWord(wordQueue.getPair(0).getNode().getValue(), end, visitedWord);
        nodeVisited++;
        if (foundEnd(wordNextMove, end)){
            Node nodeSolution = wordQueue.getPair(0).getNode();
            nodeSolution.concatNode(new Node(end));
            
            List<String> solution = nodeSolution.getNextNode().convertNodeToArrayFromBackward();
            solution = invertListString(solution);
            solution.add(String.valueOf(nodeVisited));
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
                wordQueue.insertPair(new Pair(newNode, newNode.getNodeLength()) );
                visitedWord.put(wordNextMove.get(i), true);
            }
        }
        wordQueue.deletePair(pairTemplate);
        if(wordQueue.getLength() == 0){
            return new ArrayList<>();
        }
        return algorithmUCS(start, end, wordQueue,visitedWord, dictionary, nodeVisited);
        
    }
    
}
