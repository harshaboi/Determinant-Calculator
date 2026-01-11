package Determinant_Calculator;

import java.util.HashMap;
import java.util.ArrayList;
public class RowReduce {
    /**
     * gets the echelon form of a given matrix
     *
     * @param input the given matrix
     * @return A HashMap that contains only one key-value pair where the key is the echelon form of the matrix
     * and the Integer is the number of row swaps that have been done while reducing to echelon form.
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

    /**
     * Swaps rows of a given matrix so that the matrix is ready to be row reduced to echelon form.
     *
     * @param matrix The matrix to have its rows swapped.
     * @return the number of swaps that have been done.
     */
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

    /**
     * This method row reduces the given matrix to row echelon form through Gaussian Elimination.
     *
     * @param matrix the matrix to be reduced to row echelon form.
     * @return the number of row swaps that have been done.
     */
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

    /**
     * Helper method that swaps two rows in a matrix.
     *
     * @param matrix the matrix whose rows are being swapped.
     * @param row1Ind the row index of the first row being swapped.
     * @param row2Ind the row index of the second row being swapped.
     */
    private static void swapRow(double[][] matrix, int row1Ind, int row2Ind) {
        double[] temp = matrix[row2Ind];
        matrix[row2Ind] = matrix[row1Ind];
        matrix[row1Ind] = temp;
    }

    /**
     * Helper method that does row replacements.
     *
     * @param matrix The matrix whose rows are having row replacements.
     * @param toAdd the index of the row that is being replaced.
     * @param adding the index of the row that is replacing.
     * @param factor the factor/scale multiplied to the row that is replacing.
     */
    private static void addToRow(double[][] matrix, int toAdd, int adding, double factor) {
        for (int i = 0; i < matrix[0].length; i++) {
            //Using subtraction a multiple of a row is usually being substracted from another row.
            matrix[toAdd][i] -= (matrix[adding][i] * factor);
        }
    }
    // Not necessary for my determinant calculator
    private static void scaleRow(double[][] matrix, int rowInd, double factor) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[rowInd][i] *= factor;
        }
    }
    // Not necessary for my determinant calculator
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

    /**
     * Method that checks if a matrix is in echelon form.
     * @param matrix
     * @return
     */
    public static boolean isEchelonForm(double[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[j][i] != 0) return false; 
            }
        }
        return true;
    }

    /**
     * Helper method that prints matrices. (used for debugging primarily)
     *
     * @param matrix Matrix to be printed.
     * @param msg added before the matrix is being printed (used for debugging)
     */
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
