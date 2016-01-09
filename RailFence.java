import java.util.Scanner;

/**
 * @author Carson
 */

public class RailFence {

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

        System.out.println("Enter a numeric key smaller than the length of the " + (performEncrypt ? "plaintext." : "ciphertext."));
        keyword = in.nextLine();
        int numericKeyword = Integer.parseInt(formatKeywordString(keyword));

        output = input;
        char[] outputArray = output.toCharArray();

        int lengthCounter = 0;
        for (int k = 0; k < numericKeyword; k++) {
            int j = 0;
            boolean upwards = false;
            for (int i = 0; i < input.length(); i++) {

                if (k == j) {
                    outputArray[lengthCounter] = input.charAt(i);
                    lengthCounter++;
                }

                if (j == 0 || j == numericKeyword - 1) {
                    upwards = !upwards;
                }
                if (upwards) {
                    j++;

                } else {
                    j--;

                }
            }
        }

        System.out.println("Plaintext:  " + input + "");
        System.out.print("Ciphertext:  ");
        
        for (int i = 0; i < outputArray.length; i++) {
            System.out.print(outputArray[i]);
        }



    }

    public static String formatInputString(String originalString) {
        String formattedString = "";

        formattedString = (originalString.replaceAll("[^A-Za-z]", "")).toUpperCase();

        return formattedString;
    }

    public static String formatKeywordString(String originalString) {
        String formattedString = "";

        formattedString = originalString.replaceAll("[^0-9]", "");

        return formattedString;
    }
}