import edu.stanford.nlp.simple.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Chatbot {

    // Sample responses based on intents
    private static final Map<String, String> responses = new HashMap<>();

    static {
        responses.put("greeting", "Hello! How can I assist you today?");
        responses.put("farewell", "Goodbye! Have a great day!");
        responses.put("weather", "I can't check the weather at the moment, but it seems like a good day!");
        responses.put("unknown", "Sorry, I didn't understand that. Could you please rephrase?");
    }

    // Method to determine intent
    private static String getIntent(String input) {
        // Basic keyword matching for intent classification
        input = input.toLowerCase();

        if (input.contains("hello") || input.contains("hi")) {
            return "greeting";
        } else if (input.contains("bye") || input.contains("goodbye")) {
            return "farewell";
        } else if (input.contains("weather") || input.contains("forecast")) {
            return "weather";
        } else {
            return "unknown";
        }
    }

    // Method to process user input using Stanford NLP
    private static String processInput(String input) {
        // Create a document object from the input
        Document doc = new Document(input);

        // Tokenize and parse the input
        for (Sentence sentence : doc.sentences()) {
            System.out.println("Parsed sentence: " + sentence.parse());
        }

        // Determine the intent and respond
        String intent = getIntent(input);
        return responses.getOrDefault(intent, "I'm not sure how to respond to that.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Chatbot! Type 'exit' to end the conversation.");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            // Exit condition
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Chatbot: " + responses.get("farewell"));
                break;
            }

            // Process input and respond
            String response = processInput(userInput);
            System.out.println("Chatbot: " + response);
        }

        scanner.close();
    }
}
