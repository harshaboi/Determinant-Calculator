package Determinant_Calculator;
// Class that is used to simplify the output for RowReduce's getEchelonForm method.
public class EchelonHolder {
    private double[][] echelonForm;
    private int numSwaps;

    public EchelonHolder (double[][] echelonForm, int numSwaps) {
        this.echelonForm = echelonForm;
        this.numSwaps = numSwaps;
    }

    public double[][] getEchelonForm() {
        return echelonForm;
    }

    public int getNumSwaps() {
        return numSwaps;
    }
}
