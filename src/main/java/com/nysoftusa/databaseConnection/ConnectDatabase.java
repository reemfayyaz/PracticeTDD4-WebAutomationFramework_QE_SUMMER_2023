package com.nysoftusa.databaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.nysoftusa.utilities.ReadPropertiesFrom.loadProperties;

public class ConnectDatabase {

    public static Connection connection = null;
    public static Statement statement = null;
    public static PreparedStatement preparedStatement = null;
    public static ResultSet resultSet = null;

    public static Connection getDatabaseConnection() {
        Properties properties = loadProperties("src/main/resources/Configuration.properties");

        String jdbcDriver = properties.getProperty("MYSQLJDBC.DRIVER");
        String jdbcUrl = properties.getProperty("MYSQLJDBC.URL");
        String userName = properties.getProperty("MYSQL_USER_NAME");
        String password = properties.getProperty("MYSQL_PASSWORD");

        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            statement = connection.createStatement();
            System.out.println("Database Connection Successful");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

    /**
     * This method is for Closing Database connection
     */
    public static void closeDatabaseConnection() {
        try {
            assert resultSet != null;
            resultSet.close();
            assert statement != null;
            statement.close();
            assert connection != null;
            connection.close();
            System.out.println("Database DisConnected Successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<String> getResultSetData(ResultSet resultSet, String columnName) {
        List<String> dataList = new ArrayList<>();
        while (true) {
            try {
                if (!resultSet.next()) break;
                String items;
                items = resultSet.getString(columnName);
                dataList.add(items);
            } catch (SQLException e) {

                System.out.println("SQLException " + e.getMessage());
            }
        }

        return dataList;
    }


    public static List<String> directDatabaseQueryExecution(String query, String columnName) {
        List<String> data = new ArrayList<>();
        ConnectDatabase.getDatabaseConnection(); // will Create connection to DB

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            data = getResultSetData(resultSet, columnName);
            System.out.println("Data Value : " + data);
            for (String dt : data) {
                System.out.println(dt);
            }
        } catch (SQLException e) {
            // throw new RuntimeException(e);
            System.out.println("SQLException " + e.getMessage());
        } finally {
            closeDatabaseConnection();
        }


        return data;

    }


    public static List<String> readDatabaseTableColumn(String tableName, String columnName) {
        List<String> data = new ArrayList<>();
        ConnectDatabase.getDatabaseConnection(); // will Create connection to DB

        try {
            statement = connection.createStatement();
            String query = "select * from " + tableName;
            resultSet = statement.executeQuery(query);
            data = getResultSetData(resultSet, columnName);
            System.out.println("Data Value : " + data);
            for (String dt : data) {
                System.out.println(dt);
            }
        } catch (SQLException e) {
            // throw new RuntimeException(e);
            System.out.println("SQLException " + e.getMessage());
        } finally {
            closeDatabaseConnection();
        }

        return data;

    }


    public static List<String> readDatabaseTableColumnWithCompare(String tableName, String columnName, String columnNameForCompare, String compareValue) {
        List<String> data = new ArrayList<>();
        ConnectDatabase.getDatabaseConnection(); // will Create connection to DB

        try {
            statement = connection.createStatement();
            // String query="select * from "+tableName;
            String query = "select " + columnName + " from " + tableName + " where " + columnNameForCompare + "='" + compareValue + "';";
            resultSet = statement.executeQuery(query);
            data = getResultSetData(resultSet, columnName);
            System.out.println("Data Value : " + data);
            for (String dt : data) {
                System.out.println(dt);
            }
        } catch (SQLException e) {
            // throw new RuntimeException(e);
            System.out.println("SQLException " + e.getMessage());
        } finally {
            closeDatabaseConnection();
        }

        return data;

    }


    /**
     * Not working
     *
     * @param tableName
     * @param columnName1
     * @param columnName2
     * @param columnNameForCompare
     * @param compareValue
     */
    public static void readDatabaseTableColumnWithCompare(String tableName, String columnName1, String columnName2, String columnNameForCompare, String compareValue) {
        List<String> data1 = new ArrayList<>();
        List<String> data2 = new ArrayList<>();
        ConnectDatabase.getDatabaseConnection(); // will Create connection to DB
        try {
            statement = connection.createStatement();
            // String query="select * from "+tableName;
            String query = "select " + columnName1 + "," + columnName2 + " from " + tableName + " where " + columnNameForCompare + "='" + compareValue + "';";
            resultSet = statement.executeQuery(query);
            data1 = getResultSetData(resultSet, columnName1);
            data2 = getResultSetData(resultSet, columnName2);
            System.out.println("Data1 Value : " + data1);
            System.out.println("Data2 Value : " + data2);
            for (String dt : data1) {
                System.out.println(dt);
            }
            for (String dt : data2) {
                System.out.println(dt);
            }
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getMessage());
        } finally {
            closeDatabaseConnection();
        }


    }


    public static void insertDataToMovieTable() {
        // Movie Class is using to insert data

        Movie titanic = new Movie(20, "Titanic", 1997, "Romantic", "PG-13");
        Movie friends = new Movie(21, "Friends", 2002, "Comedy", "PG-11");
        Movie equalizer = new Movie(22, "Equalizer", 2000, "Action", "PG-10");
        Movie endgame = new Movie(23, "Endgame", 2020, "Action", "PG-9");
        Movie inTime = new Movie(24, "In Time", 2011, "sci-fi/action", "PG-8");


        System.out.println(titanic.getMovieTitle());
        System.out.println(titanic.getMovieReleaseYear());
        System.out.println(endgame.getMovieRating());


        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(titanic);
        movies.add(friends);
        movies.add(equalizer);
        movies.add(endgame);
        movies.add(inTime);

        // To Develop Database connection
        getDatabaseConnection();

        // Truncate the table data

        try {
            String trucateTable="truncate table movie";
            statement.execute(trucateTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Insert Data to Movie table
        // Insert into `movies`(`movieId`,`movieTitle`,`movieReleaseyear`) values(101,'Titanic',"1997");

        for (Movie mv : movies) {
            String insertQuery = "insert into movie(movieId,movieTitle,movieReleaseYear,movieGenre,movieRating)" + "values( " + mv.getMovieId() + ",'" + mv.getMovieTitle() + "'," + mv.getMovieReleaseYear() + ",'" + mv.getMovieGenre() + "','" + mv.getMovieRating() + "')";
            try {
                statement.execute(insertQuery);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        String query = "select * from movie";
        try {
            statement.execute(query);
            System.out.println(statement.execute(query));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static void insertDataFromStringToTable(String arrayData, String tableName, String columnName) {
        getDatabaseConnection();
        // Insert into `movies`(`movieId`,`movieTitle`,`movieReleaseyear`) values(101,'Titanic',"1997");
        try {
            String query = "Insert into " + tableName + "(" + columnName + ") values(?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, arrayData);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getMessage());
        }
    }

    public static void insertDataFromStringToTable(String arrayData1, String arrayData2, String tableName, String columnName1, String columnName2) {
        getDatabaseConnection();
        // Insert into `movies`(`movieId`,`movieTitle`,`movieReleaseyear`) values(101,'Titanic',"1997");
        try {
            String query = "Insert into " + tableName + "(" + columnName1 + "," + columnName2 + ") values(?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, arrayData1);
            preparedStatement.setString(2, arrayData2);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getMessage());
        }
    }

    public static void insertDataInMultipleColumnFromListString1(String tableName, String columnName1, String columnName2, List<String> data1, List<String> data2) {
        getDatabaseConnection();
        try {
            String query = "Insert into " + tableName + "(" + columnName1 + "," + columnName2 + ") values(?,?)";
            preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < data1.size(); i++) {
                preparedStatement.setObject(1, data1.get(i));
                preparedStatement.setObject(2, data2.get(i));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getMessage());
        }
    }

    public static void insertDataInMultipleColumnFromListString(String tableName, String columnName1, String columnName2, List<String> data1, List<String> data2) {
        getDatabaseConnection();
        try {
            String query = "Insert into " + tableName + "(" + columnName1 + "," + columnName2 + ") values(?,?)";
            preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < data1.size(); i++) {
                preparedStatement.setObject(1, data1.get(i));
                preparedStatement.setObject(2, data2.get(i));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(" SQLException : " + e.getMessage());
        }


    }

    public static void insertDataInTripleColumnFromListString(String tableName, String columnName1, String columnName2, String columnName3, List<String> data1, List<String> data2, List<String> data3) {
        getDatabaseConnection();
        try {
            String query = "Insert into " + tableName + "(" + columnName1 + "," + columnName2 + "," + columnName3 + ") values(?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < data1.size(); i++) {
                preparedStatement.setObject(1, data1.get(i));
                preparedStatement.setObject(2, data2.get(i));
                preparedStatement.setObject(3, data3.get(i));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(" SQLException : " + e.getMessage());
        }


    }
    public static void insertDataInSingleColumnFromListString(String tableName, String columnName1, List<String> data1) {
        getDatabaseConnection();
        try {
            String query = "Insert into " + tableName + "(" + columnName1 + ") values(?)";
            preparedStatement = connection.prepareStatement(query);
          //  preparedStatement.executeUpdate();
            for (int i = 0; i < data1.size(); i++) {
                preparedStatement.setObject(1,data1.get(i));
                preparedStatement.executeUpdate();
            }
            System.out.println("Successful");
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getMessage());
        }

    }


    public static List<Movie> readUserProfileFromSQLTable(String tableName) {
        List<Movie> movieList = new ArrayList<>();
        Movie movie;
        Connection connection = getDatabaseConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from " + tableName;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("movieId");
                String title = resultSet.getString("movieTitle");
                int releaseYear = resultSet.getInt("movieReleaseYear");
                String genre = resultSet.getString("movieGenre");
                String rating = resultSet.getString("movieRating");

                System.out.format("%s,%s,%s,%s,%s\n",id,title,releaseYear,genre,rating);

                movie=new Movie(id,title,releaseYear,genre,rating);
                movieList.add(movie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movieList;
    }


}
