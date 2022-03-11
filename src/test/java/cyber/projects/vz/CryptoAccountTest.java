package cyber.projects.vz;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class CryptoAccountTest {

    @Test
    void constructorTest() throws IllegalArgumentException, UnacceptedCoinException{
         
        //test unacceptable coins
        assertThrows(UnacceptedCoinException.class,()-> new cryptoWallet(2, "Ripple"));
        assertThrows(UnacceptedCoinException.class,()-> new cryptoWallet(20, "Ethercoin"));
        
        //test negative values
        assertThrows(IllegalArgumentException.class,()-> new cryptoWallet(-1, "Ethercoin"));

    }

    @Test
    void depositTest() throws UnacceptedCoinException, IllegalArgumentException{
        CryptoAccount account1 = new CryptoAccount(5, "Bitcoin");
         
        //testing acceptable amount values
        account1.deposit(2, "Bitcoin");
        account1.deposit(200, "Bitcoin");
        account1.deposit(0, "Bitcoin"); //border case
        assertEquals(207, account1.getBalance(), 0.001);

        //integration tests
        assertThrows(UnacceptedCoinException.class,() -> account1.deposit(5, "Ripple"));  //unacceptable currency
        assertThrows(IllegalArgumentException.class,() -> account1.deposit(-5, "Bitcoin")); //negative value
        assertThrows(IllegalArgumentException.class,() -> account1.deposit(-50, "Chitcoin")); //negative value and unacceptable currency

    }

    @Test
    void transferTest() throws IllegalArgumentException, UnacceptedCoinException, insufficientFundsException {
        CryptoAccount s1 = new cryptoWallet(2, "Ethereum");
        CryptoAccount s2 = new cryptoWallet(2, "Ethereum");
        CryptoAccount acc1 = new cryptoWallet(20, "Bitcoin");
        CryptoAccount acc2 = new cryptoWallet(2, "Bitcoin");
        
        s1.transfer(1,"Ethereum", s2); //border case
        assertEquals(1, s1.getBalance(), 0.001);
        assertEquals(3, s2.getBalance(), 0.001);

        CryptoAccount account1 = new CryptoAccount(1000, "Bitcoin");
        CryptoAccount account2 = new CryptoAccount(1000, "Bitcoin");
        
        //integration tests
        acc1.transfer(20, "Bitcoin", account2); //transferring all coins, border case
        assertThrows(insufficientFundsException.class,()-> acc1.transfer(1, "Bitcoin", account2)); //Account empty
    
        acc2.transfer(0, "Bitcoin", account1); //border case
        assertEquals(1000, account1.getBalance(),0.001);
        assertEquals(1020, account2.getBalance(),0.001);
   
        //failed cases that cover decimals and negative values
        assertThrows(IllegalArgumentException.class,()-> acc1.transfer(-50, "Bitcoin", account2));
        assertThrows(IllegalArgumentException.class,()-> acc1.transfer(34.1245, "Ethereum", account2));  
    }

    @Test
    void calculateAsset() throws UnacceptedCoinException{ //getting usd value here
        
        CryptoAccount s1 = new cryptoWallet(2, "Ethereum");
        CryptoAccount s2 = new cryptoWallet(1000, "USDCoin");

        //calculating net worth for acceptable coins
        s1.calculateAsset(2, "Ethereum");
        assertEquals(2, s1.getBalance(), 0.001);
        assertEquals(5000, s1.getusdValue(), 0.001);

        s2.calculateAsset(1000, "USDCoin"); //border case based on wallet
        assertEquals(1000, s2.getusdValue(), 0.001);
         
        //testing for false cases
        assertThrows(IllegalArgumentException.class,()-> s2.calculateAsset(-1000, "Bitcoin")); // negative number
        assertThrows(UnacceptedCoinException.class,()-> s2.calculateAsset(1000, "XML")); //unaccepted currency
        
        

    }




    
}
