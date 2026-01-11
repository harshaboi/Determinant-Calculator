package Determinant_Calculator;

import java.util.HashMap;
import java.util.ArrayList;
public class RowReduce {
    /**
     * gets the echelon form of a given matrix
     *
     * @param input the given matrix
     * @return the echelon form of the given matrix and the number of row swaps
     */
    public static HashMap<double[][], Integer> getEchelonForm(double[][] input) {
        double[][] result = new double[input.length][input[0].length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                result[i][j] = input[i][j];
            }
        }
        int numSwaps = swapRowsForEchelon(result);
        numSwaps += reduceForEchelon(result);
        HashMap<double[][], Integer> results = new HashMap<>();
        results.put(result, numSwaps);
        return results;
    }

    private static int swapRowsForEchelon(double[][] matrix) {
        int numSwaps = 0;
        for (int col = 0; col < matrix[0].length; col++) {
            if (matrix[col][col] == 0) {
                for (int row = col + 1; row < matrix.length; row++) {
                    if (matrix[row][col] != 0) {
                        swapRow(matrix, col, row);
                        numSwaps++;
                    }
                }       
            }
        }
       //printMatrix(matrix);
        return numSwaps;
    }

    private static int reduceForEchelon(double[][] matrix) {
        int numSwaps = 0;
        for (int col = 0; col < matrix[0].length; col++) {
            double pivotVal = matrix[col][col];
            if (pivotVal == 0) {
                numSwaps += swapRowsForEchelon(matrix);
            }
            //System.out.println(pivotVal);
            for (int row = col + 1; row < matrix.length; row++) {
                if (!(matrix[row][col] < 0.0001 && matrix[row][col] > -0.0001)){
                    addToRow(matrix, row, col, matrix[row][col] / pivotVal);
                }
                printMatrix(matrix, pivotVal + "");
            }
        }
        //printMatrix(matrix);
        return numSwaps;
    }

    public static double[] getFactors(double[][] input) {
        return null;
    }

    private static void swapRow(double[][] matrix, int row1Ind, int row2Ind) {
        double[] temp = matrix[row2Ind];
        matrix[row2Ind] = matrix[row1Ind];
        matrix[row1Ind] = temp;
    }

    private static void addToRow(double[][] matrix, int toAdd, int adding, double factor) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[toAdd][i] -= (matrix[adding][i] * factor);
        }
    }

    private static void scaleRow(double[][] matrix, int rowInd, double factor) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[rowInd][i] *= factor;
        }
    }

    private static void sortRow(double[][] result, int col) {
        if (result[col][col] == 0) {
            int row = col + 1;
            boolean swapped = false;
            while (row < result.length) {
                if (result[row][0] != 0) {
                    swapRow(result, col, row);
                    swapped = true;
                    break;
                }
                row++;
            }
            if (swapped) {
                for (int j = 0; j < result.length; j++) {
                    addToRow(result, row, j, -(result[j][0] / result[row][0]));
                }
            }
        }
    }

    public static boolean isEchelonForm(double[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[j][i] != 0) return false; 
            }
        }
        return true;
    }

    public static void printMatrix(double[][] matrix, String msg) {
        System.out.println(msg);
        for (double[] x : matrix) {
            for (double y : x) {
                System.out.print(y + ", ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
