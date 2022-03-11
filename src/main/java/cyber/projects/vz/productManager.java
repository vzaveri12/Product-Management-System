package cyber.projects.vz;


/**
 * productManager
 * This class will have information about the product manager and check his/her affiliation.
 * @author Vaibhav Zaveri
 * @date 3/8/2022
 */

public class productManager {
    private String firstName;
    private String lastName;
    private String email;
    protected String affiliation;


    /**
    * constructor
    * @param firstName first name
    * @param lastName  last name
    * @param email email address
    * @param affiliation US or International
    * @throws IllegalArgumentException for unverified backgrounds
    */

    public productManager(String firstName, String lastName, String email, String affiliation) throws IllegalArgumentException{
        if(affiliation == "Google"){
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.affiliation = affiliation;
        }
        else{
            throw new IllegalArgumentException("Cannot process request");
        }

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
    * returns the email 
    * @return email
    * method type: accessor
    */
    public String getemail(){
        return email;
    }

    /**
    * returns the affiliation 
    * @return affiliation
    * method type: accessor
    */
    public String getaffliliation(){
        return affiliation;
    }

}
