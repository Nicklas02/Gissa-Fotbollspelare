package model;

import java.sql.*;
import java.util.Properties;

//Ansvaret för denna klassen är endast att skapa sample/urval av spelareobjekt från databasen och lagra i en lista
//Urvalet ska bero på förinställningar. T ex bara premier league eller bara "lätta" spelare
//Slutligen skickas listan med urvalet av spelare vidare till en annan klass.
public class GetSample {
    private Connection conn;
    private GameType gameType = GameType.None;
    private final int listSize = 30;

    public GetSample() {
        conn = getDatabaseConnection();
        Player[] playersSample = getSample(gameType, listSize);
        //skicka vidare playersSample till en annan klass som ansvarar för att generera frågor.
        //printPlayerSample(playersSample);

        GenerateQuestionSet generateQuestionSet = new GenerateQuestionSet(playersSample);
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

    public Player[] getSample(GameType gameType, int listSize){
        Player[] playerSample = new Player[listSize];
        //om gametype == none
        if (gameType==GameType.None){
            System.out.println("hey");
            try {
                String QUERY = "select * from \"spelare2023\" " +
                        "order by overall desc;";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);
                Player player = null;
                int count = 0;

                while (rs.next()) {
                    player = new Player(rs.getInt("id"), rs.getString("name"), rs.getInt("age")
                    , rs.getString("nationality"), rs.getInt("height"));
                    playerSample[count] = player;
                    count++;
                    if (count>25){
                        break;
                    }
                }
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        //om gametype = only premier league
            //get players sample from PSQL om league_name == English Premier League
        return playerSample; //urval/sample kan vara t ex 50 eller 100 Premier League spelare
    }


    public void ChangeGameType(){
        //Ändra gametype entúm till t ex bara premier league
    }

    private void printPlayerSample(Player[] list) {
        for (Player p : list){
            if(p!=null) {
                System.out.println(p.toString());
            }
        }
    }


}
