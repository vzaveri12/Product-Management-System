package edu.ithaca.vz;

public class insufficientFundsException extends Exception{
    private static final long serialVersionUID = 1L;

    public insufficientFundsException(String s) {
        super(s);
    }

}
