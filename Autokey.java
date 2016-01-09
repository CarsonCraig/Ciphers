
import java.util.Scanner;

/**
 * @author carson
 */

public class Autokey {

    static Scanner in;
    static String keyword = "";
    static String lengthenedKeyword = "";
    static int[] numericKeyword;

    static String input = "";
    static int[] numericInput;

    static String output = "";

    static Boolean performEncrypt;

    static int[] numericOutput;

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
        numericInput = turnNumeric(input, input.length());

        System.out.println("Enter keyword");
        keyword = in.nextLine();
        keyword = formatString(keyword);

        if (performEncrypt) {
            lengthenedKeyword = generateAutokeyKeyword(keyword, input);
            numericKeyword = turnNumeric(lengthenedKeyword, input.length());
            numericOutput = encryptNumericStrings(numericInput, numericKeyword);
        } else {
            numericKeyword = turnNumeric(keyword, input.length());
            numericOutput = decryptNumericStrings(numericInput, numericKeyword, keyword.length());

        }

        output = turnAlpha(numericOutput);
        System.out.println(output + "");
    }

    public static String formatString(String originalString) {
        String formattedString = "";

        formattedString = (originalString.replaceAll("[^A-Za-z]", "")).toUpperCase();

        return formattedString;
    }

    public static String generateAutokeyKeyword(String keyword, String input) {
        String returnKeyword = "";

        returnKeyword = keyword + input;

        returnKeyword = returnKeyword.substring(0, input.length());

        return returnKeyword;
    }

    public static int[] turnNumeric(String originalString, int size) {
        int[] returnNumeric = new int[size];

        for (int i = 0; i < originalString.length(); i++) {

            returnNumeric[i] = originalString.charAt(i) - 65;
        }

        return returnNumeric;
    }

    public static int[] encryptNumericStrings(int[] numericInput, int[] numericKeyword) {

        int[] returnAddedNumeric = new int[numericInput.length];

        for (int i = 0; i < numericInput.length; i++) {
            returnAddedNumeric[i] = (numericInput[i] + numericKeyword[i]) % 26;
        }

        return returnAddedNumeric;
    }

    public static int[] decryptNumericStrings(int[] numericInput, int[] numericKeyword, int keyLength) {

        int[] output = new int[numericInput.length];

        for (int i = 0; i < numericInput.length; i++) {

            output[i] = (numericInput[i] + (26 - numericKeyword[i])) % 26;

            if (keyLength + i < numericInput.length) {
                numericKeyword[keyLength + i] = output[i];
            }
        }

        return output;
    }

    public static String turnAlpha(int[] addedNumeric) {
        String returnString = "";

        for (int i = 0; i < addedNumeric.length; i++) {

            returnString = returnString + (char) (addedNumeric[i] + 65);

        }

        return returnString;
    }
}