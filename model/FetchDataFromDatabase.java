package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Scanner;

public class FetchDataFromDatabase {
    Connection conn;

    public FetchDataFromDatabase() {
        conn = getDatabaseConnection();
    }

    public Connection getDatabaseConnection() {

        String url = "jdbc:postgresql://pgserver.mau.se/onlinestore";
        Properties props = new Properties();
        props.setProperty("user", "an5411");
        props.setProperty("password", "7sbqoxrh");
        //String user = "am7496"; "xlousk70"

        try {
            Connection conn = DriverManager.getConnection(url, props);
            System.out.println("Connection Established");
            return conn;

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return null;
        }
    }
}
