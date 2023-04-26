package model;

import java.sql.*;
import java.util.Properties;

//Ansvaret för denna klassen är endast att skapa sample/urval av spelareobjekt från databasen och lagra i en lista
//Urvalet ska bero på förinställningar. T ex bara premier league eller bara "lätta" spelare
//Slutligen skickas listan med urvalet av spelare vidare till en annan klass.
public class GetSample {
    private Connection conn;
    private Player[] playersSample;

    public GetSample() {
        conn = getDatabaseConnection();
        Player[] playersSample =GetSample(GameType);
        //skicka vidare playersSample till en annan klass som ansvarar för att generera frågor.
    }

    public Connection getDatabaseConnection() {
        String url = "jdbc:postgresql://pgserver.mau.se/gissa_fotbollsspelare";
        Properties props = new Properties();
        props.setProperty("user", "an5411");
        props.setProperty("password", "7sbqoxrh");
        //String backup user = "am7496"; "xlousk70"
        try {
            Connection conn = DriverManager.getConnection(url, props);
            System.out.println("Connection Established");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Plyaers[] GetSample(GameType onlyPremierLeague){
        //om gametype = only premier league
            //get players sample from PSQL om league_name == English Premier League
        return Players[] //urval/sample kan vara t ex 50 eller 100 Premier League spelare
    }

}
