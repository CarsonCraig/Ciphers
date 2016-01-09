
import java.util.Scanner;

/**
 * @author carson
 */

public class Vigenere {

    static Scanner in;
    static String keyword = "";
    static String lengthenedKeyword = "";
    static int[] numericKeyword;

    static String input = "";
    static int[] numericInput;

    static String output = "";

    static Boolean performEncrypt;

    static int[] addedNumeric;

    public static void main(String[] args) {
        in = new Scanner(System.in);

        while (true) {
            System.out.println("Encrypt[E] or Decrypt[D]");
            String entered = in.nextLine();

            if ("E".equals(entered)) {
                performEncrypt = true;
                System.out.println("Enter a string to be encrypted");
                break;
            } else if ("D".equals(entered)) {
                performEncrypt = false;
                System.out.println("Enter a string to be decrypted");
                break;
            } else {
                System.out.println("Invalid Input");
            }
        }

        input = in.nextLine();
        input = formatString(input);
        numericInput = turnNumeric(input);

        System.out.println("Enter keyword");
        keyword = in.nextLine();
        keyword = formatString(keyword);

        lengthenedKeyword = generateVigenereKeyword(keyword, input);


        numericKeyword = turnNumeric(lengthenedKeyword);

        addedNumeric = addNumericStrings(numericInput, numericKeyword);
        output = turnAlpha(addedNumeric);
        System.out.println(output + "");
    }

    public static String formatString(String originalString) {
        String formattedString = "";

        formattedString = (originalString.replaceAll("[^A-Za-z]", "")).toUpperCase();

        return formattedString;
    }

    public static String generateVigenereKeyword(String keyword, String input) {
        String returnKeyword = "";

        while (returnKeyword.length() < input.length()) {
            returnKeyword = returnKeyword + keyword;
        }

        returnKeyword = returnKeyword.substring(0, input.length());

        return returnKeyword;
    }


    public static int[] turnNumeric(String originalString) {
        int[] returnNumeric = new int[originalString.length()];

        for (int i = 0; i < originalString.length(); i++) {

            returnNumeric[i] = ((int) originalString.charAt(i)) - 65;
        }

        return returnNumeric;
    }

    public static int[] addNumericStrings(int[] numericInput, int[] numericKeyword) {

        int[] returnAddedNumeric = new int[numericInput.length];

        for (int i = 0; i < numericInput.length; i++) {
            if (performEncrypt) {
                returnAddedNumeric[i] = (numericInput[i] + numericKeyword[i]) % 26;
            } else {
                returnAddedNumeric[i] = (numericInput[i] + (26 - numericKeyword[i])) % 26;
            }
        }

        return returnAddedNumeric;
    }

    public static String turnAlpha(int[] addedNumeric) {
        String returnString = "";

        for (int i = 0; i < addedNumeric.length; i++) {

            returnString = returnString + (char) (addedNumeric[i] + 65);

        }

        return returnString;
    }
}