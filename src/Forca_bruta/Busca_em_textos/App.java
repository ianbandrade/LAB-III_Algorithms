
package Forca_bruta.Busca_em_textos;

import java.util.ArrayList;

public class App {
    /**
     * @author Ian Bittencourt Andrade
     */

    public final static String PAT = "but";
    public final static String TEXT = "We may encounter many defeats but we must not be defeated.";

    public static ArrayList<Integer> searchBF(String text, String pat) {
        ArrayList<Integer> occurrences = new ArrayList<>();
        int n = text.length();
        int m = pat.length();
        int begin = 0;

        while (begin < (n - m + 1)) {
            int patPos = 0;

            while ((patPos < m) && (pat.charAt(patPos) == text.charAt(begin + patPos))) {
                patPos++;
            }

            if ( patPos == m ) {
                occurrences.add(begin);
            }

            begin++;
        }

        return occurrences;
    }

    public static ArrayList<Integer> searchBMHS(String text, String pat) {
        ArrayList<Integer> occurrences = new ArrayList<>();
        final int ALFHABET_SIZE = 256;
        int n = text.length();
        int m = pat.length();
        int positionAtChar = 0;
        int begin = 0;

        int[] jumps = new int[ ALFHABET_SIZE ];

        for (int i = 0; i < ALFHABET_SIZE; i++) {
            jumps[ i ] = m + 1;
        }

        for (int i = 0; i < m; i++) {
            int pos = (int) pat.charAt(i);
            jumps[ pos ] = m - i;
        }

        while (begin < (n - m + 1)) {
            int patPos = 0;

            while ((patPos < m) && (pat.charAt(patPos) == text.charAt(begin + patPos))) {
                patPos++;
            }

            if ( patPos == m ) {
                occurrences.add(begin);
            }

            positionAtChar = begin + m;

            if ( (positionAtChar) < text.length() ) {
                int letter = (int) text.charAt(positionAtChar);
                begin += jumps[ letter ];
            } else return occurrences;

        }

        return occurrences;
    }

    public static void main(String[] args) {
        System.out.println("Pat: " + PAT);
        System.out.println("Text: " + TEXT);

        System.out.println("\nPositions -> " + searchBF(TEXT, PAT) + ": Brute Force");
        System.out.println("Positions -> " + searchBMHS(TEXT, PAT) + ": Horspool");
    }
}

