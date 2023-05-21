package model;

import java.sql.*;
import java.util.Properties;


/**
 * Syftet med denna klassen är endast att skapa sample/urval av spelarobjekt från databasen och lagra i en lista
 * Urvalet ska bero på förinställningar. T ex bara premier league eller bara "lätta" spelare
 * Slutligen skickas listan med urvalet av spelare vidare till en annan klass.
 */
public class GetSample {
    private final Connection conn;
    private final int sampleSize;
    private final GameType gameType;

    public GetSample(GameType gameType, int sampleSize) {
        this.sampleSize=sampleSize;
        this.gameType=gameType;
        conn = getDatabaseConnection();
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

    /**
     * Syftet med denna metoden är att hämta ett antal spelare ur databasen för att sedan lagra dessa spelarna
     * i Player-objekt som sedermera kan användas för att generera frågor.
     * Databasen är sorterad efter de högst rankade spelarna först. Det innebär att ett större urval ger fler
     * lågt rankade spelare - dvs ett svårare spel. Därav skillnaden i urvalsstorlek beroende på svårighetsgrad.
     * Storleken på urvalen för de olika svårighetsgraderna kontrolleras från GenerateQuestionSet-klassens
     * konstanter i fältet.
     * @return en lista med spelarobjekt
     */
    public Player[] getSample() {
        Player[] playerSample = new Player[sampleSize];
        Player player;
        int count;
        ResultSet rs = null;
        Statement stmt = null;
        if (this.gameType == GameType.PremierLeague) {
            try {
                String QUERY = "select spelare2023.* from spelare2023\n" +
                        "join clubs \n" +
                        "on spelare2023.club = clubs.club\n" +
                        "where clubs.league = 'English Premier League'\n" +
                        "order by spelare2023.overall desc";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(QUERY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (this.gameType == GameType.LaLiga) {
            try {
                String QUERY = "select spelare2023.* from spelare2023\n" +
                        "join clubs \n" +
                        "on spelare2023.club = clubs.club\n" +
                        "where clubs.league = 'Spain Primera Division'\n" +
                        "order by spelare2023.overall desc";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(QUERY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (this.gameType == GameType.Bundesliga) {
            try {
                String QUERY = "select spelare2023.* from spelare2023\n" +
                        "join clubs \n" +
                        "on spelare2023.club = clubs.club\n" +
                        "where clubs.league = 'German 1. Bundesliga'\n" +
                        "order by spelare2023.overall desc";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(QUERY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (this.gameType == GameType.Ligue1) {
            try {
                String QUERY = "select spelare2023.* from spelare2023\n" +
                        "join clubs \n" +
                        "on spelare2023.club = clubs.club\n" +
                        "where clubs.league = 'French Ligue 1'\n" +
                        "order by spelare2023.overall desc";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(QUERY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (this.gameType == GameType.SerieA) {
            try {
                String QUERY = "select spelare2023.* from spelare2023\n" +
                        "join clubs \n" +
                        "on spelare2023.club = clubs.club\n" +
                        "where clubs.league = 'Italian Serie A'\n" +
                        "order by spelare2023.overall desc";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(QUERY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            count=0;
            while (rs.next()) {
                player = new Player(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("nationality"),
                        rs.getInt("height"), rs.getInt("weak_foot"),
                        rs.getInt("kit_number"),
                        rs.getString("value"),
                        rs.getString("wage"),
                        rs.getInt("overall"),
                        rs.getInt("skill_moves"),
                        rs.getString("club"),
                        rs.getString("position"));
                playerSample[count] = player;
                count++;
                if (count >= sampleSize) {
                    break;
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerSample;
    }

}
