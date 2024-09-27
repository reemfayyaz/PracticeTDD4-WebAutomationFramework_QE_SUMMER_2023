package com.nysoftusa.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesFrom {

    // Load Properties File
    public static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try {
            InputStream inputStream;
            inputStream = new FileInputStream(filePath);
            properties.load(inputStream);

        } catch (IOException ioException) {
            System.out.println("Exception -- IOException | FileNotFoundException : " + ioException.getMessage());
        }
        return properties;
    }








}
