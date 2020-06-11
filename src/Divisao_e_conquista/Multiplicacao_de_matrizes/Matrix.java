package Divisao_e_conquista.Multiplicacao_de_matrizes;

/**
 * @author Ian Bittencourt Andrade
 */

public class Matrix {
  static final int LEAF_SIZE = 1;

  private int[][] elements;

  private int size;

  /**
   * @param size Size of the matrix.
   * @brief Creates a matrix.
   */
  public Matrix(int size) throws IllegalArgumentException {
    if ( size < 1 ) {
      throw new IllegalArgumentException("invalid matrix size");
    }

    this.size = size;
    this.elements = new int[ size ][ size ];
  }

  /**
   * @param matrixA First operand.
   * @param matrixB Second operand.
   * @return A + B.
   * @brief Adds two matrices
   */
  public static Matrix add(Matrix matrixA, Matrix matrixB) {
    int n = matrixA.size;

    Matrix matrixC = new Matrix(n);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        matrixC.elements[ i ][ j ] = matrixA.elements[ i ][ j ] + matrixB.elements[ i ][ j ];
      }
    }

    return (matrixC);
  }

  /**
   * @param matrixA First operand.
   * @param matrixB Second operand.
   * @return A - B.
   * @brief Subtracts two matrices
   */
  public static Matrix subtract(Matrix matrixA, Matrix matrixB) {
    int n = matrixA.size;

    Matrix matrixC = new Matrix(n);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        matrixC.elements[ i ][ j ] = matrixA.elements[ i ][ j ] - matrixB.elements[ i ][ j ];
      }
    }

    return matrixC;
  }

  /**
   * @param matrixA First operand.
   * @param matrixB Second operand.
   * @return A*B.
   * @brief Multiplies two matrices using Naive Algorithm.
   */
  public static Matrix naive(Matrix matrixA, Matrix matrixB) {
    int n = matrixA.size;

    Matrix matrixC = new Matrix(n);

    for (int i = 0; i < n; i++) {
      for (int k = 0; k < n; k++) {
        for (int j = 0; j < n; j++) {
          matrixC.elements[ i ][ j ] += matrixA.elements[ i ][ k ] * matrixB.elements[ k ][ j ];
        }
      }
    }
    return (matrixC);
  }

  /**
   * @param matrixA First operand.
   * @param matrixB Second operand.
   * @return A*B.
   * @brief Multiplies two matrices using Strassen's Algorithm.
   */
  public static Matrix strassen(Matrix matrixA, Matrix matrixB) {
    int size = matrixA.size;

    if ( size <= LEAF_SIZE ) {
      return naive(matrixA, matrixB);
    } else {

      int newSize = size / 2;

      Matrix a11 = new Matrix(newSize);
      Matrix a12 = new Matrix(newSize);
      Matrix a21 = new Matrix(newSize);
      Matrix a22 = new Matrix(newSize);

      Matrix b11 = new Matrix(newSize);
      Matrix b12 = new Matrix(newSize);
      Matrix b21 = new Matrix(newSize);
      Matrix b22 = new Matrix(newSize);

      Matrix aResult = new Matrix(newSize);
      Matrix bResult = new Matrix(newSize);

      for (int i = 0; i < newSize; i++) {
        for (int j = 0; j < newSize; j++) {
          matrixElementSplitter(matrixA, newSize, a11, a12, a21, a22, i, j);
          matrixElementSplitter(matrixB, newSize, b11, b12, b21, b22, i, j);
        }
      }

      // p1 = (a11 + a22)*(b11 + b22)
      aResult = add(a11, a22);
      bResult = add(b11, b22);
      Matrix p1 = strassen(aResult, bResult);

      // p2 = (a21 + a22)*(b11)
      aResult = add(a21, a22);
      Matrix p2 = strassen(aResult, b11);

      // p3 = (a11)*(b12 - b22)
      bResult = subtract(b12, b22);
      Matrix p3 = strassen(a11, bResult);

      // p4 = (a22)*(b21 - b11)
      bResult = subtract(b21, b11);
      Matrix p4 = strassen(a22, bResult);

      // p5 = (a11 + a12)*(b22)
      aResult = add(a11, a12);
      Matrix p5 = strassen(aResult, b22);

      // p6 = (a21 - a11)*(b11 + b12)
      aResult = subtract(a21, a11);
      bResult = add(b11, b12);
      Matrix p6 = strassen(aResult, bResult);

      // p7 = (a12-a22)*(b21+b22)
      aResult = subtract(a12, a22);
      bResult = add(b21, b22);
      Matrix p7 = strassen(aResult, bResult);

      // c12 = p3 + p5
      Matrix c12 = add(p3, p5);

      // c21 = p2 + p4
      Matrix c21 = add(p2, p4);

      // c11 = p1 + p4 - p5 + p7
      aResult = add(p1, p4);
      bResult = add(aResult, p7);
      Matrix c11 = subtract(bResult, p5);

      // c22 = p1 + p3 - p2 + p6
      aResult = add(p1, p3);
      bResult = add(aResult, p6);
      Matrix c22 = subtract(bResult, p2);

      Matrix matrixC = new Matrix(size);
      for (int i = 0; i < newSize; i++) {
        for (int j = 0; j < newSize; j++) {
          matrixC.elements[ i ][ j ] = c11.elements[ i ][ j ];
          matrixC.elements[ i ][ j + newSize ] = c12.elements[ i ][ j ];
          matrixC.elements[ i + newSize ][ j ] = c21.elements[ i ][ j ];
          matrixC.elements[ i + newSize ][ j + newSize ] = c22.elements[ i ][ j ];
        }
      }

      return (matrixC);
    }
  }

  /**
   * @param matrix  Matrix.
   * @param newSize Size of matrix.
   * @param m11     first element of the matrix.
   * @param m12     first element in second line of the matrix.
   * @param m21     second element in first line of the matrix.
   * @param m22     second element in second line of the matrix.
   * @param i       count operand.
   * @param j       count operand.
   * @brief Separate the elements of the matrix.
   */
  private static void matrixElementSplitter(Matrix matrix, int newSize, Matrix m11, Matrix m12, Matrix m21, Matrix m22, int i, int j) {
    m11.elements[ i ][ j ] = matrix.elements[ i ][ j ];                     // top left
    m12.elements[ i ][ j ] = matrix.elements[ i ][ j + newSize ];           // top right
    m21.elements[ i ][ j ] = matrix.elements[ i + newSize ][ j ];           // bottom left
    m22.elements[ i ][ j ] = matrix.elements[ i + newSize ][ j + newSize ]; // bottom right
  }

}
