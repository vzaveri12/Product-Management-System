package edu.ithaca.vz;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class passwordToolTest {

    
    @Test
    void passwordChangerTest() throws NullPointerException, IllegalArgumentException {
        passwordTool product1 = new passwordTool();

       //test to see when password is empty
       assertThrows(IllegalArgumentException.class,()->  product1.passwordChanger("", 2));

       //tests to see shift in different types of characters
       assertEquals("234567", product1.passwordChanger("123456", 1)); //border case for shift
       assertEquals("CDEF", product1.passwordChanger("ABCD",2));
       assertEquals("cdef", product1.passwordChanger("abcd",2));
       assertEquals("%'B/", product1.passwordChanger("#%@-",2));

       //test case when there is no shift
       assertEquals("123456", product1.passwordChanger("123456", 0));

       //test to see charcter shift in combined password
       assertEquals("6F(op):;", product1.passwordChanger("1A#jk$56", 5));

    }
    }

