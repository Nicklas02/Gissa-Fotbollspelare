package model;

import java.sql.*;
import java.util.Properties;

//Ansvaret för denna klassen är endast att skapa sample/urval av spelareobjekt från databasen och lagra i en lista
//Urvalet ska bero på förinställningar. T ex bara premier league eller bara "lätta" spelare
//Slutligen skickas listan med urvalet av spelare vidare till en annan klass.
public class GetSample {
    private Connection conn;
    private GameType gameType = GameType.PremierLeague;
    private final int listSize = 30;

    public GetSample() {
        conn = getDatabaseConnection();
        //Player[] playersSample = getSample(gameType, listSize);
        //skicka vidare playersSample till en annan klass som ansvarar för att generera frågor.
        //printPlayerSample(playersSample);
        //generateQuestionSet = new GenerateQuestionSet(playersSample);

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

    public Player[] getSample(GameType gameType) {
        Player[] playerSample = new Player[listSize];
        Player player = null;
        int count = 0;
        ResultSet rs = null;
        Statement stmt = null;
        //om gametype == none
        if (gameType == GameType.None) {
            try {
                String QUERY = "select * from \"spelare2023\" " +
                        "order by overall desc;";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(QUERY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (gameType == GameType.PremierLeague) {
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
        } else if (gameType == GameType.LaLiga) {
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
        } else if (gameType == GameType.Bundesliga) {
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
        } else if (gameType == GameType.Ligue1) {
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
        } else if (gameType == GameType.SerieA) {
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
            while (rs.next()) {
                player = new Player(rs.getInt("id"), rs.getString("name"), rs.getInt("age")
                        , rs.getString("nationality"), rs.getInt("height"));
                playerSample[count] = player;
                count++;
                if (count > 15) {
                    break;
                }
                //System.out.println(player.toString());
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //om gametype = only premier league
        //get players sample from PSQL om league_name == English Premier League
        return playerSample; //urval/sample kan vara t ex 50 eller 100 Premier League spelare
    }


    public void ChangeGameType() {
        if (gameType == GameType.PremierLeague) {

        } else if (gameType == GameType.LaLiga) {

        } else if (gameType == GameType.SerieA) {

        } else if (gameType == GameType.Bundesliga) {

        } else if (gameType == GameType.Ligue1) {

        } else {
        }

    }

    private void printPlayerSample(Player[] list) {
        for (Player p : list) {
            if (p != null) {
                System.out.println(p.toString());
            }
        }
    }

}
