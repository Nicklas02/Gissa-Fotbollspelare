package model;

import java.sql.*;
import java.util.Properties;

public class HighScoreFromDatabase {
    private Connection conn;
    private final int listSize = 10;

    public HighScoreFromDatabase() {
        //newScoreToDatabase();
        String[] s = readList();
        for(int i=0; i<listSize; i++){
            System.out.println(s[i]);
        }
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

    public void newScoreToDatabase(String name, int score){
        conn = getDatabaseConnection();
        try {
            String QUERY = "insert into \"highscorelist\" (name, score)\n" +
                    "values(?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(QUERY);
            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            int affectedrows = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String[] readList(){
        String[] highScoreList = new String[listSize];
        int i = 0;
        conn = getDatabaseConnection();
        try {
            String QUERY = "select * from highscorelist\n" +
                    "order by score desc";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);

            while (rs.next() && i < listSize) {
                highScoreList[i] = String.format(i + 1 + " " + rs.getString("name") + " " +rs.getInt("score"));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highScoreList;
    }

    public static void main(String[] args) {
        HighScoreFromDatabase h = new HighScoreFromDatabase();
    }
}