package com.nysoftusa.testpages;


import com.nysoftusa.Pages.SheinHomePage;
import org.testng.annotations.Test;

public class SheinHomePageWebTest extends SheinHomePage {
    // Test Class: where we wrote all the test cases
    // Test case means the methods which belongs to a @Test annotation
    @Test
    public static void verifyOfSheinHomePage(){
        checkFunctionalityOfSheinHomePage();
        System.out.println("Connection Successfull");

    }


}
