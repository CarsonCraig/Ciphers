
import java.util.Scanner;

/**
 * @author carson
 */

public class Caesar {

    public static char[] characterArray = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args) {

        int key;
        String input = "";
        String output = "";
        Boolean performEncrypt;
        Scanner in;

        while (true) {

            in = new Scanner(System.in);

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
        input = input.replaceAll("[^A-Za-z]", "");
        input = input.toUpperCase();

        System.out.println("Enter a key from 1 - 25");
        key = in.nextInt();

        for (int i = 0; i < input.length(); i++) {

            char x = input.charAt(i);
            int index = find(characterArray, x);

            index = (index + (performEncrypt ? key : 26-key)) % 26;

            output = output + characterArray[index];
        }


            System.out.println("\nKey: " + key + (performEncrypt ? "\nUnencrypted:   " : "\nEncrypted:   ") + input + (performEncrypt ? "\nEncrypted   " : "\nUnencrypted   ") + output);

}

    public static int find(char[] array, char value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == (value)) {
                return i;
            }
        }
        return 0;
    }
}