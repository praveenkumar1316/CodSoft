import java.util.*;
public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = 7;
        int roundsWon = 0;
        while (true) {
            int secretNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;
            System.out.println("\nI'm thinking of a number between " + lowerBound + " and " + upperBound + ". Can you guess it?");
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                try {
                    int guess = Integer.parseInt(scanner.nextLine());
                    attempts++;
                    if (guess < lowerBound || guess > upperBound) {
                        System.out.println("Please enter a number between " + lowerBound + " and " + upperBound + ".");
                    } else if (guess < secretNumber) {
                        System.out.println("Too low! Try again.");
                    } else if (guess > secretNumber) {
                        System.out.println("Too high! Try again.");
                    } else {
                        System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                        roundsWon++;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
            
            if (attempts == maxAttempts) {
                System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The number was " + secretNumber + ".");
            }
            
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.nextLine().trim().toLowerCase();
            if (!playAgain.equals("yes")) {
                System.out.println("Thanks for playing! You won " + roundsWon + " rounds.");
                break;
            }
        }
        scanner.close();
    }
}
