package edu.ithaca.vz;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Auditor
 * This class will help Auditor to ask for information and form/delete contracts.
 * @author Vaibhav Zaveri
 * @date 3/8/2022
 */

public class Auditor {

    private Scanner input = new Scanner(System.in);


    /**
    * returns current date and time
    * @return date in specific format
    * method type: getter
    */
    String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
       return dtf.format(now);
    }

    /**
    * makes a contract
    * User Input needed for this method to work
    * output-> A text file with information about shareholder and Product Manager
    */
    public void makeContract() {
        System.out.println("Shareholder: ");
        Person shareholder = new Person();
        System.out.println("Product Manager:");
        Person productManager = new Person();

        String fileName = "contract.txt";
        String content = "------------------------------------------CONFIDENTIAL INFORMATION------------------------------------------\n" + "Contract Type: Create Contract\n" +
                "Date:- " + getDate() + "\n\n" + "Shareholder:\n" + shareholder + "\n\n" + "Product Manager:\n" + productManager + "\n\n" +" Please Contact our closest location for further details";
        File file = new File(fileName);
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * deletes a contract
    * output-> deletes a text file that was a contract
    */
    public void deleteContract() { 
        String fileName = "contract.txt";
        File file = new File(fileName);
        file.delete();
    }
 
    class Person {
        private String firstName;
        private String lastName;
        protected int accountID;
        private String email;
        private String affiliation;
        protected String institution;
        protected String background;

        /**
        * constructor
        * @param firstName first name
        * @param lastName  last name
        * @param accountID account ID
        * @param email email address
        * @param institution organization where person works
        * @param affiliation US or International
        * @param background TS/SCI, T3, General, checked
        * @throws IllegalArgumentException for unverified backgrounds
        */
        public Person(String firstName, String lastName, int accountID, String email, String institution, String affiliation, String background) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.accountID = accountID;
            this.email = email;
            this.institution = institution;
            this.affiliation = affiliation;
        }

        /**
         * Asks for input to add to text file
         */
        public Person() {
            System.out.print("Enter First name:");
            this.firstName = input.nextLine();
            System.out.print("Enter Last name:");
            this.lastName = input.nextLine();
            System.out.print("Enter account ID:");
            this.accountID = input.nextInt();
            input.nextLine();
            System.out.print("Enter email:");
            this.email = input.nextLine();
            System.out.print("Enter Institution:");
            this.institution = input.nextLine();
            System.out.print("Enter affiliation (US or International):");
            this.affiliation = input.nextLine();
            System.out.print("Enter Background(TS/SCI, T3, General, checked):");
            this.background = input.nextLine();
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
        * @return LastName
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
        * toString()
        * @return details like first name, account ID, email etc
        */
        @Override
        public String toString() {
            return "Details:\n" + firstName + "\n" + lastName + "\n" + accountID + "\n"+ email +"\n"+ institution +"\n" + affiliation + "\n" + institution;

        }
    }
}

