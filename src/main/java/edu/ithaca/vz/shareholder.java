package edu.ithaca.vz;


/**
 * shareholder
 * This class will help shareholder buy products via their crypto account and also help them look over it.
 * @author Vaibhav Zaveri
 * @date 3/3/2022
 */

public class shareholder {

    private String firstName;
    private String lastName;
    protected int accountID;
    private String email;
    public CryptoAccount cryptoWallet;
    protected String institution;
    private String affiliation;
    private String background;
    protected int NumofShares;
    private double balance;
    protected String ClassOfShares;
    protected cryptoWallet cryptowallet;
    
    /**
    * constructor
    * @param firstName first name
    * @param lastName  last name
    * @param accountID account ID
    * @param email email address
    * @param institution organization where person works
    * @param affiliation US or International
    * @param background TS/SCI, T3, General, checked
    * @param NumofShares Number of shares held by shareholder
    * @param ClassOfShares A, B or C
    * @throws IllegalArgumentException for unverified backgrounds
    */

    public shareholder(String firstName, String lastName, int accountID, String email, String institution, String affiliation, String background, int NumofShares,double balance, String ClassOfShares) throws IllegalArgumentException{
        if(background == "checked" || background == "TS/SCI" || background == "T3" ){
            this.firstName = firstName;
            this.lastName = lastName;
            this.accountID = accountID;
            this.email = email;
            this.institution = institution;
            this.affiliation = affiliation;
            this.background = background;
            this.NumofShares = NumofShares;
            this.ClassOfShares = ClassOfShares;
            this.balance = balance;
            cryptoWallet = null;
        }
        else{
            throw new IllegalArgumentException("Cannot access product, need background check");
        }

    }

    /**
    * Adds a new cryptocurrency wallet to the crypto account class
    * @param cryptoWallet a new cryptocurrency wallet
    * method type: mutator
    */
    public void addCryptoWallet(CryptoAccount cryptoWallet){
        this.cryptoWallet = cryptoWallet;
    }

    /**
    * isAmountValid - looks at acceptable amount value
    * @return true if amount is acceptable, else false
    *  method type - accessor - checks amount
    */
    public static boolean isAmountValid(double amount){
        String doubleStr = Double.toString(amount);

        if(amount < 0){
            return false;
        }
        else if(doubleStr.substring(doubleStr.lastIndexOf('.'), doubleStr.length() - 1).length() > 4){ //check if amount has 5 or more digits (possibility that there is 3 decimals) 300.67 , 30.678
            return false;
        }
        else{
            return true;
        }
    }

    /**
    * deposits amount of coins into crypto wallet unless the cryptocurrency is not accepted
    * @param amount Number of cryptocurrency coins that are being deposited
    * @param currency type of cryptocurrency being deposited
    * @throws UnacceptedCoinException
    * @throws IllegalArgumentException
    @throws NullPointerException
    * method type: mutator
    */
    public void depositCrypto(double amount, String currency) throws NullPointerException, UnacceptedCoinException, IllegalArgumentException{
        if(cryptoWallet == null){
            throw new NullPointerException("Please link Cryptocurrency wallet"); 
        }
        else if (currency.equals("Bitcoin") || currency.equals("Ethereum") || currency.equals("USDCoin")) {
            cryptoWallet.deposit(amount, currency);
        }
        else if(!isAmountValid(amount) || currency.length() == 0){
            throw new IllegalArgumentException("enter positive amount and define currency properly");
        }   
        else{
            throw new UnacceptedCoinException("Please trade in currencies accepted by us");
        } 
    }

