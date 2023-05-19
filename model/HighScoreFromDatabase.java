package model;

import java.sql.*;
import java.util.Properties;

/**
 * Denna klassen hämtar topplistan från databasen samt skriver in nya resultat till den.
 */
public class HighScoreFromDatabase {
    private Connection conn;
    private final int listSize = 10;

    public HighScoreFromDatabase() {
        String[] s = readList();
    }

    /**
     * Metod för att få en förbindelse till databasen.
     * @return en förbindelse till databasen.
     */
    public Connection getDatabaseConnection() {
        String url = "jdbc:postgresql://pgserver.mau.se/gissa_fotbollsspelare";
        Properties props = new Properties();
        props.setProperty("user", "an5411");
        props.setProperty("password", "7sbqoxrh");
        //String backup user = "am7496"; "xlousk70"
        try {
            Connection conn = DriverManager.getConnection(url, props);
            System.out.println("Connection (HighScoreList) Established");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Skickar ett nytt resultat till databasen
     * @param name användarnamnen på spelaren
     * @param score resultatet på ronden
     * @param gameType vilken kategori man spelat på
     * @param difficulty vilken svårighetsgrad man spelat på
     */
    public void newScoreToDatabase(String name, int score, GameType gameType, Difficulty difficulty) {
        conn = getDatabaseConnection();
        try {
            String QUERY = "insert into \"highscorelist\" (name, score, category, difficulty)\n" +
                    "values(?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(QUERY);
            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            String category = "";
            if (gameType == GameType.PremierLeague) {
                category = "PL";
            } else if (gameType == GameType.LaLiga) {
                category = "La";
            } else if (gameType == GameType.Ligue1) {
                category = "L1";
            } else if (gameType == GameType.Bundesliga) {
                category = "Bu";
            } else if (gameType == GameType.SerieA) {
                category = "SA";
            }
            String difficult = " ";
            if(difficulty == Difficulty.Normal){
                difficult = "";
            } else {difficult = "(svår)";}
            pstmt.setString(3, category);
            pstmt.setString(4, difficult);
            int affectedrows = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            System.out.println("Result added to database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hämtar en lista med toppresultaten från databasen
     * @return En lista med resultat från databasen
     */
    public String[] readList() {
        String[] highScoreList = new String[listSize];
        int i = 0;
        conn = getDatabaseConnection();
        try {
            String QUERY = "select * from highscorelist\n" +
                    "order by score desc";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);

            while (rs.next() && i < listSize) {
                highScoreList[i] = String.format(i + 1 + " " + rs.getString("name") + " " +
                        rs.getInt("score") + " " + rs.getString("category") + " " +
                        rs.getString("difficulty"));
                i++;
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highScoreList;
    }

}