package util;
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

    public int getNodeLength(){
        Node newNode = new Node(this);
        int count = 0;
        while(newNode.previousNode != null){
            count++;
            newNode = newNode.previousNode;
        }
        return count;
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
    
    public void displayNodeBackward(){

        Node newNode = new Node(this);
        System.out.print("(");
        while(newNode.previousNode != null){
            System.out.print(newNode.current + ", ");
            newNode = newNode.previousNode;
        }
        System.out.print(newNode.current);

        System.out.print(")");
    }
}
