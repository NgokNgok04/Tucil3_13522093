package src.backend;
import java.util.Arrays;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import src.backend.util.*;


public class Main {
    private static Instant startTime;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter start word : ");
        String startInput = scanner.nextLine();
        startInput = startInput.toLowerCase();
        
        System.out.print("\nEnter end word : ");
        String endInput = scanner.nextLine();
        endInput = endInput.toLowerCase();

        System.out.println("Select Method : ");
        System.out.println("1. Greedy : ");
        System.out.println("2. UCS : ");
        System.out.println("Choose 1 or 2 : ");
        String methodInput = scanner.nextLine();


        startTime = Instant.now();
        PriorityQueue wordQueue = new PriorityQueue(100000);
        Greedy greedyObject = new Greedy();
        UCS ucsObject = new UCS();
        Map<String,Boolean> visitedWord = new HashMap<>(100000);
        List<String> solution;

        if(methodInput.equals("1")){
            solution = greedyObject.algorithmGreedy(startInput,endInput,wordQueue,visitedWord);
        } else {
            solution = ucsObject.algorithmUCS(startInput, endInput, wordQueue, visitedWord);
        }
        Instant endTime = Instant.now();

        Duration duration = Duration.between(startTime, endTime);
        System.out.println("\n\nProgram execution duration: " + duration.toMillis() + " milliseconds");
        System.out.println("Solution size " + solution.size());
        System.out.print("[");
        for(int i = 0; i < solution.size(); i++){
            System.out.print(solution.get(i));
            if (i != solution.size() - 1){
                System.out.print(",");
            }
        }
        System.out.print("]");
        scanner.close();
    }

}