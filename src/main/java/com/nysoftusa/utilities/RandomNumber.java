package com.nysoftusa.utilities;



import java.util.Random;



public class RandomNumber {

    public static String randomEmail = "aziz" + randomNumberGenerate() + "@gmail.com";

    public static void main(String[] args) {
        // Generate random email address
        String myEmail = "aziz@gmail.com";
        //  randomNumberGenerate();

        // String randomEmail="aziz"+randomNumberGenerate()+"@gmail.com";
        System.out.println(randomEmail);
        System.out.println("aziz" + randomNumberGenerate() + "@gmail.com");

        System.out.println("=================================================");
        System.out.println(randomEmailGenerate("mahmud"));
        System.out.println("923-"+randomNumberGenerate());
        System.out.println("Double Number : "+"123"+randomNumberGenerateDouble());

    }

    public static int randomNumberGenerate() {
        Random random = new Random();
        return random.nextInt(10000000);
    }

    public static double randomNumberGenerateDouble() {
        return Math.random();
    }

    public static String randomEmailGenerate(String name) {
        return name + randomNumberGenerate() + "@gmail.com" ;
    }






}
