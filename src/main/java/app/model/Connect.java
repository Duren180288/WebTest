package app.model;

import app.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Connect {
    public static final String USER = "root";
    public static final String PASSWORD = "180288";
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/dur?serverTimezone=Europe/Moscow&useSSL=false";
    private static Connect instance;
//    static Connection connection ;
//    static Statement statement ;


    public void goConnectToSQL() {

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            System.out.println("Registering JDBC driver...");
            //        Class.forName(JDBC_DRIVER);
            System.out.println("Creating connection to database...");
            System.out.println("Creating table in selected database...");

            try {
                ResultSet Rs = statement.executeQuery("SELECT * FROM dur.Users");
                if (Rs != null) {
                    System.out.println("Table Users already created");
                }
            } catch (Exception e) {
                getCreate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void getCreate() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE Users (id int auto_increment primary key,UserName varchar(30) not null,Password varchar(30) not null)");
            System.out.println("Table Users successfully created...");
        } catch (SQLException s) {
            s.getSQLState();
        }
    }

    public void add(User user) {
        String sqlInsert = "INSERT INTO users (UserName, Password) value (?, ?)";
        if (!user.getName().isEmpty()) {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {
                System.out.println("Insert to DB");
                String UserName = user.getName();
                String password = user.getPassword();
                System.out.println(UserName + " " + password);
                preparedStatement.setString(1, UserName);
                preparedStatement.setString(2, password);
                int rows = preparedStatement.executeUpdate(); //not insert without this string
                System.out.println(rows + "row  was added!");
            } catch (SQLException s) {
                s.getSQLState();
            }
        }
    }

    public List<String> showAddedUsers() {
        ArrayList<String> AddedUsers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM  users");
            while (resultSet.next()) {
                AddedUsers.add(resultSet.getString(2));
            }
        } catch (SQLException s) {
            s.getSQLState();
        }
        return AddedUsers;

    }

    public void deleteUser(String userName) {
        String sqlDelete = "DELETE FROM users WHERE UserName = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete)) {
            preparedStatement.setString(1, userName);
            System.out.println("Delete " + userName + " from DB");
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " row was deleted");
        } catch (SQLException s) {
            s.getSQLState();
        }
    }

    public String selectOneUserPass(String userName) {
        //String sqlSelect = "SELECT * FROM users WHERE UserName = ?";
        String sqlSelect = "SELECT * FROM dur.users WHERE UserName = ?";
        String res="";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect)) {
            preparedStatement.setString(1, userName);
            System.out.println("Select " + userName + " from DB");

            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
           // System.out.println(resultSet.getString("Password"));

            res = resultSet.getString("Password");
            }

        } catch (SQLException s) {
            s.getSQLState();
        }
        return res;
    }


   // ResultSet Rs = statement.executeQuery("SELECT * FROM dur.Users");

}

//            statement.executeUpdate(myTableName);
//        statement.executeUpdate("INSERT INTO carShop (CarName, Distance, Year, model) value ('Rotex', 405000, 1908, 'drz')");
//        //statement.executeUpdate("DELETE FROM carShop where id =2");
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM carShop where id =1 ");
//
//        while (resultSet.next()) {
//        String model = resultSet.getString(2);
//        int id = Integer.parseInt(resultSet.getString(1));
//        int year = Integer.parseInt(resultSet.getString(4));
//        int distance = Integer.parseInt(resultSet.getString(3));
//        System.out.println("id = " + id + "  model = " + model + " year = " + year + " distance = " + distance);
//        System.out.println(resultSet.getString(1) + " " +
//        resultSet.getString(2) + " " +
//        resultSet.getString(3) + " " +
//        resultSet.getString(4) + " " +
//        resultSet.getString(5));
//
//        }
//        System.out.println("Table successfully created...");

//
//        String myTableName = "CREATE TABLE AgentDetail ("
//                    + "idNo INT(64) NOT NULL AUTO_INCREMENT,"
//                    + "initials VARCHAR(2),"
//                    + "agentDate DATE,"
//                    + "agentCount INT(64), "
//                    + "PRIMARY KEY(idNo))";


//            String myTableName = "CREATE TABLE carShop ("
//                + "id int auto_increment primary key," +
//                "CarName varchar(30) not null," +
//                "Distance varchar(10) not null," +
//                "Year varchar(10) not null)";