package Determinant_Calculator;

public class DeterminantTest {
    public static void main(String[] args) {
        double d1 = 1817.0/3000;
        double[][] matrix = 
         {{0, 97, 650, 6542}
        , {3807, 192, -50, -15.396}
        , {d1, 65, 67, 27}
        , {9000, 789, 5.72, .5}};
        RowReduce.printMatrix(matrix, "");
        System.out.println("Determinant: " + DeterminantCalculator.getDeterminant(matrix));
    }
}
