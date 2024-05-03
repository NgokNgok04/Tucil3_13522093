package src.backend;
import java.util.Arrays;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import src.backend.util.*;


public class Main {
    private static Instant startTime;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // System.out.print("Enter start word : ");
        // String startInput = scanner.nextLine();
        // startInput = startInput.toLowerCase();
        
        // System.out.print("\nEnter end word : ");
        // String endInput = scanner.nextLine();
        // endInput = endInput.toLowerCase();

        Node startNode = new Node("TEST0");
        Node node1 = new Node("TEST1");
        Node node2 = new Node("TEST2");
        Node node3 = new Node("TEST3");
        Node node4 = new Node("TEST4");

        startNode.concatNode(node1);
        node1.concatNode(node2);
        node2.concatNode(node3);
        node3.concatNode(node4);

        startNode.displayNodeForward();
        node4.displayNodePrevious();
        // startTime = Instant.now();
        // PriorityQueue wordQueue = new PriorityQueue(100000);
        // Greedy greedyObject = new Greedy();
        
        // Map<String,Boolean> visitedWord = new HashMap<>(100000);
        // int count = 0;
        // String[] solution = greedyObject.algorithmGreedy(startInput,endInput,wordQueue,visitedWord,count);


        // Instant endTime = Instant.now();

        // Duration duration = Duration.between(startTime, endTime);
        // System.out.println("Program execution duration: " + duration.toMillis() + " milliseconds");

        // System.out.print("[");
        // for(int i = 0; i < solution.length; i++){
        //     System.out.print(solution[i]);
        //     if (i != solution.length - 1){
        //         System.out.print(",");
        //     }
        // }
        // System.out.print("]");
        scanner.close();
    }

}