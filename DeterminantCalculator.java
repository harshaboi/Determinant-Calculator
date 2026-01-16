package Determinant_Calculator;

public class DeterminantCalculator {
    /**
     * This method calculates the determinant of the given matrix.
     *
     * @param matrix the matrix whose determinant is being calculated.
     * @return the determinant of the matrix.
     */
    public static Double getDeterminant(double[][] matrix) {
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else if (matrix.length == 1) {
            return matrix[0][0];
        } else if (matrix.length != matrix[0].length) return null; // Returns null if the given matrix is not square
        EchelonHolder e = RowReduce.getEchelonForm(matrix);
        double[][] result = e.getEchelonForm();
        int numSwaps = e.getNumSwaps();
        double product = 1;
        for (int i = 0; i < result.length; i++) {
            product *= result[i][i];
        }
        if (numSwaps % 2 == 1) {
            product *= -1;
        }
        product = product == 0 ? 0 : product; // Gets rid of negative sign for product if product is 0
        return product;
    }

}
