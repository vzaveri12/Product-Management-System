package edu.ithaca.vz;

public class cryptoWallet  extends CryptoAccount{

    

    /**
     * cryptoWallet - used to create a new cryptocurrency wallet
     * @param startingBalance
     * @param currencyIn
     * @throws IllegalArgumentException
     * @throws UnacceptedCoinException
     */
    public cryptoWallet(double startingBalance, String currencyIn) throws IllegalArgumentException, UnacceptedCoinException {
        
        super(startingBalance,currencyIn);

    }
}