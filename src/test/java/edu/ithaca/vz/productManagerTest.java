package edu.ithaca.vz;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;


/**
 * productManagerTest 
 * This class will help test if the affiliation of the product manager is checked or not.
 * @author Vaibhav Zaveri
 * @date 3/8/2022
 */

public class productManagerTest {

    @Test
    void constructorTest() throws IllegalArgumentException{ 

        //checking if product managers affiliation is not Google
        assertThrows(IllegalArgumentException.class, ()-> new productManager("John", "Kick", "john12@gmail.com", "Princeton"));
        
    }
    
}