    /**
    * transfers amount of coins of a specific cryptocurrency to another account unless the cryptocurrency is not accepted and there are insufficient funds
    * @param amount Number of cryptocurrency coins that needs to be transferred
    * @param currency type of cryptocurrency being deposited
    * @param transferee account to which the amount will be sent
    * @throws UnacceptedCoinException
    * @throws insufficientFundsException
    * @throws IllegalArgumentException
    * method type: mutator
    */
    public void transferCrypto(double amount, String currency, CryptoAccount transferee) throws IllegalArgumentException, insufficientFundsException, UnacceptedCoinException{
        if(cryptoWallet == null){
            throw new NullPointerException("Please link Cryptocurrency wallet"); 
        }
        else if (currency.equals("Bitcoin") || currency.equals("Ethereum") || currency.equals("USDCoin")) {
            cryptoWallet.transfer(amount,currency,transferee);
        }
        else if(!(isAmountValid(amount))){
            throw new IllegalArgumentException("amount cannot be zero,negative or have more than 2 decimal places");
        }
        else if(amount >= balance){
            throw new insufficientFundsException("Please add more funds to your account");
        } 
        else{
          throw new UnacceptedCoinException("Please trade in currencies excepted by us");
        }
    }

    /**
    * returns the current balance 
    * @return balance
    * method type: accessor
    */
    public double getBalance() { 

        return balance;
    }

    /**
    * returns the first name
    * @return firstName
    * method type: accessor
    */
    public String getFirstName() {
        return firstName;
    }

    /**
    * returns the last name 
    * @return lastName
    * method type: accessor
    */
    public String getLastName() {
        return lastName;
    }

    /**
    * returns the account id
    * @return account id
    * method type: accessor
    */
    public int getAccountID(){
        return accountID;
    }

    /**
    * returns the email 
    * @return email
    * method type: accessor
    */
    public String getEmail(){
        return email;
    }

    /**
    * returns the institution  
    * @return institution
    * method type: accessor
    */
    public String getInstitution(){
        return institution;
    }

    /**
    * returns the affiliation 
    * @return affiliation
    * method type: accessor
    */
    public String getAffiliation(){
        return affiliation;
    }

    /**
    * returns the background
    * @return background
    * method type: accessor
    */
    public String getBackground(){
        return background;
    }

    /**
    * returns the Class of shares owned
    * @return ClassOfShares
    * method type: accessor
    */
    public String getClassOfShares() {
        return ClassOfShares;
    }

    /**
    * returns the institution  
    * @return institution
    * method type: accessor
    */
    public int getNumofShares(){
        return NumofShares;
    }

    /**
    * checks if email is a valid and acceptable email
    * @param email 
    * method type: accessor - returns  true or false (this method is not changing or getting anything)
    */
    public static boolean isEmailValid(String email) {
        if (email.indexOf('@') == -1) {
            return false;
        } else if (email.length() <= 3) {
            return false;
        } else if (email.isEmpty()) {
            return false;
        } else if (email.indexOf('@') == 0 || email.indexOf('.') == 0) {
            return false;
        } else if (!Character.isLetter(email.charAt(email.indexOf('@') - 1))) {
            return false;
        } else if (email.contains("$") || email.contains("!") || email.contains("#")) {
            return false;
        } else if (email.charAt(email.indexOf('.')) == email.charAt(email.indexOf('.') + 1)) {
            return false;
        } else if (email.lastIndexOf('.') + 2 >= email.length()) {
            return false;
        } 
        if (email.indexOf('@') == -1){
            return false;
        }
        return true;
    }

    /**
    * shows the available contract options to the shareholders
    * @param option
    * method type: void : Used to request or delete contract and is helpful for UI
    */
    public  void contract(int option){
        if(option == 1){
          System.out.println("Request to create contract");
        }
        else if(option == 2){
            System.out.println ("Request to delete contract");
        }
        else{
            System.out.println ("No action needed");
        }
    }

    /**
    * looks if the password is acceptable or not
    * @param password
    * method type: accessor : Used to check different password test cases
    */
    public static boolean isPasswordValid(String password) {
        boolean sl = false;
        boolean cl = false;
        boolean num = false;
        boolean sc = false;
        
        String SpecialChars = ("!@#$%&*()_+-=[]|,./?><");

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if(ch >= 'a' && ch <= 'z') sl = true;
            if(ch >= 'A' && ch <= 'Z') cl =true;
            if(ch >= '0' && ch <= '9') num = true;
            if(SpecialChars.contains(""+ch)) sc = true;
            else if(password.length() <= 6){
                return false;
            }

        }
        return sl && cl && num && sc;
    }




}

