import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Number Guessing Game!");

        boolean playAgain = true;
        int easyBest = Integer.MAX_VALUE;
        int mediumBest = Integer.MAX_VALUE;
        int hardBest = Integer.MAX_VALUE;

        while (playAgain) {
            System.out.println("\nI'm thinking of a number between 1 and 100.");
            System.out.println("\nPlease select the difficulty level:");
            System.out.println("1. Easy (10 chances)");
            System.out.println("2. Medium (5 chances)");
            System.out.println("3. Hard (3 chances)");

            int choice = 0;
            while (choice < 1 || choice > 3) {
                System.out.print("Enter your choice: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice < 1 || choice > 3) {
                        System.out.println("Invalid choice. Please enter 1, 2 or 3.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
            }

            int maxAttempts;
            if (choice == 1) {
                maxAttempts = 10;
                System.out.println("You selected Easy. You have 10 chances.");
            } else if (choice == 2) {
                maxAttempts = 5;
                System.out.println("You selected Medium. You have 5 chances.");
            } else {
                maxAttempts = 3;
                System.out.println("You selected Hard. You have 3 chances.");
            }

            long startTime = System.currentTimeMillis();
            int secretNumber = random.nextInt(100) + 1;
            int attempts = 0;
            boolean won = false;

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");

                if (scanner.hasNextInt()) {
                    int guess = scanner.nextInt();
                    attempts++;

                    if (guess < 1 || guess > 100) {
                        System.out.println("Please enter a number between 1 and 100.");
                        attempts--;
                    } else if (guess == secretNumber) {
                        System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                        won = true;

                        if (choice == 1 && attempts < easyBest) {
                            easyBest = attempts;
                            System.out.println("New best score for Easy: " + easyBest + " attempts!");
                        } else if (choice == 2 && attempts < mediumBest) {
                            mediumBest = attempts;
                            System.out.println("New best score for Medium: " + mediumBest + " attempts!");
                        } else if (choice == 3 && attempts < hardBest) {
                            hardBest = attempts;
                            System.out.println("New best score for Hard: " + hardBest + " attempts!");
                        }

                        break;
                    } else if (guess < secretNumber) {
                        System.out.println("Incorrect! The number is greater than " + guess + ". Attempts left: " + (maxAttempts - attempts));
                    } else {
                        System.out.println("Incorrect! The number is less than " + guess + ". Attempts left: " + (maxAttempts - attempts));
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
            }

            if (!won) {
                System.out.println("Game over! The correct number was " + secretNumber + ".");
            }

            long endTime = System.currentTimeMillis();
            long timeTaken = (endTime - startTime) / 1000;
            System.out.println("You took " + timeTaken + " seconds to finish this round.");

            System.out.println("\n--- High Scores (fewest attempts) ---");
            System.out.println("Easy: " + (easyBest == Integer.MAX_VALUE ? "No score yet" : easyBest));
            System.out.println("Medium: " + (mediumBest == Integer.MAX_VALUE ? "No score yet" : mediumBest));
            System.out.println("Hard: " + (hardBest == Integer.MAX_VALUE ? "No score yet" : hardBest));

            String answer = "";
            while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")
                && !answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
                System.out.print("\nDo you want to play again? (yes/no): ");
                answer = scanner.next();
                if (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")
                    && !answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
                    System.out.println("Invalid choice. Please enter yes or no.");
                }
            }

            if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
                playAgain = false;
                System.out.println("Thanks for playing. Goodbye!");
            }
        }

        scanner.close();
    }
}