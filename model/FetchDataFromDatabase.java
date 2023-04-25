package model;

import controller.DatabaseController;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class FetchDataFromDatabase {
    Connection conn;
    private ArrayList<Player> currPlayersList = new ArrayList<>();
    private DatabaseController databaseController;
    Random rand = new Random();
    private int alt1 = 0;
    private int alt2 = 0;
    private int alt3 = 0;
    private int alt4 = 0;


    public FetchDataFromDatabase(DatabaseController databaseController) {
        conn = getDatabaseConnection();
        this.databaseController = databaseController;
    }

    /**
     * Upprättar en förbindelse med jdbc till databasen gissa fotbollsspelare. Därefter upprättas en query som skrivs
     * till psql för att sedan returnera och skriva ut data.
     *
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
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addPlayersToArray() {
        try {
            String QUERY = "select * from \"spelare\" " +
                    "join fifastats on spelare.id = fifastats.id " +
                    "order by fifastats.overall desc;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
            Player player = null;
            int limit = 100;
            int count = 0;

            while (rs.next() && count < limit) {
                player = new Player(count, rs.getString("namn"));
                player.setAge(rs.getInt("ålder"));
                currPlayersList.add(player);
                count++;
                //System.out.println(player.getName());
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Player> getCurrPlayersList() {
        return currPlayersList;
    }

    public String verifyAnswer(int answer) {
        //if questionVariabel == age / highestAge

        Player answerPlayer = currPlayersList.get(answer);
        int answerPlayerAge = answerPlayer.getAge();
        System.out.println("YOU ANSWERED " + answerPlayer.getName());
        boolean correct = true;

        if (currPlayersList.get(answer).getAge() < currPlayersList.get(alt1).getAge() || currPlayersList.get(answer).getAge() < currPlayersList.get(alt2).getAge() ||
                currPlayersList.get(answer).getAge() < currPlayersList.get(alt3).getAge() || currPlayersList.get(answer).getAge() < currPlayersList.get(alt4).getAge()) {
            correct = false;
        }

        String userOutput;
        if (correct) {
            userOutput = "Correct! ";
        } else {
            userOutput = "False! ";
        }

        userOutput += "\n" + "--------------------" + "\n" + "The answers are: ";

        userOutput += currPlayersList.get(alt1).getName() + " " + currPlayersList.get(alt1).getAge() + " --- ";
        userOutput += currPlayersList.get(alt2).getName() + " " + currPlayersList.get(alt2).getAge() + " --- ";
        userOutput += currPlayersList.get(alt3).getName() + " " + currPlayersList.get(alt3).getAge() + " --- ";
        userOutput += currPlayersList.get(alt4).getName() + " " + currPlayersList.get(alt4).getAge() + " --- ";

        return userOutput;
    }


    public String getAlternatives() {
        String str = "";
        alt1 = 0;
        alt2 = 0;
        alt3 = 0;
        alt4 = 0;

        for (int i = 0; i < 4; i++) {
            Player p = currPlayersList.get(rand.nextInt(100));
            str += p.getId();
            str += ": " + p.getName();
            str += "\n";
            if (alt1 == 0) {
                alt1 = p.getId();
            } else if (alt2 == 0) {
                alt2 = p.getId();
            } else if (alt3 == 0) {
                alt3 = p.getId();
            } else if (alt4 == 0) {
                alt4 = p.getId();
            }
        }

        return str;
    }
}
