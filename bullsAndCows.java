import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static String randomGenerator(int length, int symbols) {
        List<Character> list = new ArrayList<>(List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
                'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't','u',
                'v',  'w',  'x', 'y', 'z'));
        List<Character> randomList = list.subList(0, symbols);
        Collections.shuffle(randomList);
        StringBuilder result = new StringBuilder();
        for (var ch : randomList.subList(0, length)) {
            result.append(ch);
        }
        return result.toString();
    }

    public static boolean calculateScore(String guess, String code, int length) {
        int bulls = 0;
        int cows = 0;
        int i = 0;
        for (char ch : guess.toCharArray()) {
            if (code.indexOf(ch) != -1) {
                if (code.charAt(i) == ch) {
                    bulls++;
                } else
                    cows++;
                }
            }
            i++;
        }
        if (bulls > 0 && cows > 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s)\n", bulls, cows);
        } else if (bulls > 0) {
            System.out.printf("Grade: %d bull(s)\n", bulls);
        } else if (cows > 0) {
            System.out.printf("Grade: %d cow(s)\n", cows);
        } else {
            System.out.print("Grade: None\n");
        }
        return bulls == length;
    }

    public static void main(String[] args) {
        System.out.println("Please, enter the secret code's length:");
        int length;
        try {
            length = Integer.parseInt(sc.nextLine());
            if (length <= 0 || length > 36) {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. The length must be a positive integer.");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid secret code length. Please enter a valid length.");
            return;
        }

        System.out.println("Input the number of possible symbols in the code:");
        int symbols;
        try {
            symbols = Integer.parseInt(sc.nextLine());
            if (symbols < length || symbols > 36) {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. The number of possible symbols must be an integer.");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid number of possible symbols. The maximum is 36 (0-9, a-z).");
            return;
        }

        String code = randomGenerator(length, symbols);
        System.out.printf("The secret is prepared: %s ", "*".repeat(length));
        if (symbols <= 10) {
            System.out.printf("(0-%d).\n", symbols - 1);
        } else {
            System.out.printf("(0-9, a-%s).\n", "abcdefghijklmnopqrstuvwxyz".charAt(symbols - 11));
        }
        System.out.println("Okay, let's start a game!");

        int i = 1;
        while (true) {
            System.out.println("Turn " + i + ":");
            String guess = sc.nextLine();
            if (guess.length() != length) {
                System.out.println("Error: Invalid guess length. The length should be " + length + ".");
                return;
            }
            if (!guess.matches("[0-9a-z]+")) {
                System.out.println("Error: Invalid guess. The guess should only contain alphanumeric characters.");
                return;
            }
            if (calculateScore(guess, code, length)) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
            i++;
        }
    }
}