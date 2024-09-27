package com.nysoftusa.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class ReadFromExcel {

    public static void main(String[] args) {
        String filetPath = "../LearnJava_QE_SUMMER2023/DataTest/Data.xlsx";
      //  readExcelFile(filetPath,1);
     //   readExcelFile(filetPath,0);
        readExcelFile1(filetPath,0);

    }


    public static void readExcelFile(String filePath, int sheetNumber) {
        Workbook workbook;
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(inputStream);
            Sheet dataTypeSheet = workbook.getSheetAt(sheetNumber);

            Iterator<Row> rowIterator = dataTypeSheet.iterator();
            while (rowIterator.hasNext()) {
                Row currentRow = rowIterator.next();

                Iterator<Cell> cellIterator = currentRow.iterator();
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();

                    if (currentCell.getCellType() == CellType.STRING) {
                        System.out.print(currentCell.getStringCellValue() + "         ");
                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue() + "         ");
                    } else if (currentCell.getCellType() == CellType.BOOLEAN) {
                        System.out.print(currentCell.getBooleanCellValue() + "         ");
                    }
                }
                System.out.println();
            }
            workbook.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException "+e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException "+e.getMessage());
        }


    }


    public static void readExcelFile1(String filePath, int sheetNumber) {
        Workbook workbook;
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(inputStream);
            Sheet dataTypeSheet = workbook.getSheetAt(sheetNumber);

            for (Row currentRow : dataTypeSheet) {
                for (Cell currentCell : currentRow) {
                    if (currentCell.getCellType() == CellType.STRING) {
                        System.out.print(currentCell.getStringCellValue() + "         ");
                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue() + "         ");
                    } else if (currentCell.getCellType() == CellType.BOOLEAN) {
                        System.out.print(currentCell.getBooleanCellValue() + "         ");
                    }
                }
                System.out.println();
            }
            workbook.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException "+e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException "+e.getMessage());
        }


    }

}
