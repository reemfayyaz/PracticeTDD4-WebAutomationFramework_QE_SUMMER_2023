package com.nysoftusa.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteToExcel {
    // Create an Excel file using java
    // Path: Where the file will be created
    // Absolute path: full path
    // relative path: partial path

    // DataType [][] arrayName=new DataType[][];


    public static void main(String[] args) {
        // String filePath="C:\\Users\\mhsha\\IdeaProjects\\LearnJava_QE_SUMMER2023\\DataTest/TestDataForAutomation.xlsx";
        //  String filePath="DataTest/TestDataForAutomation.xlsx";
        // String filePath="../LearnJava_QE_SUMMER2023/DataTest/TestDataForAutomation242342345.xlsx";
        String filePath = "../LearnJava_QE_SUMMER2023/DataTest/TestDataForAutomation" + RandomNumber.randomNumberGenerate() + ".xlsx";
        Object[][] customerInfo = {{"SL", "FirstName", "LastName", "Address"},
                {"101", "James", "smith", "123-34 broadway,APT#234, bronx,NY 233443,USA"},
                {"102", "William", "bob", "223-34 broadway,APT#274, Brooklyn,NY 233483,USA"},
                {"103", "Asif", "Iqbal", "523-34 broadway,APT#974, Queens,NY 55555,USA"},
                {"104", "Rachid", "Zaiz", "823-34 broadway,APT#174, Manhattan,NY 234234444,USA"}
        };

        Object[][] customerInfo1 = {
                {"SL", "FirstName", "LastName", "Address"},
                {"101", "James", "smith", "NY 233443 USA"},
                {"102", "William", "bob", "NY 233483 USA"},
                {"103", "Asif", "Iqbal", "NY 55555 USA"},
                {"103", "Asif", "Iqbal", "NY 55555 USA"},
                {"103", "Asif", "Iqbal", "NY 55555 USA"},
                {"103", "Asif", "Iqbal", "NY 55555 USA"},
                {"103", "Asif", "Iqbal", "NY 55555 USA"},
                {"103", "Asif", "Iqbal", "NY 55555 USA"},
                {"103", "Asif", "Iqbal", "NY 55555 USA"},
                {"103", "Asif", "Iqbal", "NY 55555 USA"},
                {"103", "Asif", "Iqbal", "NY 55555 USA"},
                {"103", "Asif", "Iqbal", "NY 55555 USA"},
                {"103", "Asif", "Iqbal", "NY 55555 USA"},
                {"103", "Asif", "Iqbal", "NY 55555 USA"},
                {"103", "Asif", "Iqbal", "NY 55555 USA"},
                {"120", "AZZZZ", "yyyyy", "NY 6666 USA"},
        };

        Object[][] patientInfo = {
                {"SL", "firstName", "lastName", "address"},
                {"101", "Jesmin", "Sultana", "Jamaica,NYC"},
                {"102", "Md", "Alam", "Queens,NYC"},
                {"103", 222.45, 999, true}

        };

        String name;
        String stname = "James";

        String[][] studentInfo = new String[3][4]; // declare

        studentInfo[0][0] = "Sl";
        studentInfo[0][1] = "FirstName";
        studentInfo[0][2] = "LastName";
        studentInfo[0][3] = "Address";

        studentInfo[1][0] = "1";
        studentInfo[1][1] = "Aziz";
        studentInfo[1][2] = "Elyaagoubi";
        studentInfo[1][3] = "Queens,NY";


        studentInfo[2][0] = "2";
        studentInfo[2][1] = "Asif";
        studentInfo[2][2] = "Iqbal";
        studentInfo[2][3] = "Bronx,NY";


        //   writeToExcelFile(filePath,studentInfo,"studentInformation");
        // writeToExcelFile(filePath, customerInfo, "customerInfo");
        //  writeToExcelFileUsingForLoop(filePath, patientInfo, "patientInfo");
       // writeToExcelFileUsingForLoop(filePath, customerInfo1, "patientInfo");
        writeToExcelFileUsingForLoop(filePath, customerInfo1, "patientInfo");

//        String[][] studentInfo1 = new String[3][4]; // declare
//        System.out.println("Row: "+studentInfo1.length);
//        System.out.println("Column: "+studentInfo1[0].length);


    }

    public static void writeToExcelFile(String filePath, Object[][] input, String sheetName) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);

        int rowNumber = 0;
        for (Object[] data : input) {
            Row row = sheet.createRow(rowNumber++);
            int columnNumber = 0;
            for (Object field : data) {
                Cell cell = row.createCell(columnNumber++);

                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                } else if (field instanceof Double) {
                    cell.setCellValue((Double) field);
                } else if (field instanceof Boolean) {
                    cell.setCellValue((Boolean) field);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            System.out.println("Excel File is Created");
            workbook.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not Found Exception " + fileNotFoundException.getMessage());
        } catch (IOException e) {
            System.out.println("IO Exception " + e.getMessage());
        }


    }

    public static void writeToExcelFileUsingForLoop(String filePath, Object[][] input, String sheetName) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);
        int rowNumber = 0;
        for (int i = 0; i < input.length; i++) {
            Row row = sheet.createRow(rowNumber++);
            int columnNumber = 0;
            for (int j = 0; j < input[0].length; j++) {
                Cell cell = row.createCell(columnNumber++);
                if (input[i][j] instanceof String) {
                    cell.setCellValue((String) input[i][j]);
                } else if (input[i][j] instanceof Integer) {
                    cell.setCellValue((Integer) input[i][j]);
                } else if (input[i][j] instanceof Double) {
                    cell.setCellValue((Double) input[i][j]);
                } else if (input[i][j] instanceof Boolean) {
                    cell.setCellValue((Boolean) input[i][j]);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            System.out.println("Excel File is Created");
            workbook.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not Found Exception " + fileNotFoundException.getMessage());
        } catch (IOException e) {
            System.out.println("IO Exception " + e.getMessage());
        }


    }





}