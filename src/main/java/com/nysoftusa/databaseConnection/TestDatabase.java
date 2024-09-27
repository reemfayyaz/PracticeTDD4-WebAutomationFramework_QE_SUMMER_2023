package com.nysoftusa.databaseConnection;

import java.util.ArrayList;
import java.util.List;

import static com.nysoftusa.databaseConnection.ConnectDatabase.insertDataToMovieTable;


public class TestDatabase {


    public static void main(String[] args) {


      //  getDatabaseConnection();
      //  closeDatabaseConnection();

    //    String query="select * from newoffice";
     //   directDatabaseQueryExecution(query,"office_name");
      //  directDatabaseQueryExecution(query,"office_vp");
    //    readDatabaseTableColumn("newoffice","office_address");
//        List<String> dataValue=readDatabaseTableColumn("newoffice","office_name");
//        System.out.println("Get Signle Value : "+dataValue.get(1));
//
//        for (int i = 0; i < dataValue.size(); i++) {
//            if (dataValue.get(i).equalsIgnoreCase("Rachid Office") ){
//                System.out.println("yes we got Rachid office");
//             break;
//            } else {
//               System.out.println("no we do not got Rachid office");
//            }
//        }

     //   String productInfo="select * from products";
    //   String productInfo="select productQty from products where productColor='Red';";
      //  directDatabaseQueryExecution(productInfo,"productColor");
      //  readDatabaseTableColumnWithCompare("products","productQty","productColor","Blue");
      //  readDatabaseTableColumnWithCompare("products","productQty","productColor","productColor","Blue");


      //  insertDataToMovieTable();


        List<String> customerIdList=new ArrayList<>();
        customerIdList.add("601");
        customerIdList.add("602");
        customerIdList.add("603");

        List<String> customerFirstNameList=new ArrayList<>();
        customerFirstNameList.add("Asif");
        customerFirstNameList.add("aziz");
        customerFirstNameList.add("Sabiha");

        List<String> customerAddress=new ArrayList<>();
        customerAddress.add("Queens,NY");
        customerAddress.add("Manhattan,NY");
        customerAddress.add("Jamaica,NY");


     //   insertDataInSingleColumnFromListString("customers","customer_firstname",customerFirstNameList);
      //  insertDataInMultipleColumnFromListString("customers","customerId","customer_firstname",customerIdList,customerFirstNameList);
    //    insertDataInTripleColumnFromListString("customers","customerId","customer_firstname","customer_address",customerIdList,customerFirstNameList,customerAddress);


        insertDataToMovieTable();

     //   readUserProfileFromSQLTable("movie");




    }























}
