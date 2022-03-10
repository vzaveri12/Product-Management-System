package edu.ithaca.vz;


public class CryptoAccount {
    private double balance;
    private String currency;
    private double usdValue;

    /**
    * Constructor
    * @param startingBalance
    * @param currencyIn
    * @throws IllegalArgumentException
    * @throws UnacceptedCoinException
    */
    public CryptoAccount(double startingBalance,String currencyIn) throws IllegalArgumentException, UnacceptedCoinException{
        if(!isAmountValid(startingBalance)){
            throw new IllegalArgumentException(" Enter a number above 0 ");
        }
        else if(currencyIn.equals("Bitcoin") || currencyIn.equals("Ethereum") || currencyIn.equals("USDCoin")){
            this.balance = startingBalance;
            this.currency = currencyIn;

        }
        else{
            throw new UnacceptedCoinException(" Cannot accept this cryptocurrency");
        }
    }
    
    /**
    * returns the current balance 
    * @return balance
    * method type: accessor
    */
    public double getBalance(){
        return balance;
    }

    /**
     * isAmountValid
     * @param amount
     * @return false is amount is invalid
     */
    public static boolean isAmountValid(double amount){
        String doubleStr = Double.toString(amount);

        if(amount < 0){
            return false;
            
        }
        else if(doubleStr.substring(doubleStr.lastIndexOf('.'), doubleStr.length() - 1).length() > 2){ //check if amount has 5 or more digits (possibility that there is 3 decimals) 300.67 , 30.678
            return false;
        }
        else{
            return true;
        }

    }

    /**
     * allows user to deposit amount of coins in account
     * @param amount
     * @param currency
     * @throws UnacceptedCoinException
     * @throws IllegalArgumentException
     */
    public void deposit(double amount, String currency) throws UnacceptedCoinException, IllegalArgumentException {
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("amount cannot be negative or have more than 2 decimal places");
        }
        else if (currency.equals("Bitcoin") || currency.equals("Ethereum") || currency.equals("USDCoin")) {
            balance = balance + amount;
            
        } 
        else {
            throw new UnacceptedCoinException("Please trade in currencies excepted by us");
        } 
    }

    /**
     * transfers amount with currency type to another account
     * @param amount
     * @param currency
     * @param transferee
     * @throws UnacceptedCoinException
     * @throws insufficientFundsException
     */

    public void transfer(double amount, String currency, CryptoAccount transferee) throws UnacceptedCoinException, insufficientFundsException, IllegalArgumentException {
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("amount cannot be negative or have more than 2 decimal places");
        }
        else if (currency.equals("Bitcoin") || currency.equals("Ethereum") || currency.equals("USDCoin")) {
            withdraw(amount, currency);
            transferee.deposit(amount, currency);
        }
        else if(amount > balance){
            throw new insufficientFundsException("Add more coins to account");

        }
        else{
            throw new UnacceptedCoinException("Please trade in currencies excepted by us");
        
        }

    }
    /**
     * allows user to withdraw certain currency and amount from account
     * @param amount
     * @param currency
     * @throws insufficientFundsException
     */
    public void withdraw (double amount, String currency) throws insufficientFundsException{
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("amount cannot be negative or have more than 2 decimal places");
        }
        else if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new insufficientFundsException("Not enough coins");
        }
    }
    
    /**
    * calculates assets by US dollar
    * @param amount
    * @param currency
    * @throws UnacceptedCoinException
    * @throws IllegalArgumentException
    */
    public void calculateAsset(double amount, String currency) throws UnacceptedCoinException, IllegalArgumentException{
           if(!isAmountValid(amount)){
               throw new IllegalArgumentException("invalid");
           } 
           if(currency.equals("Bitcoin")){
                usdValue= amount * 40000;
            }
            else if(currency.equals("Ethereum")){
                 usdValue = amount * 2500;
            }
            else if(currency.equals("USDCoin")){
                 usdValue = amount *1;
            }
            else if(!isAmountValid(amount)){
                throw new IllegalArgumentException("");
            }
            else{
                throw new UnacceptedCoinException("coin not accepted");     
            }
        }

    /**
     * getusdValue
     * @return usdValue
     * method type: getter
     */
    public double getusdValue(){
        return usdValue;
    }
    
}
