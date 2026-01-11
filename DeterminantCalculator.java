package Determinant_Calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeterminantCalculator {
    /**
     * This method calculates the determinant of the given matrix.
     *
     * @param matrix the matrix whose determinant is being calculated.
     * @return the determinant of the matrix.
     */
    public static Double getDeterminant(double[][] matrix) {
        if (matrix.length != matrix[0].length) return null;
        HashMap<double[][], Integer> both = RowReduce.getEchelonForm(matrix);
        double[][] result = new double[0][0];
        int numSwaps = 0;
        for (Map.Entry<double[][], Integer> entry : both.entrySet()) {
            result = entry.getKey();
            numSwaps = entry.getValue();
        }
        double product = 1;
        for (int i = 0; i < result.length; i++) {
            product *= result[i][i];
        }
        if (numSwaps % 2 == 1) {
            product *= -1;
        }
        product = product == 0 ? 0 : product; //Gets rid of negative sign for product if product is 0
        return product;
    }

}
