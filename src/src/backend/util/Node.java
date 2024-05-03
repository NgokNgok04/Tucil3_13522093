package src.backend.util;

public class Node {
    private Node previousNode;
    private String current;
    private Node nextNode;


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

    public void concatNode(Node nextNode){
        this.nextNode = nextNode;
        nextNode.previousNode = this;
    }

    public void displayNodeForward(){
        System.out.print("[");

        System.out.print(this.current);
        while (nextNode != null){
            System.out.print(", " +nextNode.current);
            nextNode = nextNode.nextNode;
        }
        // System.out.print(nextNode.current);
        
        System.out.println("]");
    }
    
    public void displayNodePrevious(){
        System.out.print("[");
        System.out.print(this.current);
        while(previousNode != null){
            System.out.print(", " + previousNode.current);
            previousNode = previousNode.previousNode;
        }
        System.out.println("]");
    }
}
