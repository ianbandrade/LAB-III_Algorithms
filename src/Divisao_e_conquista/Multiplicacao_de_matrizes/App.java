package Divisao_e_conquista.Multiplicacao_de_matrizes;

/**
 * @author Ian Bittencourt Andrade
 */

public class App {
  private static final int N = 400;

  public static void main(String[] args) {

    Matrix matrixA = new Matrix(N);
    Matrix matrixB = new Matrix(N);

    Matrix.strassen(matrixA, matrixB);
  }
}
