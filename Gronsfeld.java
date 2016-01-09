
import java.util.Scanner;

/**
 * @author carson
 */

public class Gronsfeld {

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
        input = formatInputString(input);
        numericInput = turnInputNumeric(input);

        System.out.println("Enter a numeric key");
        keyword = in.nextLine();
        keyword = formatKeywordString(keyword);

        lengthenedKeyword = generateGronsfeldKeyword(keyword, input);

        numericKeyword = turnKeywordNumeric(lengthenedKeyword);

        addedNumeric = addNumericStrings(numericInput, numericKeyword);

        output = turnAlpha(addedNumeric);

        System.out.println(output + "");
    }

    public static String formatInputString(String originalString) {
        String formattedString = "";

        formattedString = (originalString.replaceAll("[^A-Za-z]", "")).toUpperCase();

        return formattedString;
    }

    public static String formatKeywordString(String originalString) {
        String formattedString = "";

        formattedString = (originalString.replaceAll("[^0-9]", ""));

        return formattedString;
    }

    public static String generateGronsfeldKeyword(String keyword, String input) {
        String returnKeyword = "";

        while (returnKeyword.length() < input.length()) {
            returnKeyword = returnKeyword + keyword;
        }

        returnKeyword = returnKeyword.substring(0, input.length());

        return returnKeyword;
    }

    public static int[] turnInputNumeric(String originalString) {
        int[] returnNumeric = new int[originalString.length()];

        for (int i = 0; i < originalString.length(); i++) {

            returnNumeric[i] = originalString.charAt(i) - 65;
        }

        return returnNumeric;
    }

    public static int[] turnKeywordNumeric(String originalString) {
        int[] returnNumeric = new int[originalString.length()];

        for (int i = 0; i < originalString.length(); i++) {

            returnNumeric[i] = ((int) originalString.charAt(i)) - 48;
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