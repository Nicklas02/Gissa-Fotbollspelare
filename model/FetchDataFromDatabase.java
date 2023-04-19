package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class FetchDataFromDatabase {
    Connection conn;

    public FetchDataFromDatabase() {
        conn = getDatabaseConnection();
    }

    /**
     * Upprättar en förbindelse med jdbc till databasen gissa fotbollsspelare. Därefter upprättas en query som skrivs
     * till psql för att sedan returnera och skriva ut data.
     * @return metoden returnerar en uppkoppling.
     */
    public Connection getDatabaseConnection() {
        String url = "jdbc:postgresql://pgserver.mau.se/gissa_fotbollsspelare";
        Properties props = new Properties();
        props.setProperty("user", "an5411");
        props.setProperty("password", "7sbqoxrh");
        //String backup user = "am7496"; "xlousk70"
        try {
            Connection conn = DriverManager.getConnection(url, props);
            System.out.println("Connection Established");

            String QUERY = "SELECT * FROM \"fotbollsspelare\"";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
            int count =0;
            while (rs.next()) {
                if (count>100){
                    break;
                }
                System.out.print("ID: " + rs.getString("id"));
                System.out.print(", Name: " + rs.getString("name"));
                System.out.println("----------------------------------------------------------");
                count++;
            }
            stmt.close();
            conn.close();
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
