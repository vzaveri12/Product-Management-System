package cyber.projects.vz;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class shareholderTest {

    @Test
    void constructorTest() throws IllegalArgumentException{

        //checking if shareholders affiliation is unverified (not TS/SCI, checked or T3)
        assertThrows(IllegalArgumentException.class, ()-> new shareholder("ven", "kransit", 8888, "ven@yahoo.com", "Facebook", "US", "General", 2000, 2, "C"));
    }
    @Test
    void integrationTest() throws IllegalArgumentException, UnacceptedCoinException, insufficientFundsException{
        //case - shareholder tries to deposit unacceptable cryptocurrency
        
        shareholder shareholder1 = new shareholder("John", "Wick", 7329801, "john@stanford.edu", "Stanford University", "US", "checked", 2300,2, "A");
        CryptoAccount acc1 = new CryptoAccount(2, "Bitcoin"); //account of shareholder
        CryptoAccount acc2 = new CryptoAccount(4, "Bitcoin"); //account of company

        shareholder1.addCryptoWallet(acc1);
        acc1.calculateAsset(2, "Bitcoin"); //has 2 bitcoins
        assertEquals(2, acc1.getBalance(),0.001); //shows 2 bitcoins
        assertEquals(80000, acc1.getusdValue()); //get usd value of 2 bitcoins
        
        assertThrows(UnacceptedCoinException.class,()-> shareholder1.depositCrypto(2 ,"XRP")); // shareholder tries to deposit unaccepted cryptocurrency
        assertEquals(2, acc1.getBalance(),0.001); // amount is still same, deposit unsuccessful
        assertEquals(80000, acc1.getusdValue()); //worth of totoal coins in USD
        shareholder1.transferCrypto(1, "Bitcoin" , acc2); // shareholder transfers acceptable cryptocurrency
        assertEquals(5, acc2.getBalance(),0.001); //shows 5 bitcoins (4+1) - transfer successful
        acc2.calculateAsset(5, "Bitcoin"); 
        assertEquals(200000, acc2.getusdValue()); //worth of totoal coins in USD
        shareholder1.contract(1); //shareholder wants to form a contract after transferring funds
    }

    @Test
    void depositCryptoTest() throws IllegalArgumentException, UnacceptedCoinException, NullPointerException{
        shareholder s1 = new shareholder("Ven", "Kiloj", 6781, "ven@gmail.com", "Ford", "US", "T3", 700, 5000, "B");
        CryptoAccount account1 = new CryptoAccount(5000, "Ethereum");
        s1.addCryptoWallet(account1);

        //checking for depositing acceptable currency and acceptable amounts
        s1.depositCrypto(50, "Ethereum");
        s1.depositCrypto(2, "Bitcoin");
        s1.depositCrypto(500, "USDCoin");
        s1.depositCrypto(5.0000, "USDCoin");
        s1.depositCrypto(0.01, "USDCoin"); //border case
        s1.depositCrypto(0, "Bitcoin"); //border case

        //checking for false cases - looking at possible issues (Equivalence tests)
        assertThrows(IllegalArgumentException.class,()-> s1.depositCrypto(50, "")); //empty string
        assertThrows(UnacceptedCoinException.class,()-> s1.depositCrypto(50, "USDeum")); // possible border case
        assertThrows(UnacceptedCoinException.class,()-> s1.depositCrypto(50, "Ripple")); //unaccepted coin
        assertThrows(IllegalArgumentException.class,()-> s1.depositCrypto(-50, "USDCoin"));  // negative value
        assertThrows(IllegalArgumentException.class,()-> s1.depositCrypto(5.01122343340, "Ethereum")); //too many decimals

    }

    @Test
    void transferCrypto() throws IllegalArgumentException, UnacceptedCoinException, insufficientFundsException{
        shareholder acc1 = new shareholder("Ven", "Kiloj", 6781, "ven@gmail.com", "Ford", "US", "T3", 700, 5000, "B");
        shareholder acc2 = new shareholder("Kenny", "miko", 989, "ken@gmail.com", "Tata", "US", "T3", 700, 5000, "B");
        
        CryptoAccount account1 = new CryptoAccount(5000, "Ethereum");
        CryptoAccount account2 = new CryptoAccount(5000, "Ethereum");
        
        acc1.addCryptoWallet(account1);
        acc2.addCryptoWallet(account2);

        acc1.transferCrypto(5000, "Ethereum", account2); //transferring all coins, border case
        assertThrows(insufficientFundsException.class,()-> acc1.transferCrypto(1, "Ethereum", account2)); //Account empty
        
        acc2.transferCrypto(0, "Ethereum", account1); //border case
        assertEquals(0, account1.getBalance(),0.001);
        assertEquals(10000, account2.getBalance(),0.001);

        acc2.transferCrypto(2.25, "Ethereum", account1); //border case
        
        //failed cases that cover decimals, negative values, and different cryptocurrencies
        assertThrows(UnacceptedCoinException.class,()-> acc1.transferCrypto(50, "Ripple", account2)); 
        assertThrows(IllegalArgumentException.class,()-> acc1.transferCrypto(-50, "USDCoin", account2));
        assertThrows(IllegalArgumentException.class,()-> acc1.transferCrypto(50.1245, "USDCoin", account2));  


    }

    @Test
    void isPasswordValidTest(){

        //invalid cases - missing numbers, special characters, lowercase letters, uppercase letters or all
        assertFalse(shareholder.isPasswordValid(""));
        assertFalse(shareholder.isPasswordValid("12345895"));
        assertFalse(shareholder.isPasswordValid("achdnsedk"));
        assertFalse(shareholder.isPasswordValid("GDJEUDDNK"));
        assertFalse(shareholder.isPasswordValid("@#$%@#"));
        assertFalse(shareholder.isPasswordValid("123abGF"));
        assertFalse(shareholder.isPasswordValid("@@#$GDGTavd"));
        assertFalse(shareholder.isPasswordValid("@#$"));
        assertFalse(shareholder.isPasswordValid("A1$c"));
        //valid case
        assertTrue(shareholder.isPasswordValid("abcdABCD123[]$##WQ"));
    }

}