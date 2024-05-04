package src.backend.util;
import java.util.*;
public class Node {
    private Node previousNode;
    private String current;
    private Node nextNode;

    public Node(Node currentNode){
        this.current = currentNode.current;
        this.previousNode = currentNode.previousNode;
        this.nextNode = currentNode.nextNode;
    }

    public Node(String current){
        this.current = current;
        this.nextNode = null;
        this.previousNode = null;
    }
    
    public String getValue(){
        return this.current;
    }
    
    public Node getNextNode(){
        return this.nextNode;
    }

    public Node getPreviousNode(){
        return this.previousNode;
    }

    public void setValue(String current){
        this.current = current;
    }

    public void setNextNode(Node nextNode){
        this.nextNode = nextNode;
    }

    public void setPreviousNode(Node previousNode){
        this.previousNode = previousNode;
    }

    public boolean isEqual(Node currentNode){
        if (this.current.equals(currentNode.current)){
            return false;
        }

        return true;
    }


    public void concatNode(Node nextNode){
        this.nextNode = nextNode;
        nextNode.previousNode = this;
    }

    public List<String> convertNodeToArrayFromBackward(){
        List<String> solution = new ArrayList<>(100);
        solution.add(this.current);

        while(previousNode != null){
            solution.add(previousNode.current);
            previousNode = previousNode.previousNode;
        }
        
        return solution;
    }

    public void displayNodeForward(){
        System.out.print("[");

        System.out.print(this.current);
        while (nextNode != null){
            System.out.print(", " +nextNode.current);
            nextNode = nextNode.nextNode;
        }
        
        System.out.println("]");
    }
    
    public void displayNodeBackward(){
        System.out.print("(");
        System.out.print(this.current);
        while(previousNode != null){
            System.out.print(", " + previousNode.current);
            previousNode = previousNode.previousNode;
        }
        System.out.print(")");
    }
}
