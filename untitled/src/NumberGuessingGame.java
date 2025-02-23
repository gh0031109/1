import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    //高分记录
    private static int[] highScores = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        //游戏欢迎信息
        printWelcome();

        do {
            //获取游戏难度和机会
            String difficulty = getDifficulty(scanner);
            int chances = getChances(difficulty);

            //开始游戏
            playGame(difficulty, chances, scanner);

            //询问用户是否继续
            System.out.print("\nDo you want to play  again? (y/n): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("y");
        } while (playAgain);

        //游戏结束，显示高分
        printHighScores();
    }

    //打印欢迎信息
    private static void printWelcome() {
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("You will need to guess the correct number within a limited number of attempts.");
        System.out.println("Let's get started!");
    }

    //获取难度
    private static String getDifficulty(Scanner scanner) {
        System.out.println("\nPlease select the difficulty level:");
        System.out.println("1. Easy (10 chances)");
        System.out.println("2. Medium (5 chances)");
        System.out.println("3. Hard (3 chances)");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                return "easy";

            case 2:
                return "medium";

            case 3:
                return "hard";

            default:
                return "medium";
        }
    }

    //根据难度返回相应的猜测机会
    private static int getChances(String difficulty) {
        switch (difficulty) {
            case "easy":
                return 10;

            case "medium":
                return 5;

            case "hard":
                return 3;

            default:
                return 5;
        }
    }

    private static void playGame(String difficulty, int chances, Scanner scanner) {

        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1;
        int attempts = 0;
        long startTime = System.currentTimeMillis();

        System.out.println("\nGreat! You have selected the " + difficulty + " difficulty level.");
        System.out.println("You have " + chances + " chances to guess the correct number.");

        while (attempts < chances) {
            System.out.print("\nEnter your guess: ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess == numberToGuess) {
                long endTime = System.currentTimeMillis();
                long timeTaken = endTime - startTime;

                System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                System.out.printf("It took you %.2f seconds.\n", timeTaken / 1000.0);

                //更新高分记录
                updateHighScores(difficulty, attempts);
                return;
            } else if (guess < numberToGuess) {
                System.out.println("Incorrect! The number is greater than " + guess + ".");
            } else {
                System.out.println("Incorrect! The number is less than " + guess + ".");
            }

        }
        System.out.println("\nSorry! You've used all your chances. The correct number was " + numberToGuess + ".");

    }

    // 获取难度的索引
    private static int getDifficultyIndex(String difficulty) {
        switch (difficulty) {
            case "easy":
                return 0;
            case "medium":
                return 1;
            case "hard":
                return 2;
            default:
                return 1;
        }
    }

    private static void printHighScores() {
        System.out.println("\nThanks for playing! Here's the high scores:");
        String[] difficulties = {"Easy", "Medium", "Hard"};
        for (int i = 0; i < 3; i++) {
            if (highScores[i] == Integer.MAX_VALUE) {
                System.out.println(difficulties[i] + ": No score yet.");
            } else {
                System.out.println(difficulties[i] + ": " + highScores[i] + " attempts");
            }
        }
    }

    private static void updateHighScores(String difficulty, int attempts) {
        int index = getDifficultyIndex(difficulty);

        if (attempts < highScores[index]) {
            highScores[index] = attempts;
            System.out.println("New high score for " + difficulty + " difficulty: " + attempts + " attempts.");
        }
    }
}

