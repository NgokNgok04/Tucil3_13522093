package src.backend;
import java.util.*;
// import java.util.Dictionary;
import java.time.*;
import src.backend.util.*;
import src.backend.util.PriorityQueue;
import src.backend.util.Node;
// import src.backend.util.Process;

// import java.io.IOException;
// import java.io.OutputStream;
// import java.net.InetSocketAddress;
// import com.sun.net.httpserver.HttpServer;
// import com.sun.net.httpserver.HttpHandler;
// import com.sun.net.httpserver.HttpExchange;
// import com.sun.net.httpserver.Headers;
// import java.io.InputStream;
// import java.io.InputStreamReader;

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
        System.out.println("1. GBDS : ");
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
        if(methodInput.equals("1")){
            List<String> wordListQueue = new ArrayList<>();
            solution = greedyObject.algorithmGreedy(startInput,endInput,wordListQueue,visitedWord, dictionary);
        } else if (methodInput.equals("2")){
            solution = ucsObject.algorithmUCS(startInput, endInput, wordQueue, visitedWord, dictionary);
        } else {
            System.out.println("Masuk ke A*");
            solution = aStarObject.algorithmAstar(startInput, endInput, wordQueue, visitedWord, dictionary);
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





    // private static Instant startTime;
    // public static void main(String[] args) throws IOException {
    //     HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
    //     server.createContext("/", new MyHandler());
    //     server.setExecutor(null); // creates a default executor
    //     server.start();
    //     System.out.println("Server started on port 8000");
    // }

    // static class MyHandler implements HttpHandler {
    //     @Override
    //     public void handle(HttpExchange exchange) throws IOException {
    //         if ("POST".equals(exchange.getRequestMethod())) {
    //             // Read the request body
    //             InputStream requestBody = exchange.getRequestBody();
    //             InputStreamReader requestBodyReader = new InputStreamReader(requestBody);
    //             StringBuilder requestBodyBuilder = new StringBuilder();
    //             int c;
    //             while ((c = requestBodyReader.read()) != -1) {
    //                 requestBodyBuilder.append((char) c);
    //             }
    //             requestBodyReader.close();

    //             String requestBodyString = requestBodyBuilder.toString();
    //             Map<String, String> requestData = parseJson(requestBodyString);

    //             String startWord = requestData.get("startWord");
    //             String endWord = requestData.get("endWord");
    //             String algorithmType = requestData.get("algorithmType");                

    //             Process processObject = new Process(startWord, endWord, algorithmType);
    //             String responseData = String.format("{\"timeExecution\": %d, \"nodeVisited\": %d, \"len\": %d, \"solution\": %s}", processObject.timeExecution, processObject.nodeVisited, processObject.len, Arrays.toString(processObject.solution));

    //             Headers responseHeaders = exchange.getResponseHeaders();
    //             responseHeaders.set("Content-Type", "application/json");
    //             responseHeaders.set("Access-Control-Allow-Origin", "*"); // Allow requests from all origins
    //             responseHeaders.set("Access-Control-Allow-Methods", "POST"); // Allow only POST requests
    //             responseHeaders.set("Access-Control-Allow-Headers", "Content-Type"); // Allow only specific headers

    //             exchange.sendResponseHeaders(200, responseData.getBytes().length);
    //             OutputStream os = exchange.getResponseBody();
    //             os.write(responseData.getBytes());
    //             os.close();
    //         } else {
    //             // Method not allowed
    //             exchange.sendResponseHeaders(405, -1);
    //         }
    //     }
    
    //     private Map<String, String> parseJson(String json) {
    //         Map<String, String> data = new HashMap<>();
    //         // Simple JSON parsing - You can replace this with your own parsing logic
    //         String[] parts = json.split(",");
    //         for (String part : parts) {
    //             String[] keyValue = part.split(":");
    //             if (keyValue.length == 2) {
    //                 String key = keyValue[0].trim().replaceAll("\"", "");
    //                 String value = keyValue[1].trim().replaceAll("\"", "");
    //                 data.put(key, value);
    //             }
    //         }
    //         return data;
    //     }
    // }

