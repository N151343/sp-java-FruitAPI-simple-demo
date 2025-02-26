import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // get user input (integers)
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter a fruit:");
        String fruit = scan.nextLine();

        // display output
        System.out.println("All about " + fruit + ":");
        String fruitData = getFruitInfo(fruit);
        System.out.println(fruitData);

    } // main() method closing

    public static String getFruitInfo(String fruit){
        // Constructing the url for the API request
        String url = "https://www.fruityvice.com/api/fruit/" + fruit;

        // Create an HTTP client object, so we can send a request
        HttpClient client = HttpClient.newHttpClient();

        // Build an HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            // Send the request to the API, and get a response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // If there's an issue, check that response.statusCode() returns a 200

            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}