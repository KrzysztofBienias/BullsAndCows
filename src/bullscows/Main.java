import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please, enter the secret code's length:");
        int codeLength = UserInputReader.readIntegerInput();

        System.out.println("Input the number of possible symbols in the code:");
        int possibleAmountOfChars = UserInputReader.readIntegerInput();

        BullsAndCowsGame game = new BullsAndCowsGame(codeLength, possibleAmountOfChars);
        game.play();
    }
}

class UserInputReader {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readIntegerInput() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter an integer.");
            System.exit(422);
        }

        return -1;
    }

    public static String readInputString() {
        return scanner.next();
    }
}