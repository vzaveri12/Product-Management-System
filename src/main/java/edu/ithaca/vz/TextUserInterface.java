package edu.ithaca.vz;

import java.util.Scanner;

public class TextUserInterface {
    public static void main(String[] args) throws IllegalArgumentException, UnacceptedCoinException{

        Scanner myObj = new Scanner(System.in);
        shareholder shareholder1 = new shareholder("Mark", "Bentley", 789128, "Mark123@gmail.com", "Palo Alto Networks", "US", "T3", 1200, 20 , "A");
        CryptoAccount account1 = new CryptoAccount(20, "Bitcoin");
        CryptoAccount account2 = new CryptoAccount(5, "Bitcoin");
        shareholder1.addCryptoWallet(account1);
        System.out.println("Welcome, Mr. Mark bentley!");

        //Background check
        int option = 0;
        while(option!=4){
            System.out.println("Welcome, To access our product, please enter your background status");
            System.out.println("1 - TS/SCI");
            System.out.println("2 - T3");
            System.out.println("3 - checked");
            System.out.println("4 - General/Other");
            option = myObj.nextInt();

            //unverified shareholders
            while(option>3){
                System.out.println("Please get your background verified.");
                option = myObj.nextInt(); 
            }
            //Invalid option
            while(option<0){
                System.out.println(" Invalid option, Re-enter option");
                option = myObj.nextInt(); 
            }
            //For verified shareholders
            if(option ==1 || option ==2 || option ==3){
                while(option != 5){
                System.out.println("Please choose from the options below");
                System.out.println("1 - Check Balance");
                System.out.println("2 - Deposit Cryptocurrency to account");
                System.out.println("3 - Transfer Cryptocurrency");
                System.out.println("4 - Access product");
                option = myObj.nextInt();
            }

        

            // Invalid option
            while(option>4 || option <0){
                System.out.println("Invalid option, Re-enter option");
                option = myObj.nextInt(); 
            }

            if(option == 1){
                System.out.println("The amount of cryptocurrency coins in account:" + shareholder1.getBalance());
            }

            if(option ==2){
                System.out.println("Please enter the amount of coins and type of cryptocurrency that needs to be deposited:");
                double amount = myObj.nextDouble();
                String currency = myObj.nextLine();
                try{
                     shareholder1.cryptoWallet.depositCrypto(2, "Bitcoin");
                     System.out.println("Deposit Successful");
                }
                catch(UnacceptedCoinException error){
                    System.out.println("Unaccepted cryptocurrency" + error);
                }
            }
            if(option ==3){
                System.out.println("Please enter the amount of coins and type of cryptocurrency that needs to be transferred:");
                double amount = myObj.nextDouble();
                String currency = myObj.nextLine();
                try{
                    shareholder1.transferCrypto(1, "Bitcoin", account1);
                    System.out.println("Transfer Successful");
                }
                catch(UnacceptedCoinException error){
                    System.out.println("Unaccepted cryptocurrency" + error);
                }
                catch(insufficientFundsException error){
                    System.out.println("Insufficient Funds, please deposit" + error);
                }

            }


            if(option ==4){
                while(true){
                    System.out.println("Select type of contract");
                    System.out.println("1 - Create Contract");
                    System.out.println("2 - Delete Contract");
                    option = myObj.nextInt();
                    

                    if(option ==1){
                        shareholder1.contract(1);
                        System.out.println("Thank you for partnering with us!");
                        System.out.println("Please contact the Auditor for further details");
                        break;
    
                    }
                    if(option ==2){
                        shareholder1.contract(2);
                        System.out.println("Its sad to see you go");
                        System.out.println("Please contact the Auditor for further details");
                        break;
                    }
                    else{
                        System.out.println("Invalid option");
                        continue;

                    }
                }

            }

        }


        System.out.println("Thank you for your time!");
        myObj.close();
    

    }
}

}
    
