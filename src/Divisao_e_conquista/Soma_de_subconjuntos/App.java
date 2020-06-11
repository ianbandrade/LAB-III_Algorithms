package Divisao_e_conquista.Soma_de_subconjuntos;

/**
 * @author Ian Bittencourt
 */
class App {
  /**
   * @param set    Set
   * @param left   Left start position
   * @param medium Medium position
   * @param high   High position
   * @return Max possible sum in the middle part
   */
  static int maxSumInTheMiddlePart(int[] set, int left, int medium, int high) {
    int sum = 0;
    int leftSum = Integer.MIN_VALUE;
    for (int i = medium; i >= left; i--) {
      sum = sum + set[ i ];
      if ( sum > leftSum )
        leftSum = sum;
    }

    sum = 0;
    int rightSum = Integer.MIN_VALUE;
    for (int i = medium + 1; i <= high; i++) {
      sum = sum + set[ i ];
      if ( sum > rightSum )
        rightSum = sum;
    }

    return Math.max(leftSum + rightSum, Math.max(leftSum, rightSum));
  }

  /**
   * @param set  Set
   * @param left Left Start position
   * @param high High position
   * @return Sum of maximum sum subarray
   */
  static int maxSubsetSum(int[] set, int left, int high) {
    if ( left == high )
      return set[ left ];

    int mediumPart = (left + high) / 2;

    return Math.max(Math.max(maxSubsetSum(set, left, mediumPart),
      maxSubsetSum(set, mediumPart + 1, high)),
      maxSumInTheMiddlePart(set, left, mediumPart, high));
  }

  /**
   * @param set             Set
   * @param initialPosition Initial position
   * @param sum             Value that will be checked
   * @return If this subset sum is available
   */
  static boolean subsetSumCheck(int[] set, int initialPosition, int sum) {
    if ( sum == 0 )
      return true;
    if ( set.length - initialPosition == 1 )
      return set[ initialPosition ] == sum;

    boolean sum1 = subsetSumCheck(set, initialPosition + 1, sum - set[ initialPosition ]);

    boolean sum2 = subsetSumCheck(set, initialPosition + 1, sum);

    return sum1 | sum2;
  }

  /**
   * @param set Set
   * @param sum Value that will be checked
   * @return If this subset sum is available
   */
  static boolean subsetBruteForceSumCheck(int[] set, int sum) {
    return set.length == 1 ? (set[ 0 ] == sum) : set.length >= 1 && subsetSumCheck(set, 0, sum);
  }

  public static void main(String[] args) {
    int[] set = {10, 2, -15, 10, 50, -1, 3, -30, 10};
    int n = set.length - 1;
    int maxSubsetSum = maxSubsetSum(set, 0, n);

    System.out.println("Max subset sum = " + maxSubsetSum);

    System.out.println("Is " + maxSubsetSum + " sum available? " + subsetBruteForceSumCheck(set, maxSubsetSum));
  }
}
