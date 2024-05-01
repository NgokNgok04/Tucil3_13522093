package src.backend;
import java.util.Arrays;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

import src.backend.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter start word : ");
        String startInput = scanner.nextLine();
        startInput = startInput.toLowerCase();
        
        System.out.print("\nEnter end word : ");
        String endInput = scanner.nextLine();
        endInput = endInput.toLowerCase();

        PriorityQueue wordQueue = new PriorityQueue();
        UCS ucsObject = new UCS();
        String[] solution = ucsObject.algorithmUCS(startInput,endInput,wordQueue);

        System.out.print("[");
        for(int i = 0; i < solution.length; i++){
            System.out.print(solution[i]);
            if (i != solution.length - 1){
                System.out.print(",");
            }
        }
        System.out.print("]");
        scanner.close();
    }

}