package cyber.projects.vz;

import java.util.Random;


/**
 * passwordTool
 * This class will help create random passwords and shift enterd passwords based on the users preference
 * @author Vaibhav Zaveri
 * @date 3/8/2022
 */

public class passwordTool {


    /**
    * constructor
    * empty - created for testing
    */
    public passwordTool(){
    }

    /**
    * main method
    * when run, created a randomly generated password
    */
    public static void main(String[] args){

    String Lower = "abcdefghijklmnopqrstuvwxyz";
    String Upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String Digits = "0123456789";
    String SpecialChars = "!@#$%&*()_+-=[]|,./?><";
    String combined = Lower + Upper + Digits + SpecialChars;
    int length = 12;
    char[] password = new char[length];
    Random pwd = new Random();

    for(int i = 0;i<length;i++){
        password[i] = combined.charAt(pwd.nextInt(combined.length()));

    }
    System.out.println("Generated Password:"+new String(password));
    }

    /**
    * shifts the password by a fee positions
    * @param changePwd the password that needs to be changed
    * @param shift key by which password will shift
    * method type: mutator - changes password
    */
    public String passwordChanger(String changedPwd, int shift) throws NullPointerException, IllegalArgumentException{
        if(changedPwd == null){
            throw new NullPointerException("Please enter password"); 
        }
        if(changedPwd.isEmpty()){
            throw new IllegalArgumentException("Please enter password"); 
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < changedPwd.length(); i++) {
            char c = changedPwd.charAt(i);
            sb.append((char) (c + shift));
        }
        return sb.toString();
    }
    
    
}
