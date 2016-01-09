
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author carson
 */

public class Trifid {

    static char[][][] keyCube = new char[3][3][3];

    static Scanner in;
    static Random r;
    static int period;
    static String input;
    static String output = "";
    static int[][] numericInput;

    public static void main(String[] args) {
        in = new Scanner(System.in);
        r = new Random();
        generateKeyCube();
        outputKeyCube();

        System.out.println("Enter a string to be encrypted");
        input = in.nextLine();
        input = formatString(input);
        
        System.out.println("Enter a period");
        period = Integer.parseInt(in.nextLine());

        numericInput = new int[3][input.length()];
        convertNumeric();
        convertAlpha();
        
        System.out.println("Plaintext:  " + input);
        System.out.println("Ciphertext: " + output);
    }

    public static void generateKeyCube() {
        String selectedCharacters = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {

                    while (true) {
                        String nextRandom = (char) (Math.floor(r.nextDouble() * 27) + 65) + "";
                        if (nextRandom.equals("[")) {
                            nextRandom = ".";
                        }

                        if (!selectedCharacters.contains(nextRandom)) {
                            keyCube[i][j][k] = nextRandom.charAt(0);
                            selectedCharacters = selectedCharacters + nextRandom;
                            break;
                        } else {
                            continue;
                        }
                    }

                }
            }
        }
    }

    public static void outputKeyCube() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {

                    System.out.print(keyCube[i][j][k] + " ");
                }
                System.out.print("  ");
            }
            System.out.println("");
        }
    }

    public static void convertNumeric() {

        for (int a = 0; a < input.length(); a++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {

                        if (input.charAt(a) == keyCube[i][j][k]) {
                            numericInput[0][a] = i;
                            numericInput[1][a] = j;
                            numericInput[2][a] = k;
                        }

                    }
                }
            }
        }
    }

    public static void convertAlpha() {

        ArrayList coordinates = new ArrayList();

        for (int i = 0; i < period; i++) {

        }

        int startIndex = 0;
        int square = 0;
        
        while(output.length()<input.length()){

            for (int a = startIndex; a < input.length() || a<period; a++) {
                coordinates.add(numericInput[0][a]);

                if (coordinates.size() == 3) {
                    int x = (int) coordinates.get(0);
                    int y = (int) coordinates.get(1);
                    int z = (int) coordinates.get(2);
                    output = output + keyCube[x][y][z];
                    coordinates = new ArrayList();
                }
            }
            for (int b = startIndex; b < input.length() || b<period; b++) {
                coordinates.add(numericInput[1][b]);

                if (coordinates.size() == 3) {
                    int x = (int) coordinates.get(0);
                    int y = (int) coordinates.get(1);
                    int z = (int) coordinates.get(2);
                    output = output + keyCube[x][y][z];
                    coordinates = new ArrayList();
                }
            }
            for (int c = startIndex; c < input.length() || c<period; c++) {
                coordinates.add(numericInput[2][c]);

                if (coordinates.size() == 3) {
                    int x = (int) coordinates.get(0);
                    int y = (int) coordinates.get(1);
                    int z = (int) coordinates.get(2);
                    output = output + keyCube[x][y][z];
                    coordinates = new ArrayList();
                }
            }
            startIndex = startIndex + period;
        }
    }

    public static String formatString(String originalString) {
        String formattedString = "";

        formattedString = (originalString.replaceAll("[^A-Za-z]", "")).toUpperCase();

        return formattedString;
    }
}