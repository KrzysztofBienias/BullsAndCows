import java.util.HashSet;
import java.util.Random;

public class BullsAndCowsGame {
    private static final int CODE_MAX_LENGTH = 36;
    private static final int CODE_MIN_LENGTH = 1;
    private final String secretCode;
    private boolean isGuessed;
    private int cows;
    private int bulls;
    private int turn;

    public BullsAndCowsGame(int codeLength, int possibleAmountOfChars) {
        if (codeLength < CODE_MIN_LENGTH || codeLength > CODE_MAX_LENGTH) {
            System.out.println("Error: code length has to be in the range of 1-36");
            System.exit(422);
        }

        if (codeLength > possibleAmountOfChars) {
            System.out.println("Error: cannot generate code when the code length is bigger than the number of unique symbols");
            System.exit(422);
        }

        if (possibleAmountOfChars > CODE_MAX_LENGTH) {
            System.out.println("Error: the max number of symbols in the code is 36 (0-9, a-z)");
            System.exit(422);
        }

        this.secretCode = generateSecretCode(codeLength, possibleAmountOfChars);
//
        System.out.println(this.secretCode);
        System.out.println(this.secretCode.length());

        this.isGuessed = false;
        this.cows = 0;
        this.bulls = 0;
        this.turn = 1;
    }

    public void play() {
        System.out.println("Okay, let's start a game!");

        while (!isGuessed) {
            System.out.println("Turn " + turn + ":");
            String guessCode = UserInputReader.readInputString();

            countBullsAndCows(guessCode);
            displayGradeAndCheckVictory();

            turn++;
            bulls = 0;
            cows = 0;
        }
    }

    private void countBullsAndCows(String guessCode) {
        for (int i = 0; i < secretCode.length(); i++) {
            char secretChar = secretCode.charAt(i);
            char guessChar = guessCode.charAt(i);

            if (secretChar == guessChar) {
                bulls++;
            } else if (secretCode.contains(String.valueOf(guessChar))) {
                cows++;
            }
        }
    }

    private void displayGradeAndCheckVictory() {
        String grade;

        if (bulls == 0 && cows == 0) {
            grade = "None";
        } else if (bulls == 0) {
            grade = String.format("%s cow(s)", cows);
        } else if (cows == 0) {
            grade = String.format("%s bull(s)", bulls);
        } else {
            grade = String.format("%s bull(s) and %s cow(s)", bulls, cows);
        }

        System.out.println("Grade: " + grade);

        if (bulls == secretCode.length()) {
            isGuessed = true;
            System.out.println("Congratulations! You guessed the secret code.");
        }
    }

    private String generateSecretCode(int length, int possibleAmountOfChars) {
        StringBuilder code = new StringBuilder();
        HashSet<Integer> usedCharacters = new HashSet<>();
        Random random = new Random();

        while (code.length() < length) {
            int randomChar = random.nextInt(length);

            if (!usedCharacters.contains(randomChar)) {
                usedCharacters.add(randomChar);

                // Number case
                if (randomChar < 10) {
                    code.append(randomChar);
                }

                // Character case
                if (randomChar >= 10 && possibleAmountOfChars > 0) {
                    char randomLetter = (char) ('a' + randomChar - 10);
                    code.append(randomLetter);

                    possibleAmountOfChars--;
                }
            }
        }

        return code.toString();
    }
}
