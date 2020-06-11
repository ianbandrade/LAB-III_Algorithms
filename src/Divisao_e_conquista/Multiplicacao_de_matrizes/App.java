package Divisao_e_conquista.Multiplicacao_de_matrizes;

import java.util.Arrays;

/**
 * @author Ian Bittencourt Andrade
 */

public class App {
    private static final int N = 400;

    public static void main(String[] args) {
        long begin;

        Matrix matrixA = new Matrix(N);
        Matrix matrixB = new Matrix(N);

        begin = System.currentTimeMillis();
        Matrix matrixC = Matrix.strassen(matrixA, matrixB);
        System.out.println("Time: " + (System.currentTimeMillis() - begin) + " milliseconds");
    }
}
