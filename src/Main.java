
import java.util.*;
// import java.util.Dictionary;
import java.time.*;
import util.*;
import util.PriorityQueue;
import util.Node;

public class Main {
    private static Instant startTime;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter start word : ");
        String startInput = scanner.nextLine();
        startInput = startInput.toLowerCase();

        System.out.print("\nEnter end word : ");
        String endInput = scanner.nextLine();
        endInput = endInput.toLowerCase();

        System.out.println("Select Method : ");
        System.out.println("1. GBFS : ");
        System.out.println("2. UCS : ");
        System.out.println("3. A* : ");
        System.out.println("Choose 1,2,3 : ");
        String methodInput = scanner.nextLine();


        startTime = Instant.now();

        PriorityQueue wordQueue = new PriorityQueue(100000);
        Greedy greedyObject = new Greedy();
        UCS ucsObject = new UCS();
        Astar aStarObject = new Astar();
        Map<String,Boolean> visitedWord = new HashMap<>(100000);
        
        List<String> solution;
        MyDictionary dictionary = new MyDictionary();
        int len = 0;
        int nodeVisited = 0;
        if (startInput.length() == endInput.length() && dictionary.isAValidWord(startInput) && dictionary.isAValidWord(endInput)){

            if(methodInput.equals("1")){
                List<String> wordListQueue = new ArrayList<>();
                solution = greedyObject.algorithmGreedy(startInput,endInput,wordListQueue,visitedWord, dictionary);
                len = solution.size() - 2;
                nodeVisited = solution.size() - 1;
            } else if (methodInput.equals("2")){
                solution = ucsObject.algorithmUCS(startInput, endInput, wordQueue, visitedWord, dictionary,0);
                len = solution.size() - 2;
                nodeVisited = Integer.parseInt(solution.get(solution.size() - 1));
                
            } else {
                solution = aStarObject.algorithmAstar(startInput, endInput, wordQueue, visitedWord, dictionary,0);
                len = solution.size() - 2;
                nodeVisited = Integer.parseInt(solution.get(solution.size() - 1));
            }
            Instant endTime = Instant.now();
            
            Duration duration = Duration.between(startTime, endTime);
            System.out.println("\n\nProgram execution duration: " + duration.toMillis() + " milliseconds");
            System.out.println("Node Visited: " + nodeVisited);
            if (len > 0){
                System.out.println("Solution length : " + len);
                System.out.print("[");
                for(int i = 0; i < solution.size() - 1; i++){
                    System.out.print(solution.get(i));
                    if (i != solution.size() - 2){
                        System.out.print(",");
                    }
                }
                System.out.print("]");
            }
        } else {
            System.out.println("Masukan tidak Valid");
        }
        scanner.close();
    }
}