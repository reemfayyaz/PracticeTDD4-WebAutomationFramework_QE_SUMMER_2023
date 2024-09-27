package com.nysoftusa.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {

    public static String relativePath = "../TDD-WebAutomationFramework_QE_SUMMER2023/DataTest/Sample.txt";


    public static void main(String[] args) {
        readDataFile(relativePath);
    }


    public static void readDataFile(String filePath) {
        // File Reader
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                System.out.println(data);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not Found Exception : " + e.getMessage());
            //  throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("IO Exception : " + e.getMessage());
            //   throw new RuntimeException(e);
        } finally {

            try {
                assert bufferedReader != null;
                bufferedReader.close();
                fileReader.close();

            } catch (IOException e) {
                System.out.println(" " + e.getMessage());
                //  throw new RuntimeException(e);
            }
        }

    }


}
