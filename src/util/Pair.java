package util;


public class Pair {
    private Node currentNode;
    private int value;

    public Pair(){
        this.value = 100;
        this.currentNode = new Node("");
    }

    public Pair(Node currentNode, int value){
        this.currentNode = currentNode;
        this.value = value;
    }

    public Node getNode() {
        return this.currentNode;
    }

    public int getValue() {
        return this.value;
    }

    public void displayPair(){
        System.out.print("(");
        System.out.print(currentNode.getValue());
        System.out.print(",");
        System.out.print(this.getValue());
        System.out.print(")");
    }
}
